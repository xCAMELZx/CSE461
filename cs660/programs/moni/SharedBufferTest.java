//SharedBuffetTest creates producer and consumer threads

public class SharedBufferTest
{
  public static void main( String [] args )
  {
    SynchronizedBuffer sharedLocation = new SynchronizedBuffer();

    StringBuffer colHeads = new StringBuffer( "Operation" );
    colHeads.setLength( 40 );
    colHeads.append( "Buffer\t\tOcuppied Count");
    System.err.println( colHeads );
    System.err.println();
    sharedLocation.displayState( "Initial State" );

    Producer p = new Producer( sharedLocation );
    Consumer c = new Consumer( sharedLocation );
    p.start();		//start producer thread
    c.start();		//start consumer thread
  } //main
}//SharedBufferTest
