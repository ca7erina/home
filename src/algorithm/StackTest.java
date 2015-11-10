package algorithm;

import org.junit.Test;

/**
 * Created by chenxiaoxue on 11/10/15.
 */
public class StackTest {
    @Test
    public void test(){
        Stack theStack = new Stack(10);
        theStack.push(20);
        theStack.push(40);
        theStack.push(60);
        theStack.push(80);
        while(!theStack.isEmpty()){
            System.out.println(theStack.pop());

        }
    }
}
