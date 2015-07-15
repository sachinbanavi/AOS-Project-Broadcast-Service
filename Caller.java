import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;


public class Caller {
	static int serial;
	static int setup;
	static int count;
	public static boolean flag;
	public static boolean isFirst;
	
	public static void main(String[] args) throws InterruptedException,
	IOException {
		
	

		// Reading node number from user
System.out.println("Enter name of the node");

Scanner in = new Scanner(System.in);
int s = in.nextInt();
//int s = Integer.parseInt(args[0]);
System.out.println(s);
serial = s;
// System.out.println("Node number "+serial);

// Creating server class thread.
Server s1 = new Server();
Thread t1 = new Thread(s1);
t1.start();
// System.out.println("Successfully created server thread");

Thread.sleep(35000);
// Calling client method1

Client c1 = new Client();
Thread t2 = new Thread(c1);
t2.start();

Thread.sleep(35000);


Thread.sleep(25000);

if(Listen.AckList.size() != 0){
	while(Listen.TreeNode.contains(Listen.AckList.get(0))){
		Listen.AckList.remove(Listen.AckList.get(0));
	}
	if(Listen.AckList.size() <= 0){
System.out.println("I am the root node of Spanning Tree");
}else{
	System.out.println("My parent is "+Listen.AckList.get(0));
}
}else{
	System.out.println("I am the root node of Spanning Tree");
}
Thread.sleep(25000);
System.out.println("The child neighbors are "+Listen.TreeNode);

Broadcast.AskToBroadcast();
Broadcast.BeginBreadcast(Broadcast.bmsg);

//Thread.sleep(15000);
System.out.println("Initiating Convergecast");

if(Listen.TreeNode.size() == 0){
	int ss = Listen.AckList.get(0);
Broadcast.BeginConvergecast(ss);
}
/*
//Thread.sleep(15000);
//Writing REQ messages to Log file
//PrintStream out = new PrintStream(new FileOutputStream("HashSet.txt"));
//Iterator hashSetIterator = Listen.ReqList.iterator();
//while(hashSetIterator.hasNext()){
  //  System.out.println(hashSetIterator.next());
//}
//out.close();

//Writing Positive ACK's to file
//PrintStream out1 = new PrintStream(new FileOutputStream("HashSet1.txt"));
//Iterator hashSetIterator1 = Utility.ChildList.iterator();
//while(hashSetIterator1.hasNext()){
    System.out.println(hashSetIterator1.next());
}
//out1.close();

//Writing all Broadcast messages to Log file
//PrintStream out2 = new PrintStream(new FileOutputStream("HashSet2.txt"));
Iterator hashSetIterator2 = Utility.BroadcastMSG.iterator();
while(hashSetIterator1.hasNext()){
    System.out.println(hashSetIterator2.next());
}
//out2.close();

//Writing Convergecast message to Log file
//PrintStream out3 = new PrintStream(new FileOutputStream("HashSet3.txt"));
Iterator hashSetIterator3 = Utility.ConvergeList.iterator();
while(hashSetIterator1.hasNext()){
    System.out.println(hashSetIterator2.next());
}
//out3.close();
*/
	}

}

