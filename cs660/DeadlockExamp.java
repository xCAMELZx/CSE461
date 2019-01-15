/**
 * DeadlockExamp.java
 * by T.L. Yu, adopted from the DeadlockExample.java of Silberschatz
 */
 
class Mutex
{
  public String mname;
  public Mutex( String name )
  {
    mname = name;
  }

}

class Athread extends Thread
{
   private Mutex first, second;
   private String tname;
   
   public Athread(Mutex f, Mutex s, String name ) {
      first = f;
      second = s;
      tname = name;
   }
   
   public void run() {
      synchronized (first) {
         // do something

	try { 
		Thread.sleep( ((int)(3*Math.random()))*1000);
	}
	catch (InterruptedException e) {}

         System.out.println( tname + " thread got mutex " + first.mname );
         synchronized (second) {
            // do something
            System.out.println( tname + " thread got mutex " + second.mname );
            
         }
      }
   }
}


public class DeadlockExamp
{  
   public static void main(String arg[]) {         
      Mutex mutexX = new Mutex( "mutexX" );
      Mutex mutexY = new Mutex( "mutexY" );
      
      Athread A = new Athread(mutexX,mutexY, "A");
      Athread B = new Athread(mutexY,mutexX, "B");
      
      A.start();
      B.start();
   }
}
