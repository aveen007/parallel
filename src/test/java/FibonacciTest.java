import junit.framework.TestCase;


public class FibonacciTest extends TestCase {

    int n=50;

    public void testFiboPP(){
        long start = System.nanoTime();
        Fibonacci fib = new Fibonacci(n);
        long res = fib.compute();
        long end = System.nanoTime()-start;
        System.out.printf("Fibonacci for %d is %d, and parallel execution took %d nano seconds\n",n,res,end);
//        Fibonacci parallel takes 144269 fon n = 50 before enhancement 1
//        Fibonacci parallel takes 0.0113 ms fon n = 50 after enhancement 1
    }

    public void testFiboSeq(){
        long start = System.nanoTime();
        Fibonacci fib = new Fibonacci(n);
        long res = fib.computeSeq();
        long end = System.nanoTime()-start;
        System.out.printf("Fibonacci for %d is %d, and sequential execution took %d nano seconds\n",n,res,end);
        //Fibonacci Sequential takes 86495 fon n = 50 before enhancement 1
        //Fibonacci Sequential takes 1.833 ms fon n = 50 before enhancement 1
    }
}
