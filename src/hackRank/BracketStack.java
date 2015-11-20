import java.util.Scanner;
import java.util.Stack;

public class BracketStack {
 public static void main(String []argh)
	   {
	      Scanner sc = new Scanner(System.in);
	      
	      while (sc.hasNext()) {
	    	 boolean getResult =false;
	    	 String input=sc.nextLine().trim();
	         Stack<Character> myStack = new Stack<Character>();
	         for(int i =0; i<input.length();i++){
	             if(input.charAt(i)=='{'||input.charAt(i)=='('||input.charAt(i)=='['){
	                 myStack.push(input.charAt(i));
	             }
	             if(input.charAt(i)=='}'){
	                 if(myStack.isEmpty()||myStack.pop()!='{'){
	                      System.out.println("false");
	                      getResult=true;
	                    break;
	                 }
	             }
	             if(input.charAt(i)==']'){
	                 if(myStack.isEmpty()||myStack.pop()!='['){
	                      System.out.println("false");
	                      getResult=true;
	                    break;
	                 }
	             }
	             if(input.charAt(i)==')'){
	                   if(myStack.isEmpty()||myStack.pop()!='('){
	                      System.out.println("false");
	                      getResult=true;
	                     break;
	                 }
	             }
	         }
	         if (getResult==false){
	        	 if (myStack.isEmpty()){
		             System.out.println("true");
		         }else{
		              System.out.println("false");
		         }
	         }
	         
	        
	          
	          
	      }
	      
	   }
}
