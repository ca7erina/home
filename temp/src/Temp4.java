import java.util.regex.Pattern;

/**
 * Created by chenxiaoxue on 1/10/16.
 */
public class Temp4 {


    public static void main(String args[]){
        String str1 = "(c) Sketch a swim lane activity diagram for the followi";
        Pattern p = Pattern.compile("\\(c.*");
        System.out.println(p.matcher(str1).matches());
}

}
