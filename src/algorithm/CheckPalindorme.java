package algorithm;

/**
 * Created by chenxiaoxue on 12/2/15.
 */
public class CheckPalindorme {

    public static void main(String args[]){
        System.out.println(palindrome("abb"));
    }

    public static boolean palindrome(String words){
        if(words.length()<2){
            return true;
        }
        if(words.charAt(0)==words.charAt(words.length()-1)){
            return palindrome(words.substring(1,words.length()-1));
        }else{
            return false;
        }


    }

    public static String check(String mystring){
        if(mystring.length()<2){
            return "TRUE";
        }

        if(mystring.charAt(0)!=mystring.charAt(mystring.length()-1)){
            return "FALSE";
        }
        String substring = mystring.substring(1,mystring.length()-1);
        return(check(substring));
    }

}
