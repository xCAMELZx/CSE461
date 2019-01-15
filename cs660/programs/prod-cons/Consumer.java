public class Consumer extends Thread
{
  private Buffer sharedLocation;
  private int nTimes = 5;		//number of times to run a loop

  public Consumer( Buffer shared )
  {
    super( "Consumer" );
    sharedLocation = shared;
  }

  public void run()
  {
    int sum = 0;

    for ( int i = 1; i <= nTimes; i++ ) 
    {
	//sleep 0 to 3 seconds, then reads value from Buffer
	try {
	  Thread.sleep( (int) ( Math.random() * 3001 ) );
	  sum += sharedLocation.get();		//read from buffer
	} catch ( InterruptedException e ) {
	  e.printStackTrace();
	}
    }//for
    System.err.println( getName() + " read values totaling:" + sum + 
	"\nTerminating " + getName() + "." );
  } //end run
} //end Consumer
