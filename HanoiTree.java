import java.util.*;
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
   
   public static void children(Node<Stack<Integer>> root){
      Stack<Integer> A = 
      if (root.A.peek() == 1){
         
      }
   }

   public static void constructTree(){
      Stack<Integer> A = new Stack<>();
      Stack<Integer> B = new Stack<>();
      Stack<Integer> C = new Stack<>();
      A.push(3);
      A.push(2);
      A.push(1);
      Node<Stack<Integer>> root = new Node(A.clone(),new Stack<>(),new Stack<>());
      B.push(A.pop());
      root.left = new Node(A.clone(),B.clone(),new Stack<>());
      root.right = new Node(A.clone(),new Stack<>(),B.clone());
      while (!(root.A.empty() && root.B.empty())){
         
      
      }
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
