package designPattern.singleton;

/**
 * Created by chenxiaoxue on 7/25/16.
 */
public class Singleton{
    private static Singleton uniqueInstance = new Singleton(); //make sure thread safe

    private Singleton() { }

    public static Singleton getInsance(){
        if(uniqueInstance ==null){
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;

    }

}


//public class Singleton{
//    private static Singleton uniqueInstance;
//
//    private Singleton() { }
//
//    public static synchronized Singleton getInsance(){ //synchronized is redundent most time; cause only instant once
//        if(uniqueInstance ==null){
//            uniqueInstance = new Singleton();
//        }
//        return uniqueInstance;
//
//    }
//
//}

//public class Singleton{
//    private volatile static Singleton uniqueInstance;
//    private Singleton() { }
//    public static Singleton getInsance(){
//        if(uniqueInstance ==null){
//            synchronized( Singleton.class){
//
//                if(uniqueInstance ==null){
//                    uniqueInstance = new Singleton();
//                }
//            }
//        }
//        return uniqueInstance;
//
//    }
//
//}


