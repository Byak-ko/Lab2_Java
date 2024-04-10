import java.util.Random;
public class FindMin {
    private final int arrayLength;
    private final int threadNum;

    public final int[] array;


    public FindMin(int arrayLength, int threadAmount) {
        this.arrayLength = arrayLength;
        array = new int[arrayLength];
        this.threadNum = threadAmount;
        for(int i = 0; i < arrayLength; i++){
            array[i] = i;
        }
        Random random = new Random();
        array[random.nextInt(arrayLength)]*=-1;
    }

    public long OneThread(int startIndex, int finishIndex){
        long min =Long.MAX_VALUE;
        for(int i = startIndex; i < finishIndex; i++){
            if(min> array[i]){
                min= array[i];
            }
        }
        return min;
    }

    private long min = 0;

    synchronized private long getMin() {//crit w
        while (getThreadCount()<threadNum){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return min;
    }

    synchronized public void collectMin(long min){
        if(this.min>min){
            this.min = min;
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

    public long threadMin(){
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int len = arrayLength / threadNum;
        for (int i = 0; i < threadNum - 1; i++) {
            threadMins[i] = new ThreadMin(len * i, len * (i + 1), this);
            threadMins[i].start();
        }
        threadMins[threadNum-1]= new ThreadMin(len*(threadNum-1), arrayLength, this);
        threadMins[threadNum-1].start();
        return getMin();
    }
}
