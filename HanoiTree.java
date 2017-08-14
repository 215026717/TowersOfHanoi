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
   public static Stack<Integer> tempA = new Stack<>();
   public static Stack<Integer> tempB = new Stack<>();
   public static Stack<Integer> tempC = new Stack<>();

   public static boolean DF(Node<Stack<Integer>> X){
      if (X == null){
        System.out.println("null");
         return false;
      }
      else if (X.A.empty() && X.B.empty()){
          tester++;
          //System.out.println(X.C.peek());
         return true;
       }
      else{
        closed.push(X);
        one(X);
        //two(X);
        //tester++;
        if (X.right != null)
          open.push(X.right);
        if (X.left != null)
          open.push(X.left);
        if (!open.empty())
        return DF(open.pop());
        }
        System.out.println(tester);
        return false;
      }

   public static void one(Node<Stack<Integer>> root){
     if ((root.A.empty() && root.C.empty())){
       root.A.push(root.B.pop());
       root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
       root.B.push(root.A.pop());
       root.C.push(root.B.pop());
       root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
       root.B.push(root.C.pop());
     }
     else if(root.B.empty() && root.C.empty()){
       root.B.push(root.A.pop());
       root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
       root.A.push(root.B.pop());
       root.C.push(root.A.pop());
       root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
       root.A.push(root.C.pop());
     }
      else if (!root.A.empty()){
        if (root.A.peek() == 1){
         root.B.push(root.A.pop());
	 if (search(root,open) || search(root,closed))
		root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
         root.A.push(root.B.pop());
         root.C.push(root.A.pop());
         if (search(root,open) || search(root,closed))
            root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
         root.A.push(root.C.pop());
       }
       else{
         if (root.B.empty() || root.B.peek() > root.A.peek()){
                   root.B.push(root.A.pop());
                   root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
                   root.A.push(root.B.pop());
                }
                else if (root.C.empty() || root.C.peek() > root.A.peek()){
                   root.C.push(root.A.pop());
                   root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
                   root.A.push(root.C.pop());
                }
              }
      }

      else if (!root.B.empty()){
        if (root.B.peek() == 1){
         //one(root,B,A,C);
         root.A.push(root.B.pop());
         if (search(root,open) || search(root,closed))
            root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
         root.B.push(root.A.pop());
         root.C.push(root.B.pop());
         if (search(root,open) || search(root,closed))
            root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
         root.B.push(root.C.pop());
       }
       else{
         if (root.C.empty() || root.C.peek() > root.B.peek()){
            root.C.push(root.B.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.B.push(root.C.pop());
         }
         else if (root.A.empty() || root.A.peek() > root.B.peek()){
            root.A.push(root.B.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.B.push(root.A.pop());
         }
       }
      }

      else if (!(root.C.empty())){
         if(root.C.peek() == 1){
         root.B.push(root.C.pop());
         if (search(root,open) || search(root,closed))
            root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
         root.C.push(root.B.pop());
         root.A.push(root.C.pop());
         if (search(root,open) || search(root,closed))
            root.left = new Node(root.A.clone(),root.B.clone(),root.C.clone());
         root.C.push(root.A.pop());
       }
       else{
            if (root.A.empty() || root.A.peek() > root.C.peek()){
               root.A.push(root.C.pop());
               root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
               root.C.push(root.A.pop());
            }
            else if (root.B.empty() || root.B.peek() > root.C.peek()){
               root.B.push(root.C.pop());
               root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
               root.C.push(root.B.pop());
            }
       }
      }
   }

   public static void two(Node<Stack<Integer>> root){
      /*if (!(root.A.empty()) && root.A.peek() > 1){
         if (root.B.empty() || root.B.peek() > root.A.peek()){
            root.B.push(root.A.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.A.push(root.B.pop());
         }
         else if (root.C.empty() || root.C.peek() > root.A.peek()){
            root.C.push(root.A.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.A.push(root.C.pop());
         }
      }

      else if (!(root.B.empty()) && root.B.peek() > 1){
         if (root.C.empty() || root.C.peek() > root.B.peek()){
            root.C.push(root.B.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.B.push(root.C.pop());
         }
         else if (root.A.empty() || root.A.peek() > root.B.peek()){
            root.A.push(root.B.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.B.push(root.A.pop());
         }
      }

      else if (!(root.C.empty()) && root.C.peek() > 1){
         if (root.A.empty() || root.A.peek() > root.C.peek()){
            root.A.push(root.C.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.C.push(root.A.pop());
         }
         else if (root.B.empty() || root.B.peek() > root.C.peek()){
            root.B.push(root.C.pop());
            root.right = new Node(root.A.clone(),root.B.clone(),root.C.clone());
            root.C.push(root.B.pop());
         }
      }*/

   }

   public static boolean search(Node<Stack<Integer>> X,Stack<Node<Stack<Integer>>> OpClo){
      while (!OpClo.empty()){
	 temp.push(OpClo.pop());
	 //if (compareStacks(temp.peek().A,X.A) && compareStacks(temp.peek().B,X.B) && compareStacks(temp.peek().C,X.C)){
   if (temp.peek().A.equals(X.A) && temp.peek().B.equals(X.B) && temp.peek().C.equals(X.C)){
		//while(!temp.empty()){
			OpClo.push(temp.pop());
		//}
		return true;
         }
      }
      //while (!temp.empty()){
      OpClo.push(temp.pop());
      //}
      return false;
   }

   public static boolean compareStacks(Stack<Integer> A, Stack<Integer> B){
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
   }

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
      //open.push(root);
      if (DF(root))
        System.out.println("Goal");
      System.out.println(root.left.right.C.peek());

   }

   /*public boolean insert(List element){
      if (root == null){
         root = new TreeNode(element);
      }
   }*/

   public static void main(String[] arg){
      //if (tempB.equals(temp))

      constructTree();
      //System.out.println(tester);
   }
}//Must return length of path from start state (root node) to goal state
