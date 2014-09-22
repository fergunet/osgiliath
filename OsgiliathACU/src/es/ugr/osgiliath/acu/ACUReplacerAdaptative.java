package es.ugr.osgiliath.acu;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.evolutionary.migrator.Migrator;
import es.ugr.osgiliath.utils.Logger;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ACUReplacerAdaptative extends OsgiliathService
  implements Replacer
{
  Migrator migrator;
  Initializer initializer;
  Logger log;
  int bank;
  int nGenCounter;
  int generations = 0;

  public void select(Population pop, ArrayList<Individual> parents, ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring)
  {
    int reward = 0;

    pop.addIndividuals(offspring);

    int removed = offspring.size();
    removeWorstIndividuals(removed, pop);

    long sentTime = 0L;
    ArrayList<Individual> sents = new ArrayList<Individual>();

    int migrationGens = ((Integer)getAlgorithmParameters().getParameter("algorithm.evolutionary.migration.generations")).intValue();
    if (this.nGenCounter == migrationGens)
    {
      sentTime = System.nanoTime();
      sents = migrate(pop);
      sentTime = System.nanoTime() - sentTime;

      this.nGenCounter = 0;
    } else {
      this.nGenCounter += 1;
    }

    ArrayList<Individual> received = this.migrator.readLOCAL();

    int receivedSize = received.size();
    if (receivedSize > 0) {
      for (int z = 0; z < received.size(); z++) {
        MetaACUIndividual mind = (MetaACUIndividual)received.get(z);

        if (mind == null) {
          System.out.println("AQUI HAY UN POBLEMA");
          System.out.println("Received size" + received.size());
          System.out.println(received.toString());
        }
        mind.setFathers(null);
      }
      pop.addIndividuals(received);
      removeWorstIndividuals(receivedSize, pop);
    }

    this.generations += 1;

    int minSize = ((Integer)getAlgorithmParameters().getParameter("algorithm.acu.islandsize.min")).intValue();
    int maxSize = ((Integer)getAlgorithmParameters().getParameter("algorithm.acu.islandsize.max")).intValue();
    if (receivedSize > 0) {
      System.out.println("INDIVIDUO RECIBIDO");
      MetaACUIndividual mind = (MetaACUIndividual)received.get(received.size() - 1);
      int otherGens = mind.getIslandGenerations();

      double proportion = this.generations / (otherGens*1.0);
      double newSize = pop.getSize() * proportion;
      int intNewSize = (int)newSize;
      System.out.println("Yo: " + this.generations + " El otro:" + otherGens + " Prop:" + proportion + " Nuevo: " + newSize + " intNuevo:" + intNewSize);

      if (intNewSize > maxSize) {
        intNewSize = maxSize;
      }
      
      if(intNewSize < minSize)
    	  intNewSize = minSize;
      
      if (intNewSize > pop.getSize()){ //AD INDIVIDUALS
        String typeAddition = (String)getAlgorithmParameters().getParameter("algorithm.acu.addingtype");

        ArrayList<Individual> inds = new ArrayList<Individual>();

        if (typeAddition.equals("random")) {
          inds = this.initializer.initializeIndividuals(intNewSize - pop.getSize());
        }
        else if (typeAddition.equals("duplicates")) {
          while (inds.size() < intNewSize - pop.getSize()) {
            MetaACUIndividual newInd = (MetaACUIndividual)pop.getRandomIndividual();

            MetaACUIndividual newClone = (MetaACUIndividual)newInd.clone();
            inds.add(newClone);
          }

        }
        else
        {
          System.out.println("PARAMETER NOT FOUND!!!");
        }

        pop.addIndividuals(inds);
      }
      else { //REMOVE INDIVIDUALS
        removeWorstIndividuals(pop.getSize() - intNewSize, pop);
      }

    }

    analyzeInformation(pop, received, sents, reward, sentTime, pop.getSize());
  }

  private ArrayList<Individual> migrate(Population pop)
  {
    ArrayList<Individual> toSend = new ArrayList<Individual>();

    List<Individual> popinds = pop.getAllIndividuals();

    String migrationType = (String)getAlgorithmParameters().getParameter("algorithm.evolutionary.migration.type");

    if (migrationType.equals("ACU")) {
      for (Individual ind : popinds) {
        MetaACUIndividual mind = (MetaACUIndividual)ind;
        int acusPerMigration = ((Integer)getAlgorithmParameters().getParameter("algorithm.acu.migrationprize")).intValue();
        if (mind.getACUs() > acusPerMigration) {
          toSend.add(mind);
          mind.decreaseACUs(acusPerMigration);
        }
      }

    }

    if (migrationType.equals("best")) {
      int indsToMigrate = ((Integer)getAlgorithmParameters().getParameter("algorithm.evolutionary.migration.size")).intValue();
      toSend = pop.getNBestIndividuals(indsToMigrate);
    }
  
    if (migrationType.equals("random")) {
      int indsToMigrate = ((Integer)getAlgorithmParameters().getParameter("algorithm.evolutionary.migration.size")).intValue();
      for (int i = 0; i < indsToMigrate; i++) {
        toSend.add(pop.getRandomIndividual());
      }
    }
    for (Individual inda : toSend) {
      ((MetaACUIndividual)inda).setIslandGenerations(this.generations);
    }

    this.migrator.sendLOCAL(toSend);

    return toSend;
  }

  private void analyzeInformation(Population pop, ArrayList<Individual> received, ArrayList<Individual> sent, int reward, long sentTime, int popSize)
  {
    List<Individual> all = pop.getAllIndividuals();

    Fitness bestFitness = ((Individual)all.get(0)).getFitness();
    Fitness total = ((Individual)all.get(0)).getFitness();
    int foreigns = 0;

    String bestFitnessSentStr = "";
    String avgFitnessSentStr = "";
    String bestFitnessReceivedStr = "";
    String avgFitnessReceivedStr = "";

    MetaACUIndividual first = (MetaACUIndividual)all.get(0);

    double totalMigrationProb = first.getMigrationProb();
    int totalACUs = first.getACUs();
    int ACUs_best = totalACUs;
    int max_ACUs = first.getACUs();

    if (!first.getIslandId().equals(getFrameworkId())) {
      foreigns++;
    }

    for (int i = 1; i < all.size(); i++) {
      MetaACUIndividual ind = (MetaACUIndividual)all.get(i);

      if (ind.getFitness().compareTo(bestFitness) < -1) {
        bestFitness = ind.getFitness();
        ACUs_best = ind.getACUs();
      }

      if (ind.getACUs() > max_ACUs) {
        max_ACUs = ind.getACUs();
      }
      if (!ind.getIslandId().equals(getFrameworkId()))
        foreigns++;
      total = total.add(ind.getFitness());
      totalACUs += ind.getACUs();
      totalMigrationProb += ind.getMigrationProb();
    }

    Fitness avgFitness = total.divide(all.size());
    double avgACU = totalACUs / all.size();
    double avgMigration = totalMigrationProb / all.size();
    double percForeigns = foreigns / all.size();
    DecimalFormat num = new DecimalFormat("####.00000000");
    String avgACUstr = num.format(avgACU);
    String avgMigrationStr = num.format(avgMigration);
    String percForeignsStr = num.format(percForeigns);

    if (sent.size() > 0) {
      Fitness bestFitnessSent = ((Individual)sent.get(0)).getFitness();
      Fitness totalSent = ((Individual)sent.get(0)).getFitness();

      for (int i = 1; i < sent.size(); i++) {
        Individual ind = (Individual)sent.get(i);

        if (ind.getFitness().compareTo(bestFitnessSent) < -1)
          bestFitnessSent = ind.getFitness();
        totalSent = totalSent.add(ind.getFitness());
      }

      Fitness avgFitnessSent = totalSent.divide(sent.size());
      avgFitnessSentStr = avgFitnessSent.toString();
      bestFitnessSentStr = bestFitnessSent.toString();
    }

    if (received.size() > 0) {
      Fitness bestFitnessReceived = ((Individual)received.get(0)).getFitness();
      Fitness totalReceived = ((Individual)received.get(0)).getFitness();

      for (int i = 1; i < received.size(); i++) {
        Individual ind = (Individual)received.get(i);

        if (ind.getFitness().compareTo(bestFitnessReceived) < -1)
          bestFitnessReceived = ind.getFitness();
        totalReceived = totalReceived.add(ind.getFitness());
      }

      Fitness avgFitnessReceived = totalReceived.divide(received.size());
      avgFitnessReceivedStr = avgFitnessReceived.toString();
      bestFitnessReceivedStr = bestFitnessReceived.toString();
    }

    this.log.stats(bestFitness.toString() + ";" + 
      avgFitness.toString() + ";" + 
      bestFitnessSentStr + ";" + 
      avgFitnessSentStr + ";" + 
      bestFitnessReceivedStr + ";" + 
      avgFitnessReceivedStr + ";" + 
      avgACUstr + ";" + 
      max_ACUs + ";" + 
      ACUs_best + ";" + 
      totalACUs + ";" + 
      avgMigrationStr + ";" + 
      percForeignsStr + ";" + 
      sent.size() + ";" + 
      received.size() + ";" + 
      reward + ";" + 
      sentTime + ";" + 
      popSize + 
      "\n");
  }

  private int rewardingAverage(List<Individual> offspring, Population pop)
  {
    int totalAcus = 0;

    List<Individual> popinds = pop.getAllIndividuals();

    Fitness total = ((Individual)popinds.get(0)).getFitness();
    for (int i = 1; i < popinds.size(); i++)
    {
      total = total.add(((Individual)popinds.get(i)).getFitness());
    }

    Fitness avg = total.divide(pop.getSize());

    for (Individual ind : offspring) {
      MetaACUIndividual mind = (MetaACUIndividual)ind;

      if (mind.getFitness().compareTo(avg) == -1)
      {
        for (MetaACUIndividual father : mind.getFathers()) {
          int reward = ((Integer)getAlgorithmParameters().getParameter("algorithm.acu.reward")).intValue();

          totalAcus += reward;
          father.increaseACUs(reward);
        }

      }

      mind.setFathers(null);
    }

    return totalAcus;
  }

  private void removeIndividualsWithoutACUs(Population pop)
  {
  }

  private void removeWorstIndividuals(int indstoremove, Population pop)
  {
    List<Individual> worst = pop.getNWorstIndividuals(indstoremove);

    for (Individual i : worst)
      pop.removeIndividual(i);
  }

  public void setMigrator(Migrator mig) {
    this.migrator = mig;
  }

  public void unsetMigrator(Migrator mig) {
    this.migrator = null;
  }

  public void reset()
  {
    this.migrator.reset();
    this.bank = ((Integer)getAlgorithmParameters().getParameter("algorithm.acu.bank")).intValue();
    this.nGenCounter = 0;
    this.generations = 0;
  }

  public void setLogger(Logger log)
  {
    this.log = log;
  }

  public void unsetLogger(Logger log) {
    this.log = null;
  }

  public Initializer getInitializer() {
    return this.initializer;
  }

  public void setInitializer(Initializer initializer) {
    this.initializer = initializer;
  }
}