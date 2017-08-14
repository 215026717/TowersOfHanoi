import java.util.*;
import java.io.*;
import java.lang.*;
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
   public static int tester = 0;
   public static Stack<Node<Stack<Integer>>> open = new Stack<>();
   public static Stack<Node<Stack<Integer>>> closed = new Stack<>();
   public static Stack<Node<Stack<Integer>>> temp = new Stack<>();
   public static Node<Stack<Integer>> Goal;
   public static Stack<Integer> tempA = new Stack<>();
   public static Stack<Integer> tempB = new Stack<>();
   public static Stack<Integer> tempC = new Stack<>();

   public static boolean DF(Node<Stack<Integer>> X){
      if (X == null){
         System.out.println("null");
         return false;
      }
      else if (X.A.empty() && X.B.empty()){
         //tester++;
         Goal = X.left;
         closed.push(X);
         System.out.println(X.C.peek());
         return true;
      }
      else{
         //closed.push(X);
         //open.push(X);
         one(X);
         tester++;
         if (X.right != null)
            open.push(X.right);
         if (X.left != null)
            open.push(X.left);
        //if (!open.empty())
        //return DF(open.pop());
         if (DF(X.left)){
            closed.push(X);
            return true;
         }
         if (DF(X.right)){
            closed.push(X);
            return true;
         }
         return false;
      }
        //System.out.println(tester);
        //return false;
   }

   public static void one(Node<Stack<Integer>> root){
      Stack<Integer> A = (Stack<Integer>)root.A.clone();
      Stack<Integer> B = (Stack<Integer>)root.B.clone();
      Stack<Integer> C = (Stack<Integer>)root.C.clone();
      tempA = (Stack<Integer>)root.A.clone();
      tempB = (Stack<Integer>)root.B.clone();
      tempC = (Stack<Integer>)root.C.clone();
      if ((A.empty() && C.empty())){
         A.push(B.pop());
         root.left = new Node(A.clone(),B.clone(),C.clone());
         B.push(A.pop());
         C.push(B.pop());
         root.right = new Node(A.clone(),B.clone(),C.clone());
         B.push(C.pop());
      }
      else if(B.empty() && C.empty()){
         B.push(A.pop());
         root.left = new Node(A.clone(),B.clone(),C.clone());
         A.push(B.pop());
         C.push(A.pop());
         root.right = new Node(A.clone(),B.clone(),C.clone());
         A.push(C.pop());
      }
      else {
         if (!A.empty()){
            if (A.peek() == 1){
               B.push(A.pop());
               tempC.push(tempA.pop());
               if (search(open,A,B,C) && search(open,tempA,tempB,tempC)){
                  root.left = new Node(A.clone(),B.clone(),C.clone());
                  root.right = new Node(tempA.clone(),tempB.clone(),tempC.clone());
               }
               else if (search(open,A,B,C))
                  root.left = new Node(A.clone(),B.clone(),C.clone());
               else if (search(open,tempA,tempB,tempC))
                  root.left = new Node(tempA.clone(),tempB.clone(),tempC.clone());
               A.push(B.pop());
               tempA.push(tempC.pop());
            }
            else{
               if (B.empty() || B.peek() > A.peek()){
                  B.push(A.pop());
                  if (search(open,A,B,C))
                     root.right = new Node(A.clone(),B.clone(),C.clone());
                  A.push(B.pop());
               }
               else if (C.empty() || C.peek() > A.peek()){
                  C.push(A.pop());
                  if (search(open,A,B,C))
                     root.right = new Node(A.clone(),B.clone(),C.clone());
                  A.push(C.pop());
               }
            }
         }
      
         if (!B.empty()){
            if (B.peek() == 1){
            //one(root,B,A,C);
               A.push(B.pop());
               tempC.push(tempB.pop());
               if (search(open,A,B,C) && search(open,tempA,tempB,tempC)){
                  root.left = new Node(A.clone(),B.clone(),C.clone());
                  root.right = new Node(tempA.clone(),tempB.clone(),tempC.clone());
               }
               else if (search(open,A,B,C))
                  root.left = new Node(A.clone(),B.clone(),C.clone());
               else if (search(open,tempA,tempB,tempC))
                  root.left = new Node(tempA.clone(),tempB.clone(),tempC.clone());
               B.push(A.pop());
               tempB.push(tempC.pop());
            }
            else{
               if (C.empty() || C.peek() > B.peek()){
                  C.push(B.pop());
                  if (search(open,A,B,C))
                     root.right = new Node(A.clone(),B.clone(),C.clone());
                  B.push(C.pop());
               }
               else if (A.empty() || A.peek() > B.peek()){
                  A.push(B.pop());
                  if (search(open,A,B,C))
                     root.right = new Node(A.clone(),B.clone(),C.clone());
                  B.push(A.pop());
               }
            }
         }
      
         if (!C.empty()){
            if(C.peek() == 1){
               B.push(C.pop());
               tempA.push(tempC.pop());
               if (search(open,A,B,C) && search(open,tempA,tempB,tempC)){
                  root.left = new Node(A.clone(),B.clone(),C.clone());
                  root.right = new Node(tempA.clone(),tempB.clone(),tempC.clone());
               }
               else if (search(open,A,B,C))
                  root.left = new Node(A.clone(),B.clone(),C.clone());
               else if (search(open,tempA,tempB,tempC))
                  root.left = new Node(tempA.clone(),tempB.clone(),tempC.clone());
               tempC.push(tempA.pop());
               C.push(B.pop());
            }
            else{
               if (A.empty() || A.peek() > C.peek()){
                  A.push(C.pop());
                  if (search(open,A,B,C))
                     root.right = new Node(A.clone(),B.clone(),C.clone());
                  C.push(A.pop());
               }
               else if (B.empty() || B.peek() > C.peek()){
                  B.push(C.pop());
                  if (search(open,A,B,C))
                     root.right = new Node(A.clone(),B.clone(),C.clone());
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

   /*public static boolean compareStacks(Stack<Integer> A, Stack<Integer> B){
      if (A.empty() && B.empty())
         return true;
      else if (A.empty() || B.empty()){
         return false;}
      while ((!A.empty() && !B.empty()) && (A.peek() == B.peek())){
         tempA.push(A.pop());
         tempB.push(B.pop());
         if (A.empty() && B.empty()){
            while (!tempA.empty() && !tempB.empty()){
               A.push(tempA.pop());
               B.push(tempB.pop());
            }
            //tempA.clear();
            //tempB.clear();
            return true;
         }
      }
      while (!tempA.empty() && !tempB.empty()){
         A.push(tempA.pop());
         B.push(tempB.pop());
      }
      //tempA.clear();
      //tempB.clear();
      return false;
   }*/

   public static void constructTree(){
      //Node<Stack<Integer>> root = new Node(
      //root.A = new Stack<>();
      Stack<Integer> A = new Stack<>();
      //Stack<Integer> B = new Stack<>();
      //Stack<Integer> C = new Stack<>();
      A.push(3);
      A.push(2);
      A.push(1);
      Node<Stack<Integer>> root = new Node(A,new Stack<>(),new Stack<>());
      open.push(root);
      if (DF(root))
         System.out.println(closed.size());
      //System.out.println(root.left.right.C.peek());
   
   }
   
   public static void display(){
   while (!closed.empty()){
      for(int i = 3; i > 0; i--){
         System.out.println();
         if (closed.peek().A.size() == i)
            System.out.print(closed.peek().A.pop());
         if (closed.peek().B.size() == i)
            System.out.print(closed.peek().B.pop());
         if (closed.peek().C.size() == i)
            System.out.print(closed.peek().C.pop());
      }
      System.out.println("\nABC");
      closed.pop();
      }
   }

   /*public boolean insert(List element){
      if (root == null){
         root = new TreeNode(element);
      }
   }*/

   public static void main(String[] arg){
      //if (tempB.equals(temp))
   
      constructTree();
      display();
      //System.out.println(tester);
   }
}//Must return length of path from start state (root node) to goal state
