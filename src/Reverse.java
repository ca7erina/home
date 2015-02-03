import java.util.Arrays;
import java.util.List;

/**
 * Reverse String.
 */
public class Reverse {

    public static void main(String args[]) {
//        String str1= "Happy New Year!";
        String str2 = "123 456 789";
        System.out.println(str2);
        System.out.println(ReverseList(str2));
        System.out.println(str2);
        System.out.println(ReverseArray(str2));
        System.out.println(str2);
        System.out.println(StringBufferReverse(str2));

    }


    public static String StringBufferReverse(String strstr) {
        StringBuffer sb = new StringBuffer(strstr);
        sb.reverse();
        return sb.toString();
    }


    public static String ReverseArray(String strstr) {
        String[] str = strstr.split("");
        for(int i = 0; i < str.length / 2; i++) {
            String temp;
            temp = str[i];
            str[i] = str[str.length - 1 - i];
            str[str.length - 1 - i] = temp;
        }

        String result = "";
        for(String s : str) {
            result += s;
        }
        return result;

    }

    public static String ReverseList(String strstr) {
        List<String> str = Arrays.asList(strstr.split(""));
        for(int i = 0; i < str.size() / 2; i++) {
            String temp;
            temp = str.get(i);
            str.set(i, str.get(str.size() - 1 - i));
            str.set(str.size() - i - 1, temp);
        }

        String result = "";
        for(String s : str) {
            result += s;
        }
        return result;
    }


}
