package es.ugr.osgiliath.evolutionary.basiccomponents.genomes;

/*
Copyright 2010 Visin Suresh Paliath
Distributed under the BSD license
*/


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ugr.osgiliath.evolutionary.individual.Gene;

public class GenericTreeNode implements Cloneable{

   private Gene data;
   private List<GenericTreeNode> children;
   private GenericTreeNode parent;

   public GenericTreeNode() {
       super();
       children = new ArrayList<GenericTreeNode>();
   }

   public GenericTreeNode(Gene data) {
       this();
       setData(data);
   }

   public GenericTreeNode getParent() {
       return this.parent;
   }

   public List<GenericTreeNode> getChildren() {
       return this.children;
   }

   public int getNumberOfChildren() {
       return getChildren().size();
   }

   public boolean hasChildren() {
       return (getNumberOfChildren() > 0);
   }

   public void setChildren(List<GenericTreeNode> children) {
       for(GenericTreeNode child : children) {
          child.parent = this;
       }

       this.children = children;
   }

   public void addChild(GenericTreeNode child) {
       child.parent = this;
       children.add(child);
   }

   public void addChildAt(int index, GenericTreeNode child) throws IndexOutOfBoundsException {
       child.parent = this;
       children.add(index, child);
   }

   public void removeChildren() {
       this.children = new ArrayList<GenericTreeNode>();
   }

   public void removeChildAt(int index) throws IndexOutOfBoundsException {
       children.remove(index);
   }

   public GenericTreeNode getChildAt(int index) throws IndexOutOfBoundsException {
       return children.get(index);
   }
   
   public boolean isLeaf(){
	   if (children.size() == 0)
		   return true;
	   else
		   return false;
   }

   public Gene getData() {
       return this.data;
   }

   public void setData(Gene data) {
       this.data = data;
   }

   public String toString() {
       return getData().toString();
   }

   @Override
   public boolean equals(Object obj) {
       if (this == obj) {
          return true;
       }
       if (obj == null) {
          return false;
       }
       if (getClass() != obj.getClass()) {
          return false;
       }
       GenericTreeNode other = (GenericTreeNode) obj;
       if (data == null) {
          if (other.data != null) {
             return false;
          }
       } else if (!data.equals(other.data)) {
          return false;
       }
       return true;
   }

   /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((data == null) ? 0 : data.hashCode());
      return result;
   }

   public String toStringVerbose() {
       String stringRepresentation = getData().toString() + ":[";

       for (GenericTreeNode node : getChildren()) {
           stringRepresentation += node.getData().toString() + ", ";
       }

       //Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
       Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
       Matcher matcher = pattern.matcher(stringRepresentation);

       stringRepresentation = matcher.replaceFirst("");
       stringRepresentation += "]";

       return stringRepresentation;
   }
   
   public Object clone(){
	   GenericTreeNode clon = new GenericTreeNode();
	   Object dataCloned =  this.data.clone();
	   clon.setData((Gene)(dataCloned));
	   List<GenericTreeNode> childs = new ArrayList<GenericTreeNode>();
	   for(GenericTreeNode c:this.children){
		   GenericTreeNode childClone = (GenericTreeNode) c.clone();
		   childs.add(childClone);
	   }
	   clon.setChildren(childs);
	   
	   return clon;
	   
   }
}