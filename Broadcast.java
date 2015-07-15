import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class Broadcast {
 
	public static String bmsg;
	public static void AskToBroadcast(){
		
		System.out.println("Do you want to Broadcast? Yes/No\n");
		Scanner a = new Scanner(System.in);
		String b = a.nextLine();
		//String bmsg;

		if(b.contentEquals("Yes")){
			System.out.println("Please enter your braodcast message:");
			Scanner z = new Scanner(System.in);
			bmsg = z.nextLine();
			System.out.println(bmsg);
		}else{
			System.out.println("Atleast try once stupid");
		}		
		
	}
	
	public static void BeginBreadcast(String bmsg){
		
		if(Listen.AckList.size() != 0){
			
			//Send to the parent node
			int tmp = Listen.AckList.get(0);
			Socket sk1 = Utility.Nodemap.get(tmp);
			Utility.SendBroadcast(bmsg, Caller.serial,sk1);
			
			//Send to all its child nodes
			Iterator it = Listen.TreeNode.iterator();
			while (it.hasNext()) {
			    int sh = (int) it.next();
			    Socket sk2 = Utility.Nodemap.get(sh);
			    Utility.SendBroadcast(bmsg, Caller.serial, sk2);
			}
		}else{
			
			//Send only to its child nodes
			Iterator it = Listen.TreeNode.iterator();
			while (it.hasNext()) {
			    int hs = (int) it.next();
			    Socket sk2 = Utility.Nodemap.get(hs);
			    Utility.SendBroadcast(bmsg, Caller.serial, sk2);
			}
		}
		
	}
	
	 public static void CheckNsend(String bmsg,int snd){
		 /*
		//Broadcast to Parent node if the msg is coming from leafnode
		 if(snd == Listen.AckList.get(0)){
			 
		 //Broadcast to all the child except the incomming node
		 Iterator it1 = Listen.TreeNode.iterator();
			while (it1.hasNext()) {
			    int hs1 = (int) it1.next();
			    if(!(hs1 == snd)){
			    Socket sk3 = Utility.Nodemap.get(hs1);
			    Utility.SendBroadcast(bmsg, Caller.serial, sk3);
			}
			}
		 }else{
			 int o = Listen.AckList.get(0);
			 Socket sk6 = Utility.Nodemap.get(o);
			    Utility.SendBroadcast(bmsg, Caller.serial, sk6);
			 Utility.SendBroadcast(bmsg, Caller.serial, sk6);
		 }
			*/
		 //New code
		 
		 //if the broadcast is coming from parent,send the broadcast to all the children
		 if(Listen.AckList.size() > 0){
		 
		 if(snd == Listen.AckList.get(0)){
			 
			 Iterator it2 = Listen.TreeNode.iterator();
				while (it2.hasNext()) {
				    int shh = (int) it2.next();
				    Socket skk = Utility.Nodemap.get(shh);
				    Utility.SendBroadcast(bmsg, Caller.serial, skk);
				}
		 }else {
			 //Broadcast is coming from child node,send broadcast to parent if it exists and all other remaining children
			//Broadcast to all the child except the incomming node
			 Iterator it1 = Listen.TreeNode.iterator();
				while (it1.hasNext()) {
				    int hs1 = (int) it1.next();
				    if(!(hs1 == snd)){
				    Socket sk3 = Utility.Nodemap.get(hs1);
				    Utility.SendBroadcast(bmsg, Caller.serial, sk3);
				}
				}
				if(Listen.AckList.size() >= 0){
				
					int tmp0 = Listen.AckList.get(0);
					Socket sk7 = Utility.Nodemap.get(tmp0);
					Utility.SendBroadcast(bmsg, Caller.serial,sk7);
					
				}
		 }
		 
		 }else{
			 //if root node,send to other child nodes except the requesting child
			 Iterator it1 = Listen.TreeNode.iterator();
				while (it1.hasNext()) {
				    int hs1 = (int) it1.next();
				    if(!(hs1 == snd)){
				    Socket sk3 = Utility.Nodemap.get(hs1);
				    Utility.SendBroadcast(bmsg, Caller.serial, sk3);
				}
				}
		 }
		 
			
	 }
	 
	
	 public static void BeginConvergecast(int snd){
		 
		/*
		 
		 int count = Listen.TreeNode.size();
		 if(count == 0){
			 Socket sk4 = Utility.Nodemap.get(snd);
			 DataOutputStream dout;
				try {
					
			    dout = new DataOutputStream(sk4.getOutputStream());
				dout.writeUTF("From node " + Caller.serial + " DONE");
				//System.out.println("Sending Positive"+Messge.ACK);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }else{
			 
			 while(count >= 0){
				 
				 
				// Socket sk5 = Utility.Nodemap.get(Caller.serial);
				 //Utility.ReceiveMsg(sk5);
				 count--;
				 
			 }
			 if(count < 0){
				 Socket sk5 = Utility.Nodemap.get(snd);
				 DataOutputStream dout;
					try {
						
				    dout = new DataOutputStream(sk5.getOutputStream());
					dout.writeUTF("From node " + Caller.serial + " DONE");
					//System.out.println("Sending Positive"+Messge.ACK);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 //if(Listen.AckList.size() == 0){
				// System.out.println("Broadcast complete");
			 //}*/
		 //}
		 
		 if(Listen.AckList.size() >0){
		 Socket sk5 = Utility.Nodemap.get(snd);
		 DataOutputStream dout;
			try {
				
		    dout = new DataOutputStream(sk5.getOutputStream());
			dout.writeUTF("From node " + Caller.serial + " DONE");
			//System.out.println("Sending Positive"+Messge.ACK);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 Iterator it3 = Listen.TreeNode.iterator();
				while (it3.hasNext()) {
				    int hs = (int) it3.next();
				    if(!(hs == snd)){
				    Socket sk0 = Utility.Nodemap.get(hs);
				    Utility.SendBroadcast(bmsg, Caller.serial, sk0);
				}
				}
		 }
	 }
		
	
}
