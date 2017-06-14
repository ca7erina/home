package main.java;

/**
 * after return also execute finally ..
 */
public class TryCatch {
    public static void main(String[] args){
        System.out.println(ifReturn());

    }

    public static int ifReturn(){
        try{
            System.out.println("try ..");

            return 1;

        }catch(Exception e){
            e.printStackTrace();

        }finally{
            System.out.println("finally executed ..");
        }
        return 0;
    }


}
