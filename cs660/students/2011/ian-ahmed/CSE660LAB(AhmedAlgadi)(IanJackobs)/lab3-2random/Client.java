/*
 * Copyright &#169; 2004, 2010 Oracle and/or its affiliates. All  Rights Reserved.
 *  
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 *  notice, this list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 *  
 * Neither the name of Oracle and/or its affiliates. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. Oracle and/or its affiliates. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *  
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility.
 */
package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Client {

    private Client() {}

    public static void main(String[] args) {

	String host = (args.length < 1) ? null : args[0];
	String N, Min, Max, Nanswer;
	N = "10";
	Min = "20";
	Max = "100";
	Nanswer = "4";
	BufferedReader object = new BufferedReader(
  		new InputStreamReader(System.in));
	try{
		System.out.println("This is a random number generator. There are two options. One requests the number of numbers");
 		System.out.println("to generate.  The other asks the number of numbers to generate plus the Min and Max numbers to"); 
		System.out.println("include in the random numbers. ");
		System.out.println("Press 1 to generate N number of random numbers");
		System.out.println("Press 2 to generate N number of random numbers with Min and Max numbers.");
		System.out.println("Press 3 to exit");
  		Nanswer = object.readLine();
  		//System.out.println("Random Numbers to generate: ");
  		//System.out.println(" "+ N);
	} catch (IOException e){
  			System.out.println("Out of range! " );
			System.exit(1);
	}
	
	int answer = Integer.parseInt(Nanswer);

	if (answer == 1){
		try{
  			System.out.println("Enter number of Random numbers");
  			N = object.readLine();
  			System.out.println("Random Numbers to generate: " + N);
  		
		} catch (IOException e){
  			System.out.println("Out of range! " );
			System.exit(1);
		}
		Min = "0";
		Max = "100";
	
		try {
			System.out.println("ip= " + host);
			Registry registry = LocateRegistry.getRegistry(host);
	    		Hello stub = (Hello) registry.lookup("Hello");
	    		String response = stub.sayHello(N,Min,Max);
	    		System.out.println("response: " + response);
		} catch (Exception e) {
	    		System.err.println("Client exception: " + e.toString());
	   		 e.printStackTrace();
		}

// use min of 0 and max of 100

	}
	else if (answer == 2){
// use inputed min and max
	
		try{
  			System.out.println("Enter number of Random numbers");
  			N = object.readLine();
  			System.out.println("Random Numbers to generate: " + N);
  		
		} catch (IOException e){
  			System.out.println("Out of range! " );
			System.exit(1);
		}
	
		try{
  			System.out.println("Enter number of Minimum number");
  			Min = object.readLine();
  		
  			System.out.println(" "+ Min);
		} catch (IOException e){
  			System.out.println("Out of range! " );
			System.exit(1);
		}
		try{
  			System.out.println("Enter number of Maximum number");
  			Max = object.readLine();
  		
  			System.out.println(" "+ Max);
		} catch (IOException e){
  			System.out.println("Out of range! " );
			System.exit(1);
		}
		try {
	    		Registry registry = LocateRegistry.getRegistry(host);
	    		Hello stub = (Hello) registry.lookup("Hello");
	    		String response = stub.sayHello(N,Min,Max);
	    		System.out.println("response: " + response);
		} catch (Exception e) {
	    		System.err.println("Client exception: " + e.toString());
	    		e.printStackTrace();
		}



	}//elseif = 2
	else if (answer == 3) {
		System.out.println(" Now exiting: ");
	//exit
	}
	else {
		System.out.println(" Choice selected was not 1 or 2 or 3. Please try again: ");
  		System.out.println(" "+ Nanswer);
	}
    }
}

 
