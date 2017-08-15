import java.util.*;
//import java.io.*;
//import java.lang.*;
public class HanoiTree{
   static class Node<E>{
	  E A,B,C;
      Node<E> left,right;
      public Node(E A, E B, E C){
         this.A = A;
         this.B = B;
         this.C = C;
         left = null;
         right = null;
      }
   }
   
   public static int disks;
   public static Queue<Node<Stack<Integer>>> BFopen = new LinkedList<>();
   public static int length = 0;
   public static Stack<Node<Stack<Integer>>> BFclosed = new Stack<>();
   public static Stack<Node<Stack<Integer>>> DFopenClosed = new Stack<>();
   public static Stack<Node<Stack<Integer>>> DFpath = new Stack<>();
   public static Stack<Node<Stack<Integer>>> temp = new Stack<>();
   public static Node<Stack<Integer>> Goal;
   public static Stack<Integer> tempA = new Stack<>();
   public static Stack<Integer> tempB = new Stack<>();
   public static Stack<Integer> tempC = new Stack<>();
private static Scanner input;

   public static boolean DF(Node<Stack<Integer>> X){
	      if (X == null){
	         return false;
	      }
	      
	      else if (X.A.empty() && X.B.empty()){
	         Goal = X;
	         //display(X);
	         DFpath.push(X);
	         return true;
	      }
	      
	      else{
	         //DFpath.push(X);
	         //DFopenClosed.push(X);
	         one(X);
	         if (X.right != null)
	            DFopenClosed.push(X.right);
	         if (X.left != null)
	            DFopenClosed.push(X.left);
	        //if (!DFopenClosed.empty())
	        //return DF(DFopenClosed.pop());
	         if (DF(X.left)){
	        	length++;
	            DFpath.push(X);
	            return true;
	         }
	         if (DF(X.right)){
	        	length++;
	            DFpath.push(X);
	            return true;
	         }
	         return false;
	      }
	        //System.out.println(tester);
	        //return false;
	   }

   
   public static boolean BF(Node<Stack<Integer>> X){
      if (X == null){
         return false;
      }
      
      else if (X.A.empty() && X.B.empty()){
         Goal = X;
         DFpath.push(X);
         return true;
      }
      
      else{
         one(X);
         if (X.left != null){
         	DFopenClosed.push(X.left);
	     	BFopen.add(X.left);
	     }
	     if (X.right != null){
	        BFopen.add(X.right);
	        DFopenClosed.push(X.right);
	     }
	     if (BF(BFopen.remove())){
	    	if (X.right != null && X.right.equals(Goal)){
	    		Goal = X;
	    		length++;
	    		DFpath.push(X);
	    	}
	    	else if (X.left != null && X.left.equals(Goal)){
	    		length++;
	    		Goal = X;
	    		DFpath.push(X);
	    	}
	     	return true;
	     }
	     return false;
      }
   }
   
   @SuppressWarnings("unchecked")
   public static void one(Node<Stack<Integer>> root){
      Stack<Integer> A = (Stack<Integer>)root.A.clone();
      Stack<Integer> B = (Stack<Integer>)root.B.clone();
      Stack<Integer> C = (Stack<Integer>)root.C.clone();
      tempA = (Stack<Integer>)root.A.clone();
      tempB = (Stack<Integer>)root.B.clone();
      tempC = (Stack<Integer>)root.C.clone();
      if ((A.empty() && C.empty())){
         A.push(B.pop());
         root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
         B.push(A.pop());
         C.push(B.pop());
         root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
         B.push(C.pop());
      }
      else if(B.empty() && C.empty()){
         B.push(A.pop());
         root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
         A.push(B.pop());
         C.push(A.pop());
         root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
         A.push(C.pop());
      }
      else {
         if (!A.empty()){
            if (A.peek() == 1){
               B.push(A.pop());
               tempC.push(tempA.pop());
               if (search(DFopenClosed,A,B,C) && search(DFopenClosed,tempA,tempB,tempC)){
            	  root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
            	  root.right = new Node<Stack<Integer>>((Stack<Integer>)tempA.clone(),(Stack<Integer>)tempB.clone(),(Stack<Integer>)tempC.clone());
               }
               else if (search(DFopenClosed,A,B,C))
            	  root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
               else if (search(DFopenClosed,tempA,tempB,tempC))
            	  root.left = new Node<Stack<Integer>>((Stack<Integer>)tempA.clone(),(Stack<Integer>)tempB.clone(),(Stack<Integer>)tempC.clone());
               A.push(B.pop());
               tempA.push(tempC.pop());
            }
            else{
               if (B.empty() || B.peek() > A.peek()){
                  B.push(A.pop());
                  if (search(DFopenClosed,A,B,C))
                	  root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  A.push(B.pop());
               }
               else if (C.empty() || C.peek() > A.peek()){
                  C.push(A.pop());
                  if (search(DFopenClosed,A,B,C))
                	  root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  A.push(C.pop());
               }
            }
         }
      
         if (!B.empty()){
            if (B.peek() == 1){
            //one(root,B,A,C);
               A.push(B.pop());
               tempC.push(tempB.pop());
               if (search(DFopenClosed,A,B,C) && search(DFopenClosed,tempA,tempB,tempC)){
            	  root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  root.right = new Node<Stack<Integer>>((Stack<Integer>)tempA.clone(),(Stack<Integer>)tempB.clone(),(Stack<Integer>)tempC.clone());
               }
               else if (search(DFopenClosed,A,B,C))
                  root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
               else if (search(DFopenClosed,tempA,tempB,tempC))
                  root.left = new Node<Stack<Integer>>((Stack<Integer>)tempA.clone(),(Stack<Integer>)tempB.clone(),(Stack<Integer>)tempC.clone());
               B.push(A.pop());
               tempB.push(tempC.pop());
            }
            else{
               if (C.empty() || C.peek() > B.peek()){
                  C.push(B.pop());
                  if (search(DFopenClosed,A,B,C))
                     root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  B.push(C.pop());
               }
               else if (A.empty() || A.peek() > B.peek()){
                  A.push(B.pop());
                  if (search(DFopenClosed,A,B,C))
                     root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  B.push(A.pop());
               }
            }
         }
      
         if (!C.empty()){
            if(C.peek() == 1){
               B.push(C.pop());
               tempA.push(tempC.pop());
               if (search(DFopenClosed,A,B,C) && search(DFopenClosed,tempA,tempB,tempC)){
                  root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  root.right = new Node<Stack<Integer>>((Stack<Integer>)tempA.clone(),(Stack<Integer>)tempB.clone(),(Stack<Integer>)tempC.clone());
               }
               else if (search(DFopenClosed,A,B,C))
                  root.left = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
               else if (search(DFopenClosed,tempA,tempB,tempC))
                  root.left = new Node<Stack<Integer>>((Stack<Integer>)tempA.clone(),(Stack<Integer>)tempB.clone(),(Stack<Integer>)tempC.clone());
               tempC.push(tempA.pop());
               C.push(B.pop());
            }
            else{
               if (A.empty() || A.peek() > C.peek()){
                  A.push(C.pop());
                  if (search(DFopenClosed,A,B,C))
                     root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  C.push(A.pop());
               }
               else if (B.empty() || B.peek() > C.peek()){
                  B.push(C.pop());
                  if (search(DFopenClosed,A,B,C))
                     root.right = new Node<Stack<Integer>>((Stack<Integer>)A.clone(),(Stack<Integer>)B.clone(),(Stack<Integer>)C.clone());
                  C.push(B.pop());
               }
            }
         }
      }
   }

   public static boolean search(Stack<Node<Stack<Integer>>> OpClo,Stack<Integer> A,Stack<Integer> B,Stack<Integer> C){
      if (OpClo.empty())
         return true;
      while (!OpClo.empty()){
         temp.push(OpClo.pop());
      //if (compareStacks(temp.peek().A,X.A) && compareStacks(temp.peek().B,X.B) && compareStacks(temp.peek().C,X.C)){
         if (temp.peek().A.equals(A) && temp.peek().B.equals(B) && temp.peek().C.equals(C)){
            while(!temp.empty()){
               OpClo.push(temp.pop());
            }
            return false;
         }
      }
      while (!temp.empty()){
         OpClo.push(temp.pop());
      }
      return true;
   }
   
   @SuppressWarnings("unchecked")
   public static void display(Node<Stack<Integer>> node){
	   tempA.clear();
	   tempB.clear();
	   tempC.clear();
	   tempA = (Stack<Integer>)node.A.clone();
	   tempB = (Stack<Integer>)node.B.clone();
	   tempC = (Stack<Integer>)node.C.clone();
	   for(int i = disks; i > 0; i--){
	         System.out.println();
	         if (tempA.size() == i)
	            System.out.print(tempA.pop());
	         else
	        	System.out.print(' ');
	         if (tempB.size() == i)
	            System.out.print(tempB.pop());
	         else
	         	System.out.print(' ');
	         if (tempC.size() == i)
	            System.out.print(tempC.pop());
	         else
	         	System.out.print(' ');
	      }
	      System.out.println("\nABC");
   
   }
   
   public static void displayDFpath(){
	   while (!DFpath.empty()){
		   try{
			   Thread.sleep(1000);
		   } 
		   catch(InterruptedException ie){}
		   display(DFpath.peek());
		   DFpath.pop();
	   }
   }

   /*public boolean insert(List element){
      if (root == null){
         root = new TreeNode(element);
      }
   }*/

   public static void main(String[] arg){
      //if (tempB.equals(temp))
	  String search;
	   
	  input = new Scanner(System.in);	
	  System.out.println("This is a program that solves the famous Towers Of Hanoi problem. You (the user) have a choice between two distinct searches; breadth first search and depth first search. You must also specify the number of disks (3+) you wish to solve for. If that wasn't clear enough, it'll make sense later on.\n\n\n\n\n Enter the number of disks you wish to solve for (3-):");
	  disks = input.nextInt();
	  System.out.println("Enter the search you with to use (B)readth first or (D)epth first?:");
	  search = input.next();
	  Stack<Integer> A = new Stack<>();
	  for (int i = disks; i > 0; i--){
		  A.push(i);
	  }
	  Node<Stack<Integer>> root = new Node<Stack<Integer>>(A,new Stack<Integer>(),new Stack<Integer>());
	  DFopenClosed.push(root);
	  if (search.equals("D") || search.equals("d")){
	    System.out.println("The Solution path using the Depth first search is:");
	    DF(root);
	    displayDFpath();
	    System.out.println("The length from start state to the goal state is " + length);
	  }
	  else if (search.equals("B") || search.equals("b")){
		System.out.println("The Solution path using the Breadth first search is:");
		BF(root);
		displayDFpath();
		System.out.println("The length from start state to the goal state is " + length);
	  }
	  else{
		System.out.println("Invalid Search! The Program terminated!");
	  }
   }
}//Must return length of path from start state (root node) to goal state
