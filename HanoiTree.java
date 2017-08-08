import java.util.*;
public class HanoiTree{
	class Node<E>{
		E A,B,C;
		Node<Stack> left,right,centre;
      
		public Node(Stack A,E B,E C){
			this.A = A;
         this.B = B;
         this.C = C;
         //B.push(2);
			//left = new Node(A,B,C);
         //C.push(B.pop());
			//right = new Node(A,B,C);
         left = null;
         right = null;
         centre = null;
         //A.push(C.pop());
		}
      
	}
   
   public void start(){
   
   }
   
   /*public boolean insert(List element){
      if (root == null){
         root = new TreeNode(element);
      }
   }*/
   
   public static void main(String[] arg){
      Stack<Integer> Ast = new Stack<>();
      Ast.push(3);
      Node<Stack<Integer>> A = new Node(Ast);
   }
}//Must return length of path from start state (root node) to goal state