package algorithm.recursion;

/**
 *For example, if the user types east, the program should list all 24 permutations, including eats, etas, teas, and non-words like tsae.
 * 1234
 * 1 234 1 243
 * 1 324 1 342
 * 1 423 1 432
 */
public class Anagrams {


    public static void main(String[] args) {
      String words = "abcd";
        printAnagrams("",words);
    }

    public static void printAnagrams(String prefix, String words){
        if(words.length()<=1) {
            System.out.println(prefix + words);
            return;
        }
            for(int i=0;i<words.length();i++){
                String target = words.substring(i,i+1);
                String after = words.substring(i+1,words.length());
                String before = words.substring(0, i);
                System.out.println("i:"+i+" prefix:"+prefix+" target:"+target+" before:"+before+" after:"+after+" words:"+words);

                printAnagrams(prefix + target, before + after);

            }


    }
}
