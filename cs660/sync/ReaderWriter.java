import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReaderWriter
{
  final Lock mutex = new ReentrantLock();
  final Condition readerQueue=mutex.newCondition();  //cond variable
  final Condition writerQueue=mutex.newCondition();  //cond variable
  int   nReaders = 0; //number of reader threads
  int   nWriters = 0; //number of writer threads (0 or 1)

  void reader() throws InterruptedException
  {
    mutex.lock();         //mutual exclusion
    while ( !(nWriters == 0) )
      readerQueue.await();//wait in readerQueue till no more writers
    nReaders++;           //one more reader
    mutex.unlock();
    //read
    //........
    //finished reading
    mutex.lock();         //need mutual exclusion
    if ( --nReaders == 0 )
      writerQueue.signal();//wake up a waiting writer
    mutex.unlock();
  }

  void writer() throws InterruptedException
  {
    mutex.lock();
    while ( !((nReaders == 0) && (nWriters == 0)) )
      writerQueue.await();//wait in writerQueue
                          // until no more writer & readers
    nWriters++; //one writer
    mutex.unlock();
    //write
    //........
    //finished writing
    mutex.lock(); //need mutual exclusion
    nWriters--; //only one writer at a time
    writerQueue.signal(); //wake up a waiting writer
    readerQueue.signalAll(); //wake up all waiting readers
    mutex.unlock();
  }
}
