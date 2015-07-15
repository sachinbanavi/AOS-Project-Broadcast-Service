import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Utility {
	
	public static HashMap<Integer, Socket> Nmap = new HashMap<Integer, Socket>();
	public static HashMap<Integer, Socket> Nodemap = new HashMap<Integer, Socket>();
	public static HashSet<String> ChildList = new HashSet<String>();
	public static HashSet<String> BroadcastMSG = new HashSet<String>();
	public static HashSet<String> ConvergeList = new HashSet<String>();
	
	public static void NodeEntries(){
		
		try {
			int port;
			String ipaddr;
			int node;
			String[] liner;
			String filename = "Configuration";

			FileReader inputFile = new FileReader(filename);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;

			while ((line = bufferReader.readLine()) != null) {
				
				liner = line.split(" ");
				node = Integer.parseInt(liner[0]);
				
				ipaddr = liner[1];
				port = Integer.parseInt(liner[2]);
               	
					Socket socks = new Socket(ipaddr, port);
					
					Nodemap.put(node, socks);

					
			}
			// Close the buffer reader
			bufferReader.close();
			inputFile.close();

			//SpanningTree.Checkforneighbor();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		
	}
	
	public static void Getsocket(){

		try {
			int port;
			String ipaddr;
			int node;
			String[] liner;
			String filename = "Configuration";

			FileReader inputFile = new FileReader(filename);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;

			while ((line = bufferReader.readLine()) != null) {
				
				liner = line.split(" ");
				node = Integer.parseInt(liner[0]);
				for (int i = 0; i < SpanningTree.aList.size(); i++) {
					if(SpanningTree.aList.get(i) == node){
				ipaddr = liner[1];
				port = Integer.parseInt(liner[2]);
               	
					Socket sock = new Socket(ipaddr, port);
					
					Nmap.put(node, sock);

					}//if
				}//for
			}
			// Close the buffer reader
			bufferReader.close();
			inputFile.close();

			//SpanningTree.Checkforneighbor();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}
	
	public static void SendBroadcast(String bmsg,int sender,Socket sk){
		
		DataOutputStream dout;
		try {
			
	    dout = new DataOutputStream(sk.getOutputStream());
		dout.writeUTF("From node " + sender + " BMSG: "+bmsg);
		//System.out.println("Sending Positive"+Messge.ACK);
		
		/*if(Listen.TreeNode.size() == 0){
			int ss = Listen.AckList.get(0);
			
		Broadcast.BeginConvergecast(ss);
		}*/
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void SendMsg(Socket sk,int sender){
		DataOutputStream dout;
		try {
			
	    dout = new DataOutputStream(sk.getOutputStream());
		dout.writeUTF("From node " + sender + " Positive "+Messge.ACK);
		//System.out.println("Sending Positive"+Messge.ACK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String ReceiveMsg(Socket sk){
		
		String str = "";
		String[] temp1;
		try {
		DataInputStream din = new DataInputStream(sk.getInputStream());
		str = din.readUTF();
		temp1 = str.split(" ");
		if(str.contains("ACK")){
			
			//System.out.println("temp1 is "+ temp1);
			Listen.TreeNode.add(Integer.parseInt(temp1[2]));
			//System.out.println("From Uitility "+Listen.TreeNode);
			ChildList.add(str);
		}
		
		System.out.println("	>> Received msg: " + str);
		
		if(str.contains("BMSG")){
		String[] temp2;
		BroadcastMSG.add(str);
		int snd;
		String md;
		temp2 = str.split(" ");
		md = temp2[4];
		snd = Integer.parseInt(temp2[2]);
		Broadcast.CheckNsend(md, snd);
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if(Listen.TreeNode.size() == 0){
		if(str.contains("DONE")){
			ConvergeList.add(str);
		}
	    if(Listen.AckList.size()>0){
		int ss = Listen.AckList.get(0);	
		Broadcast.BeginConvergecast(ss);
	    }else{
	    	String[] tem;
			int sn;
			String d;
			tem = str.split(" ");
			d = tem[4];
			sn = Integer.parseInt(tem[2]);
			Broadcast.BeginConvergecast(sn);
	    }
		//}
		
		}
		
		/*if(str.contains("DONE")){
			String[] temp3;
			int send;
			temp3 = str.split(" ");
			send = Integer.parseInt(temp3[2]);
			Broadcast.BeginConvergecast(send);
			
			
		}*/
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(str);
	}

	public static void printHashMap() {
		HashMap<Integer, Socket> map = Nmap;
		System.out.println("* Printing hashmap objects *");
		System.out.println("Hashmap size - " + map.size());
		for (Map.Entry<Integer, Socket> entry : map.entrySet()) {
			System.out.println("============================= "
					+ entry.getKey() + " : " + entry.getValue());
		}
	}

	public static void printAckList() {
		System.out.println("* Printing Ack List *");
		for (Integer i : Listen.AckList) {
			System.out.println("Ack object : " + i);
		}
	}

}
