/*
* Class created to hold lock and condition
* */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWrites {
    private final String file = "counter.txt";
    private final Lock _mutex = new ReentrantLock();// create mutex instance
    private final Condition readerQueue = _mutex.newCondition();// Returns a new condition for
// reader that is bound to this Lock instance.
    private final Condition writerQueue = _mutex.newCondition();// Returns a new condition for writer that
// is bound to this Lock instance.

    private int readers_count = 0;// to store current readers count
    private int writers_count = 0;// to store current writers count
    public ReadWrites() {
        try {
            FileWriter filewriter = new FileWriter(new File(file));
            filewriter.write(new Integer(0).toString());
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void readerrun() throws InterruptedException {
        _mutex.lock(); // acquire lock
        while (!(writers_count == 0)) {
            readerQueue.await();
        }
        readers_count++;
        _mutex.unlock();
        read(file);//read file
        _mutex.lock();
        if (--readers_count == 0) {
            writerQueue.signal();//signal writers
        }
        _mutex.unlock();//remove lock
    }
    void writerrun() throws InterruptedException {
        _mutex.lock();
        while (!((readers_count == 0) && (writers_count == 0))) {
            writerQueue.await();// when reader and writer is zero wait in
// writerQueue
        }
        writers_count++; // increment writer
        _mutex.unlock();// remove lock
        write(file);// write to file
        _mutex.lock(); // acquire lock
        writers_count--; // only one writer at a time
        writerQueue.signal(); // signal writers
        readerQueue.signalAll(); // signal all readers
        _mutex.unlock();// remove lock
    }
    void read(String path) {
        try {
            Scanner reader = new Scanner(new FileInputStream(path));
            int x = reader.nextInt();
            System.out.printf(Thread.currentThread().getName() + " is reading ...");
            System.out.printf(" Counter value : %d\n", x);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    void write(String path) {
        int countw;
        try {
            Scanner reader = new Scanner(new FileInputStream(path));
            countw = (int) reader.nextInt();
            countw++;
            FileWriter f = new FileWriter(new File(path));
            f.write(new Integer(countw).toString());
            f.close();
            System.out.printf(Thread.currentThread().getName() + " Writing... ");
            System.out.printf(" Counter value : %d\n", countw);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
