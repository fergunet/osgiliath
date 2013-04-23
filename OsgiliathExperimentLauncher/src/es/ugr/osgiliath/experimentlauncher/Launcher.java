/*
 * Launcher.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 */
package es.ugr.osgiliath.experimentlauncher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.EvolutionaryAlgorithm;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.Solution;
import es.ugr.osgiliath.problem.SolutionValue;
import es.ugr.osgiliath.utils.Logger;
import es.ugr.osgiliath.utils.Stopwatch;

public class Launcher extends OsgiliathService implements CommandProvider {

	List<Algorithm> algorithms = new ArrayList<Algorithm>();
	List<AlgorithmParameters> params = new ArrayList<AlgorithmParameters>();
	List<Problem> problems = new ArrayList<Problem>();
	List<List<Solution>> runs = new ArrayList<List<Solution>>();
	List<Long> runtimes = new ArrayList<Long>();
	
	Logger log;

	// int i;

	public void activate() {
		System.out.println("Launcher activated");
		// this.algorithms = new ArrayList<Algorithm>();
	}

	public void _listAlgorithms(CommandInterpreter ci) {
		System.out.println("ALGORITHMS");
		int j = 0;
		for (Algorithm alg : algorithms) {
			System.out.println(j + " " + alg);
			j++;
		}
	}

	public void _listAlgorithmParameters(CommandInterpreter ci) {
		System.out.println("ALGORITHM PARAMETERS");
		for (AlgorithmParameters par : this.params) {
			System.out.println(par);
		}
	}

	public void _listProblems(CommandInterpreter ci) {
		System.out.println("PROBLEMS");
		for (Problem par : this.problems) {
			System.out.println(par);
		}
	}

	public void _listOsgiliathServices(CommandInterpreter ci) {

	}

	public void _startAlgorithm(CommandInterpreter ci) {
		String id = ci.nextArgument();
		Algorithm al = this.algorithms.get(Integer.parseInt(id));
		al.start();
	}

	public void _startAll(CommandInterpreter ci) { // Send a reset event to all?
		
		int nTimes = 1;
		
		String times = ci.nextArgument();
		if(times != null){
			nTimes = Integer.parseInt(times);
		}
		this.launchExperiment(nTimes);
		/*this.params.get(0).updateParameter("algorithm.evolutionary.crossover.prob", 0.25);
		this.params.get(0).updateParameter("algorithm.evolutionary.mutator.prob", 0.25);
		
		this.launchExperiment(nTimes);
		
		this.params.get(0).updateParameter("algorithm.evolutionary.crossover.prob", 0.75);
		this.params.get(0).updateParameter("algorithm.evolutionary.mutator.prob", 0.50);
		
		this.launchExperiment(nTimes);
		*/
		//System.exit(1);
		
	}

	private void writeInFile(String s){
		try{
			Date d = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd.hh'h'mm'm'ss's'");
			String timestamp = format.format(d);

			// Create file
			String filename = "/home/pgarcia/Escritorio/pruebas/"+timestamp+".TIMES.txt";
			
			File file = new File(filename);
			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter out = new BufferedWriter(fw);

			out.write(s+"\n");
			out.close();
		}catch(Exception ex){
			System.out.println("EXCEPTION"+ex.getMessage());
		}
	}
	
	
	public void _executeFile(CommandInterpreter ci) {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(ci.nextArgument()));

			// MODIFY PROBLEM PARAMETERS
			for (Problem prob : this.problems) {
				prob.getParameters().setup(properties);
			}

			// MODIFY ALGORITHM PARAMETERS (select algorithm to execute)
			for (AlgorithmParameters algPars : this.params) {
				algPars.setup(properties);
			}
			// MODIFY EXPERIMENT PARAMETERS (fileLog y todo eso)

			String stringExec = properties
					.getProperty("osgiliath.numexecutions");
			int numExec = 1;

			if (stringExec != null)
				numExec = Integer.parseInt(stringExec);

			for (int i = 0; i < numExec; i++)
				this.algorithms.get(0).start(); // TODO change this

		} catch (IOException e) {
			System.out.println("Can't read properties file " + e.getMessage());
		}

	}
	
	public void launchExperiment(int nTimes){
		try {
			List<Thread> threads = new ArrayList<Thread>();
			this.runtimes.clear();//Clear because ADD
			int numRuns = nTimes;
			this.runs = new ArrayList<List<Solution>>();

			for (int i = 0; i < numRuns; i++) {
				this.runs.add(new ArrayList<Solution>());

				for (int j = 0; j < this.algorithms.size(); j++)
					this.runs.get(i).add(new Solution() { // TODO CAMBIAR ESTO,
								// POR FAVOR!

								@Override
								public void setSolutionValue(
										SolutionValue sValue) {
									// TODO Auto-generated method stub

								}

								@Override
								public SolutionValue getSolutionValue() {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public int compareTo(Object arg0) {
									// TODO Auto-generated method stub
									return 0;
								}
							});
			}

			for (int run = 0; run < numRuns; run++) {
				threads.clear();
				// BEGIN RUN
				/*this.getEventAdmin().sendEvent(
						EventCreator.createResetEvent(true));*/

				Stopwatch sw = new Stopwatch();
				sw.start();
				for (int i = 0; i < this.algorithms.size(); i++) {
					System.out.println("LANZO EL ALGORITMO"+i+" EN LA RUN "+run);
					AlgRunnable r = new AlgRunnable(i, run);
					Thread t = new Thread(r);
					threads.add(t);
					t.start();
				}

				for (Thread t : threads) {
					t.join();
				}
				// END RUN
				sw.stop();
				System.out.println("TIME "+sw.toValue());
				this.runtimes.add(sw.toValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		int runid = 0;
		for (List<Solution> runx : this.runs) {
			System.out.print(runid + "\t");
			for (Solution sol : runx)
				System.out.print(sol.getSolutionValue() + "\t");
			Collections.sort(runx);
			String exit = "BEST "+runx.get(0).getSolutionValue()+" TIME "+this.runtimes.get(runid);
			System.out.println(exit);
			//writeInFile(exit);
			//System.out.print("BEST "+runx.get(0).getSolutionValue());
			//System.out.println(" TIME "+this.runtimes.get(runid));
			runid++;
		}
	}

	public void addParameters(AlgorithmParameters params) {
		this.params.add(params);
		System.out.println("PARAMETERS ADDED");
	}

	public void unsetParameters() {
		this.params.remove(params);
	}

	public void addAlgorithm(Algorithm alg) {
		System.out.println("ALGORITHM ADDED--->");
		//String id = ((EvolutionaryAlgorithm)alg).getFrameworkId();
		//System.out.println("ALGORITHM ADDED--->"+id);
		algorithms.add(alg);
	}

	public void removeAlgorithm(Algorithm alg) {
		System.out.println("ALGORITHM DELETED-->");
		algorithms.remove(alg);
		
	}

	public void addProblem(Problem p) {
		this.problems.add(p);
	}

	public void removeProblem(Problem p) {
		this.problems.remove(p);
	}
	


	private void stopAllAlgorithms(){
		for(Algorithm a:this.algorithms){
			a.stop();
		}
	}
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	class AlgRunnable implements Runnable {
		public int id;
		public int run;

		public AlgRunnable(int idAlgorithm, int run) {
			this.id = idAlgorithm;
			this.run = run;
		}

		@Override
		public void run() {
			Algorithm a = algorithms.get(id);
		
			a.reset();
			
			a.start(); //OJO! QUE NO DURE MÁS QUE REMOTECALL!
			
			Solution sol = a.getObtainedSolution();
			
			System.out.println("EL ALGORITMO "+id+" EN LA EJECUCION "+run+" da "+sol);
			runs.get(run).set(id, sol);
			
			stopAllAlgorithms();
		

		}

	}
}
