import java.util.Random;


public class sample {

    final static int READTCount = 20;// as given in homework
    final static int WRITETCount = 3;// as given in homework
    static ReadWrites ReadWrites = new ReadWrites();
    static Random random = new Random();

    static class readerThread extends Thread {
        @Override
        public void run() {
            System.out.println("Reader " + getName() + ": Started");
            while (true) {
                try {
                    ReadWrites.readerrun();
                    Thread.sleep(random.nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * static ReaderThread class extends thread class to continue in a loop with
     * random number of wait time between 0 - 3000 milliseconds
     */
    static class writerThread extends Thread {
        @Override
        public void run() {
            System.out.println("Writer " + getName() + ": Started");
            while (true) {
                try {
                    ReadWrites.writerrun();
                    Thread.sleep(random.nextInt(3000));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /*
     * Create threads with max numbers 20 and 3 for reader and writer respectively
     */
    public static void main(String[] args) {
        readerThread readerThreads[] = new readerThread[READTCount];
        writerThread writerThreads[] = new writerThread[WRITETCount];
        System.out.println("Program Start");
        for (int i = 0; i < WRITETCount; ++i) {
            writerThreads[i] = new writerThread();
            writerThreads[i].start();
        }
        for (int i = 0; i < READTCount; ++i) {
            readerThreads[i] = new readerThread();
            readerThreads[i].start();
        }
    }
}
