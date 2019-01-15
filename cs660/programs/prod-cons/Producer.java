public class Producer extends Thread
{
  private Buffer sharedLocation;
  private int nTimes = 5;

  public Producer( Buffer shared )
  {
    super ( "Producer" );
    sharedLocation = shared;
  }

  public void run()
  {
    for ( int i = 1; i <= nTimes; i++ ) 
    {
	//sleep 0 to 3 seconds, then place value in Buffer
	try {
	  Thread.sleep( (int) ( Math.random() * 3001 ) );
	  sharedLocation.set ( i );		//write to buffer
	} catch ( InterruptedException e ) {
	  e.printStackTrace();
	}
    }//for
    System.err.println( getName() + "done producing." + 
	"\nTerminating " + getName() + "." );
  }//end run
} //end class Producer
