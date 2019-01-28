/*
* Yousef Jarrar
* Home Work #1 -- Problem 6 or #2
* Dr. Tong Yu -- CSE 461
* Read_Write Threads
* */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Read and Writer Implementation for threads.

public class RWThread {

    final Lock mutex = new ReentrantLock();
    final Condition readerQueue = mutex.newCondition();
    final Condition writerQueue = mutex.newCondition();
    int reader_count = 0;
    int writer_count = 0;
    int activeWriter_count = 0;
    String INP_FILE = "counter.txt";
    public static Random random = new Random();


    //constructor

    public RWThread() {
        FileWriter f;
        try {
            f = new FileWriter(new File(INP_FILE));
            f.write(new Integer(0).toString());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Reader Implementation

    void reader() throws InterruptedException {
        mutex.lock();
        while (writer_count != 0) {
            readerQueue.await();
        }
        reader_count++;
        mutex.unlock();
        readFile(INP_FILE);
        mutex.lock();
        if (--reader_count == 0) {
            writerQueue.signal();
        }
        mutex.unlock();
    }

    //Writer Implementation

    void writer() throws InterruptedException {
        mutex.lock();
        writer_count++;
        while (!((reader_count == 0) && (activeWriter_count == 0))) {
            writerQueue.await();
        }
        activeWriter_count++;
        mutex.unlock();
        writeFile(INP_FILE);
        mutex.lock();
        activeWriter_count--;
        if (--writer_count == 0) {
            readerQueue.signalAll();
        } else {
            writerQueue.signal();
        }
        mutex.unlock();
    }


    //Read Count

    public void readFile(String path) {
        try {
            int inp = new Scanner(new FileInputStream(path)).nextInt();
            System.out.printf(Thread.currentThread().getName() + " Reader reading");
            System.out.printf(" Counter value: %d\n", inp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Write Count
    public void writeFile(String path) {
        int writerCount;
        try {
            writerCount = (int) new Scanner(new FileInputStream(path)).nextInt();
            writerCount++;
            FileWriter writr = new FileWriter(new File(path));
            writr.write(new Integer(writerCount).toString());
            writr.close();
            System.out.printf(Thread.currentThread().getName() + " Writer writing");
            System.out.printf(" Counter value: %d\n", writerCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}