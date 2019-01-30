/*
 * Yousef Jarrar
 * Home Work #1 -- Problem 6 or #2
 * Dr. Tong Yu -- CSE 461
 * Read_Write Threads
 * Main Program
 * output.java
 * */


import java.util.Random;

//Start of running program.

public class sample {

    public final static int READ_MAX = 20; //maximum number of readers
    public final static int WRITE_MAX = 3; //maximum number of writers
    public static RWThread readerWriterthread = new RWThread(); //instantiate RW_Thread
    public static Random random = new Random(); //create random variable
    static class readerThread extends Thread {
        public void run() {
            System.out.println("Reader :" + getName() + ": Start");
            while (true) {
                try {
                    readerWriterthread.reader();
                    int time = random.nextInt(3000);
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class writerThread extends Thread {
        public void run() {
            System.out.print("Writer :" + getName() + ": Start");
            while (true) {
                try {
                    readerWriterthread.writer();
                    Thread.sleep(random.nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        readerThread readerThreads[] = new readerThread[READ_MAX];
        writerThread writerThreads[] = new writerThread[WRITE_MAX];
        System.out.println("start");
        for (int i = 0; i < WRITE_MAX; ++i) {
            writerThreads[i] = new writerThread();
            writerThreads[i].start();
        }
        for (int i = 0; i < READ_MAX; ++i) {
            readerThreads[i] = new readerThread();
            readerThreads[i].start();
        }
    }
}
