import java.util.Random;
public class FindMin {
    private final int arrayLength;
    private final int threadNum;

    public final int[] array;
    private long minArr[] = {0, 0};


    public FindMin(int arrayLength, int threadAmount) {
        this.arrayLength = arrayLength;
        array = new int[arrayLength];
        this.threadNum = threadAmount;
        for(int i = 0; i < arrayLength; i++){
            array[i] = i;
        }
        Random random = new Random();
        array[random.nextInt(arrayLength)]=-1;
    }

    public long[] OneThread(int startIndex, int finishIndex){
        long min =Long.MAX_VALUE;
        for(int i = startIndex; i < finishIndex; i++){
            if(min> array[i]){
                min= array[i];
                minArr[0]=i;
                minArr[1]=min;
            }
        }
        return minArr;
    }

    synchronized private long[] getMin() {//crit w
        while (getThreadCount()<threadNum){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return minArr;
    }

    synchronized public void collectMin(long[] minArr){
        if (this.minArr[1] > minArr[1]){
            this.minArr[1] = minArr[1];
            this.minArr[0] = minArr[0];
        }
    }

    private int threadCount = 0;
    synchronized public void incThreadCount(){
        threadCount++;
        notify();
    }

    private int getThreadCount() {
        return threadCount;
    }

    public long[] threadMin(){
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int len = arrayLength / threadNum;
        for (int i = 0; i < threadNum - 1; i++) {
            threadMins[i] = new ThreadMin(len * i, len * (i + 1), this);
            threadMins[i].start();
        }
        threadMins[threadNum-1]= new ThreadMin(len*(threadNum-1), arrayLength, this);
        threadMins[threadNum-1].start();
        minArr = getMin();
        return minArr;
    }
}