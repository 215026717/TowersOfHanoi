import java.util.*;

public class Hanoi{
   List<Stack<Integer>> stack = new ArrayList<Stack<Integer>>();
   static Stack<Integer> A = new Stack<>();
   static Stack<Integer> B = new Stack<>();
   static Stack<Integer> C = new Stack<>();
   
   
   public static void startState(){
      A.push(3);
      A.push(2);
      A.push(1);
      int a = A.pop();
      System.out.println(a);
   }
   
   /*public static void searchBF(){
   
   }
   
   public static void searchDF(){
   
   }
   
   public static void displayPath(){
   
   }
   
   public static boolean goal(){
   
   }*/
   
   public static void main(String[] arg){
      startState();
      
   }

}
