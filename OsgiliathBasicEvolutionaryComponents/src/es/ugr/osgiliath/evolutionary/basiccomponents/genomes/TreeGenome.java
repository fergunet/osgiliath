package es.ugr.osgiliath.evolutionary.basiccomponents.genomes;

/*
Copyright 2010 Vivin Suresh Paliath
Distributed under the BSD License
*/



import java.util.*;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class TreeGenome implements Cloneable,Genome{

 
private GenericTreeNode root;

   public TreeGenome() {
       super();
   }

   public GenericTreeNode getRoot() {
       return this.root;
   }

   public void setRoot(GenericTreeNode root) {
       this.root = root;
   }

   public int getNumberOfNodes() {
       int numberOfNodes = 0;

       if(root != null) {
           numberOfNodes = auxiliaryGetNumberOfNodes(root) + 1; //1 for the root!
       }

       return numberOfNodes;
   }
   
   private int auxiliaryGetNumberOfNodes(GenericTreeNode node) {
       int numberOfNodes = node.getNumberOfChildren();

       for(GenericTreeNode child : node.getChildren()) {
           numberOfNodes += auxiliaryGetNumberOfNodes(child);
       }

       return numberOfNodes;
   }
   
   /**
    * 
    * @return
    */
   public List<GenericTreeNode> getNodeList() {
       
       ArrayList<GenericTreeNode> list = new ArrayList<GenericTreeNode>();

       if(root != null) {
    	  // list.add(root);    	   
           list.addAll(auxiliaryGetNodeList(root));
       }

       return list;
   }
   
   private List<GenericTreeNode> auxiliaryGetNodeList(GenericTreeNode node) {
	   ArrayList<GenericTreeNode> list = new ArrayList<GenericTreeNode>();
	   list.add(node);
       for(GenericTreeNode child : node.getChildren()) {
           list.addAll(auxiliaryGetNodeList(child));
       }

       return list;
   }
   
  

   
   
   public int getDepth(){
	   int depth = 0;
	   if(root != null) {
		   depth = auxiliaryGetDepth(root); //1 for the root!
	   }
	   return depth;
   }
   
   private int auxiliaryGetDepth(GenericTreeNode node) {
       
       int max = 0;
       
       if(node.getChildren().size()==0)
    	   return 1;
       
       for(GenericTreeNode child : node.getChildren()) {
    	   int childDepth = auxiliaryGetDepth(child);
    	   if(max<childDepth)
    		   max = childDepth;
    	   //System.out.println("CHD "+childDepth);
       }

       return max+1;
   }

   public boolean exists(Gene dataToFind) {
       return (find(dataToFind) != null);
   }

   public GenericTreeNode find(Gene dataToFind) {
       GenericTreeNode returnNode = null;

       if(root != null) {
           returnNode = auxiliaryFind(root, dataToFind);
       }

       return returnNode;
   }

   private GenericTreeNode auxiliaryFind(GenericTreeNode currentNode, Gene dataToFind) {
       GenericTreeNode returnNode = null;
       int i = 0;

       if (currentNode.getData().equals(dataToFind)) {
           returnNode = currentNode;
       }

       else if(currentNode.hasChildren()) {
           i = 0;
           while(returnNode == null && i < currentNode.getNumberOfChildren()) {
               returnNode = auxiliaryFind(currentNode.getChildAt(i), dataToFind);
               i++;
           }
       }

       return returnNode;
   }

   public boolean isEmpty() {
       return (root == null);
   }

   public List<GenericTreeNode> build(GenericTreeTraversalOrderEnum traversalOrder) {
       List<GenericTreeNode> returnList = null;

       if(root != null) {
           returnList = build(root, traversalOrder);
       }

       return returnList;
   }

   public List<GenericTreeNode> build(GenericTreeNode node, GenericTreeTraversalOrderEnum traversalOrder) {
       List<GenericTreeNode> traversalResult = new ArrayList<GenericTreeNode>();

       if(traversalOrder == GenericTreeTraversalOrderEnum.PRE_ORDER) {
           buildPreOrder(node, traversalResult);
       }

       else if(traversalOrder == GenericTreeTraversalOrderEnum.POST_ORDER) {
           buildPostOrder(node, traversalResult);
       }

       return traversalResult;
   }

   private void buildPreOrder(GenericTreeNode node, List<GenericTreeNode> traversalResult) {
       traversalResult.add(node);

       for(GenericTreeNode child : node.getChildren()) {
           buildPreOrder(child, traversalResult);
       }
   }

   private void buildPostOrder(GenericTreeNode node, List<GenericTreeNode> traversalResult) {
       for(GenericTreeNode child : node.getChildren()) {
           buildPostOrder(child, traversalResult);
       }

       traversalResult.add(node);
   }

   public Map<GenericTreeNode, Integer> buildWithDepth(GenericTreeTraversalOrderEnum traversalOrder) {
       Map<GenericTreeNode, Integer> returnMap = null;

       if(root != null) {
           returnMap = buildWithDepth(root, traversalOrder);
       }

       return returnMap;
   }

   public Map<GenericTreeNode, Integer> buildWithDepth(GenericTreeNode node, GenericTreeTraversalOrderEnum traversalOrder) {
       Map<GenericTreeNode, Integer> traversalResult = new LinkedHashMap<GenericTreeNode, Integer>();

       if(traversalOrder == GenericTreeTraversalOrderEnum.PRE_ORDER) {
           buildPreOrderWithDepth(node, traversalResult, 0);
       }

       else if(traversalOrder == GenericTreeTraversalOrderEnum.POST_ORDER) {
           buildPostOrderWithDepth(node, traversalResult, 0);
       }

       return traversalResult;
   }

   private void buildPreOrderWithDepth(GenericTreeNode node, Map<GenericTreeNode, Integer> traversalResult, int depth) {
       traversalResult.put(node, depth);

       for(GenericTreeNode child : node.getChildren()) {
           buildPreOrderWithDepth(child, traversalResult, depth + 1);
       }
   }

   private void buildPostOrderWithDepth(GenericTreeNode node, Map<GenericTreeNode, Integer> traversalResult, int depth) {
       for(GenericTreeNode child : node.getChildren()) {
           buildPostOrderWithDepth(child, traversalResult, depth + 1);
       }

       traversalResult.put(node, depth);
   }

   public String toString() {
       /*
       We're going to assume a pre-order traversal by default
        */

       String stringRepresentation = "";

       if(root != null) {
           stringRepresentation = build(GenericTreeTraversalOrderEnum.PRE_ORDER).toString();

       }

       return stringRepresentation;
   }

   public String toStringWithDepth() {
       /*
       We're going to assume a pre-order traversal by default
        */

       String stringRepresentation = "";

       if(root != null) {
           stringRepresentation = buildWithDepth(GenericTreeTraversalOrderEnum.PRE_ORDER).toString();
       }

       return stringRepresentation;
   }
   
   /**
    * Does not take into account the root!!!!
    * @return 
    */
   public GenericTreeNode getRandomBranch(){
	   List<GenericTreeNode> list = this.getNodeList();
	   double r = (Math.random()*(list.size()-1)+1);
	   int ir = (int) r;
	   //System.out.println("RANDOM BRANCH "+ir);
	   return list.get(ir);
	  
   }
   
   /**
    * The root can be selected (FIXED)
    * @return 
    */
   public GenericTreeNode getRandomNode(){
	   List<GenericTreeNode> list = this.getNodeList();
	   int idx = new Random().nextInt(list.size());
	   
	   return list.get(idx);
	   
   }
   
   public Object clone(){
	   TreeGenome clon = new TreeGenome();
	   clon.setRoot((GenericTreeNode)this.root.clone());
	   return clon;
	   
	   
   }
}