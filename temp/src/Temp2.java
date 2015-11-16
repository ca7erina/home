
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by chenxiaoxue on 11/9/15.
 */
public class Temp2 {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        char words[] = myScanner.nextLine().toCharArray();
        Stack<Character> p = new Stack<Character>() ;

        int counter =0;
        int wordsLength = words.length;
        int half = wordsLength/2-1;

        for(int i =0;i<=half;i++){
            p.push(words[i]);
        }

        if(wordsLength%2==1){
            counter = half+2;
        }else{
            counter = half+1;
        }


        while(counter<words.length){

//            System.out.println(words[counter]);
//            System.out.println("try"+p.peek());
            if(p.pop()!=words[counter]){
                System.out.println(p.pop());
                System.out.println(words[counter]);
                System.out.println("FALSE");
                return;
            }

            counter++;
        }
        System.out.println("TRUE");



    }
}
