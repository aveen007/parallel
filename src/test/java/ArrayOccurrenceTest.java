import junit.framework.TestCase;


import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ArrayOccurrenceTest extends TestCase {


    private int[] randomArray(int size) {
        Random rand = new Random();
        int lowerBound=1;
        int upperBound=100_000;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(upperBound+1)+lowerBound;
        }

         //Check  random array
    //    Arrays.stream(arr).forEach(System.out::println);
        return arr;
    }
//    public void  testRandomArray(){
//        int size=10;
//
//        int arr[]=randomArray(size);
//        Arrays.stream(arr).forEach(System.out::println);
//
//    }
//    private int[] intStream(int size) {
//        IntStream intStream = IntStream.range(1, size);
//        Arrays.stream(intStream.toArray()).forEach(System.out::println);
//        return intStream.toArray();
//    }

    public void testArrayOccSeq() {

        int size = 1000_000_00;
        int[] arr = randomArray(size);
           // int [] arr2={2,5,3,4,5,5};
        ArrayOccurrence array = new ArrayOccurrence(arr, 0, arr.length - 1, 5);
        long start = System.currentTimeMillis();
        long sum = array.computeSeq();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Time execution for Random Array of size %d is %d ms, number of occurrences is %d\n", size, endTimer, sum);
//        assertEquals(15,res);
    }

    public void testArrayOccPP() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 1000_000_00;
        int[] arr = randomArray(size);
       // int [] arr={2,5,3,4,5,5};
        ArrayOccurrence array = new ArrayOccurrence(arr, 0, arr.length - 1,5);
        long start = System.currentTimeMillis();
        ForkJoinPool.commonPool().invoke(array);
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Time execution for Random Array of size %d is %d ms number of occurrences is %d\n", size, endTimer, array.sum);
//        assertEquals(15,res);
    }

    public void testArrayOccParallelStream() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 1000_000_00;
        int[] arr = randomArray(size);
        // int [] arr={2,5,3,4,5,5};

        ArrayOccurrence array = new ArrayOccurrence(arr, 0, arr.length - 1,5);
        long start = System.currentTimeMillis();
        array.computeParallelStream();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Stream Time execution for Random Array of size %d is %d ms number of occurrences is %d\n", size, endTimer, array.sum);
//        assertEquals(15,res);
    }
    public void testArrayOccSeqStream() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 1000_000_00;
        int[] arr = randomArray(size);
        // int [] arr={2,5,3,4,5,5};

        ArrayOccurrence array = new ArrayOccurrence(arr, 0, arr.length - 1,5);
        long start = System.currentTimeMillis();
        array.computeSeqStream();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Stream Time execution for Random Array of size %d is %d ms number of occurrences is %d\n", size, endTimer, array.sum);
//        assertEquals(15,res);
    }
//    public void resource() {
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println(ForkJoinPool.commonPool().getParallelism());
//    }

}
