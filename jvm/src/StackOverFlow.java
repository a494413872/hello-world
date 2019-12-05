/**
 * Created by songjian on 5/7/2018.
 */
public class StackOverFlow {
private static int counter=0;
    public void count(){
        System.out.println("the stack frame depth is :"+counter++);
        count();
    }
    public static void main(String[] args) {
            StackOverFlow stackOverFlow= new StackOverFlow();
            stackOverFlow.count();
    }
}
