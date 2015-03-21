package main.java;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenxiaoxue on 3/21/15.
 */
public class MapPractice {
    public static void main(String[] args){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("Tom",26);
        map.put("Jack",28);
        map.put("Mary",21);
        map.put("Candy",18);

        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
            String key =pair.getKey();
            int value = pair.getValue();
            System.out.println(key+","+value);
        }


    }

}
