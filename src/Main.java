public class Main {
    public static void main(String[] args) {
        int arrayLength = 100000000;
        int threadAmount = 4;
        long time = System.currentTimeMillis();

        FindMin findMin = new FindMin(arrayLength, threadAmount);
        long[] minArr = findMin.OneThread(0, arrayLength);
        time = System.currentTimeMillis() - time;
        System.out.println("Min Index: " + minArr[0] + " - Min Value: " + minArr[1] + " time:" + time);

        time = System.currentTimeMillis();
        minArr = findMin.threadMin();
        time = System.currentTimeMillis() - time;
        System.out.println("Min Index: " + minArr[0] + " - Min Value: " + minArr[1] + " time:" + time);
    }
}