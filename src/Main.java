public class Main {
    public static void main(String[] args) {
        int arrayLength = 100000000;
        int threadAmount = 4;
        long time = System.currentTimeMillis();

        FindMin findMin = new FindMin(arrayLength, threadAmount);
        long minIndex = findMin.OneThread(0, arrayLength);
        time = System.currentTimeMillis() - time;
        System.out.println(minIndex + " time:" + time);

        time = System.currentTimeMillis();
        minIndex = findMin.threadMin();
        time = System.currentTimeMillis() - time;
        System.out.println(minIndex + " time:" + time);
    }
}