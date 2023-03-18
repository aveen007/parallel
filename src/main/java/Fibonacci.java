import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;


/*
However, besides being a dumb way to compute Fibonacci functions (there is a simple fast linear algorithm that you'd use in practice),
 this is likely to perform poorly because the smallest subtasks are too small to be worthwhile splitting up.
 Instead, as is the case for nearly all fork/join applications,
 you'd pick some minimum granularity size (for example 10 here) for which you always sequentially solve rather than subdividing.
 */

public class Fibonacci extends RecursiveTask<Long> {
    final int n;
    private static final Map<Integer, Long> cache = new HashMap<>();
    public Fibonacci(int n) { this.n = n; }

    public Long compute() {
        Long cached = cache.get(n);
        if (cached != null) {
            return cached;
        }
        if(n > 20) {

            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            Long result = f2.compute() + f1.join();

            cache.put(n, result);

            return result;
        }else{
            return computeSeq();
        }
    }

    public Long computeSeq() {
        if (n <= 1)
            return (long)n;
        Long cached = cache.get(n);
        if (cached != null) {
            return cached;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        Fibonacci f2 = new Fibonacci(n - 2);
        Long result = f2.computeSeq() + f1.computeSeq();

        cache.put(n, result);

        return result;
    }

}
