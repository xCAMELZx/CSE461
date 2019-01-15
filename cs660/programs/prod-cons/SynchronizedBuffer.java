//in this example, the 'buffer' only holds one item
public class SynchronizedBuffer implements Buffer  
{
  private int buffer = -1;	//shared by producer and consumer
  private int occupiedBuffers = 0;	//counts occupied buffers

  public synchronized void set( int value )	//produce an item
  {
    String name = Thread.currentThread().getName();
    while ( occupiedBuffers == 1 ) {
	try {
	  System.err.println( name + " tries to write." );
	  displayState( "Buffer full. " + name + " waits." );
	  wait();
  	} catch ( InterruptedException e ) {
	  e.printStackTrace();
	}
    } //end while

    buffer = value;
    ++occupiedBuffers;
    displayState( name + " writes " + buffer + " \t"  );

    notifyAll();	//wake up other threads
  }  //end set()

  public synchronized int get()	//consume an item
  {
    String name = Thread.currentThread().getName();

    while ( occupiedBuffers == 0 ) {
  	try{
	  System.err.println( name + " tries to read." );
	  displayState( "Buffer empty. " + name + " waits."  );
	  wait();	//goto sleep
 	} catch ( InterruptedException e ) {
	  e.printStackTrace();
	}
    }  //while
    --occupiedBuffers;
    displayState( name + " reads " + buffer + " \t"  );
    notifyAll();
    return buffer;
  }  //get()
  public void displayState ( String str )
  {
    StringBuffer outputLine = new StringBuffer( str );
    outputLine.setLength( 40 );
    outputLine.append( buffer + "\t\t" + occupiedBuffers );
    System.err.println( outputLine );
    System.err.println();
  }
} //SynchronizedBuffer
