import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ArrayOccurrence extends RecursiveAction {
    public long sum=0;
    int key;
    int arr[], lo, hi;

    public ArrayOccurrence(int[] arr, int lo, int hi, int key) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.key=key;
    }

    public long computeSeq() {

        for (int i = lo; i <= hi; ++i) {

            if (arr[i]==key){
                sum += 1;

            }

        }
        return sum;
    }

    @Override
    protected void compute() {
        if (hi - lo > 1_000_000) {
            int mid = (lo + hi) / 2;
            ArrayOccurrence leftTask = new ArrayOccurrence(arr, lo, mid,key);
            ArrayOccurrence rightTask = new ArrayOccurrence(arr, mid, hi,key);
            leftTask.fork();
            rightTask.compute();
            leftTask.join();
            sum+= rightTask.sum + leftTask.sum;
        } else {
//            sum =computeSeq();
            for (int i = lo; i <= hi; ++i) {
                if (arr[i]==key){
                    sum += 1;

                }
            }
        }

    }

    public void computeParallelStream() {
        sum = Arrays.stream(arr).asLongStream().parallel().filter(x->x==key).count();

    }
    public void computeSeqStream() {
        sum = Arrays.stream(arr).asLongStream().filter(x->x==key).count();

    }
}
