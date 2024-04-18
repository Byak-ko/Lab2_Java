public class ThreadMin extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final FindMin findMin;

    public ThreadMin(int startIndex, int finishIndex, FindMin findMin) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.findMin = findMin;
    }

    @Override
    public void run() {
        long[] minArr = findMin.OneThread(startIndex, finishIndex);
        findMin.collectMin(minArr);
        findMin.incThreadCount();
    }
}