import java.util.*;
import java.io.*;
import java.lang.*;
public class HanoiTree{
   static class Node<E>{
      E A,B,C;
      Node<E> left,right,centre;
      
      public Node(E A, E B, E C){
         this.A = A;
         this.B = B;
         this.C = C;
         left = null;
         right = null;
         centre = null;
      }
   }
   public static boolean switcher;
   
   public static boolean children(Node<Stack<Integer>> root){
      if (root == null){
         return false;
      }
      Stack<Integer> A = root.A;
      Stack<Integer> B = root.B;
      Stack<Integer> C = root.C;
      //one(root,A,B,C);
      //two(root,A,B,C);
      if (!root.C.empty())
         System.out.println(root.C.peek());
      
      switcher = !switcher;
      one(root,A,B,C);
      two(root,A,B,C);
      if (root.A.empty() && root.B.empty() || root.C.empty() && root.A.empty()){
         return true;
      }
      else if (switcher){
         children(root.left);
         //children(root.right);
         //children(root.centre);
         return false;
      }
      else{
         children(root.centre);
         children(root.right);
         //children(root.left);
         return false;
      }
   }
   
   public static void one(Node<Stack<Integer>> root, Stack<Integer> A, Stack<Integer> B, Stack<Integer> C){
      if (!(A.empty()) && A.peek() == 1){
         //one(root,A,B,C);
         B.push(A.pop());
         root.left = new Node(A.clone(),B.clone(),C.clone());
         A.push(B.pop());
         C.push(A.pop());
         root.right = new Node(A.clone(),B.clone(),C.clone());
         A.push(C.pop());
      }
      else if (!(B.empty()) && B.peek() == 1){
         //one(root,B,A,C);
         A.push(B.pop());
         root.left = new Node(A.clone(),B.clone(),C.clone());
         B.push(A.pop());
         C.push(B.pop());
         root.right = new Node(A.clone(),B.clone(),C.clone());
         B.push(C.pop());
      }
      else if (!(C.empty()) && C.peek() == 1){
         //one(root,C,A,B);
         B.push(C.pop());
         root.left = new Node(A.clone(),B.clone(),C.clone());
         C.push(B.pop());
         A.push(C.pop());
         root.right = new Node(A.clone(),B.clone(),C.clone());
         C.push(A.pop());
      }

   }
   
   public static void two(Node<Stack<Integer>> root, Stack<Integer> A, Stack<Integer> B, Stack<Integer> C){
      if (!(A.empty()) && A.peek() > 1){
         if (B.empty() || B.peek() > A.peek()){
            B.push(A.pop());
            root.centre = new Node(A.clone(),B.clone(),C.clone());
            A.push(B.pop());
         }
         else if (C.empty() || C.peek() > A.peek()){
            C.push(A.pop());
            root.centre = new Node(A.clone(),B.clone(),C.clone());
            A.push(C.pop());
         }
      }
      
      else if (!(B.empty()) && B.peek() > 1){
         if (C.empty() || C.peek() > B.peek()){
            C.push(B.pop());
            root.centre = new Node(A.clone(),B.clone(),C.clone());
            B.push(C.pop());
         }
         else if (A.empty() || A.peek() > B.peek()){
            A.push(B.pop());
            root.centre = new Node(A.clone(),B.clone(),C.clone());
            B.push(A.pop());
         }
      }
      
      else if (!(C.empty()) && C.peek() > 1){
         if (A.empty() || A.peek() > C.peek()){
            A.push(C.pop());
            root.centre = new Node(A.clone(),B.clone(),C.clone());
            C.push(A.pop());
         }
         else if (B.empty() || B.peek() > C.peek()){
            B.push(C.pop());
            root.centre = new Node(A.clone(),B.clone(),C.clone());
            C.push(B.pop());
         }
      }
      
   }

   public static void constructTree(){
      //Node<Stack<Integer>> root = new Node(
      //root.A = new Stack<>();
      Stack<Integer> A = new Stack<>();
      Stack<Integer> B = new Stack<>();
      Stack<Integer> C = new Stack<>();
      A.push(3);
      A.push(2);
      A.push(1);
      Node<Stack<Integer>> root = new Node(A,new Stack<>(),new Stack<>());
      children(root);
      System.out.println(root.left.A.peek());
      /*B.push(A.pop());
      root.left = new Node(A.clone(),B.clone(),new Stack<>());
      root.right = new Node(A.clone(),new Stack<>(),B.clone());
      while (!(root.A.empty() && root.B.empty())){
         
      
      }*/
   }
   
   
   
   /*public boolean insert(List element){
      if (root == null){
         root = new TreeNode(element);
      }
   }*/
   
   public static void main(String[] arg){
      constructTree();
   }
}//Must return length of path from start state (root node) to goal state
