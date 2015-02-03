/**
 * Unlike StringBuilder, StringBuffer is synchronized that is StringBuffer is thread safe .
 */
//1. String 类
//
//        　　String的值是不可变的，这就导致每次对String的操作都会生成新的String对象，不仅效率低下，而且大量浪费有限的内存空间。
//        String a = "a"; //假设a指向地址0x0001
//        a = "b";//重新赋值后a指向地址0x0002，但0x0001地址中保存的"a"依旧存在，但已经不再是a所指向的,a 已经指向了其它地址。
//        因此String的操作都是改变赋值地址而不是改变值操作。
//
//        2. StringBuffer是可变类，和线程安全的字符串操作类，任何对它指向的字符串的操作都不会产生新的对象。 每个StringBuffer对象都有一定的缓冲区容量，当字符串大小没有超过容量时，不会分配新的容量，当字符串大小超过容量时，会自动增加容量。
//
//        StringBuffer buf=new StringBuffer(); //分配长16字节的字符缓冲区
//        StringBuffer buf=new StringBuffer(512); //分配长512字节的字符缓冲区
//        StringBuffer buf=new StringBuffer("this is a test")//在缓冲区中存放了字符串，并在后面预留了16字节的空缓冲区。
//
//        3.StringBuffer
//        　　StringBuffer和StringBuilder类功能基本相似，主要区别在于StringBuffer类的方法是多线程、安全的，而StringBuilder不是线程安全的，相比而言，StringBuilder类会略微快一点。对于经常要改变值的字符串应该使用StringBuffer和StringBuilder类。
//
//        4.线程安全
//        StringBuffer 线程安全
//        StringBuilder 线程不安全
//
//        5.速度
//        一般情况下,速度从快到慢:StringBuilder>StringBuffer>String,这种比较是相对的，不是绝对的。
//
//        6.总结
//        （1）.如果要操作少量的数据用 = String
//        （2）.单线程操作字符串缓冲区 下操作大量数据 = StringBuilder
//        （3）.多线程操作字符串缓冲区 下操作大量数据 = StringBuffer
public class StringBufferPractice {

    public static void main(String[] args) {
        StringBuilder buf = new StringBuilder();//buf is a char[16]
        buf.append("Professor Tom ").append("always says ").
                append("his ex-wife is extremely pretty .").
                insert(0, "The master ").
                delete(11, 21).
                replace(22, 26, "mentions");
        String str = buf.toString();
        System.out.println(str);
        System.out.println(buf.capacity());
        System.out.println(buf.length());

    }
}
