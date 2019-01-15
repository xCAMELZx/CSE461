scpackage example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Client {

    private Client() {}

    public static void main(String[] args) {
boolean repeat=false;

String max = "100";
String min = "0";
System.out.print("CSE 660 - Lab 3 - Part 2 - Spring 2011");
System.out.print("\nJava RMI - Random Number");
System.out.print("\nBy : Erwin Soekianto - 003657224");
do{
       
	System.out.print("\nHow many random no do you need? and press Enter: ");
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String n = null;
       try {
         n = br.readLine();
       } catch (IOException e) {
         System.out.println("Only enter number. Not String.");
         System.exit(1);
       }
System.out.print("\nDo you want to set up the upper and lower bound of the number(y/n)? ");
       //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String n2 = null;
       try {
         n2 = br.readLine();
       } catch (IOException e) {
         System.out.println("Only enter number. Not String.");
         System.exit(1);
       }


if (n2.equals("y") || n2.equals("yes") ){
System.out.print("\nWhat is the upper limit? and press Enter: ");
       //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       //String n = null;
       try {
         max = br.readLine();
       } catch (IOException e) {
         System.out.println("Only enter number. Not String.");
         System.exit(1);
       }

System.out.print("\nWhat is the lower limit? and press Enter: ");
       //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       //String n = null;
       try {
         min = br.readLine();
       } catch (IOException e) {
         System.out.println("Only enter number. Not String.");
         System.exit(1);
       }
}else{
System.out.print("\n That's OK! Let's just use default limit from 0 to 100\n");
max = "100"; min="0";
}

	String host = (args.length < 1) ? null : args[0];
	try {
	    Registry registry = LocateRegistry.getRegistry(host);
	    Hello stub = (Hello) registry.lookup("Hello");
	    String response = stub.sayHello(n,max,min);
	    System.out.println("You asked for = "+ n + " random number. \nWith limit from  " + min +" to "+max+" \nAnd here they are = " + response);
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}

       System.out.print("Do you need more random number (y/n)?  ");
       //BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
       String n1 = null;
       try {
         n1 = br.readLine();
       } catch (IOException e) {
         System.out.println("Only enter number. Not String.");
         System.exit(1);
       }
	if (n1.equals("y") || n1.equals("yes") ){
		repeat = true;
	} else {
	System.out.print("\nYou don't need anymore random number or enter wrong input. Bye!\n ");
	repeat = false;
	}
}while (repeat);
    }
}
