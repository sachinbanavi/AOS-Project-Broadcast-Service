import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Listen implements Runnable {

	public static volatile ArrayList<Integer> AckList = new ArrayList<Integer>();
	public static HashSet<Integer> TreeNode = new HashSet<Integer>();
	public static HashSet<String> ReqList = new HashSet<String>();
	public static boolean val = true;
	Socket SOCK;
	static String str2;
	public static int hs;
	
	public Listen(Socket sock) {
		this.SOCK = sock;
	}
	
	public void run() {
		
		try {
			
			while (true) {
	
				str2 = Utility.ReceiveMsg(SOCK);
				String[] temp = str2.split(" ");
				
				Thread.sleep(15000);
				
				if (str2.contains("REQ")) {
					ReqList.add(str2);
					if(AckList.contains(temp[2])){
						System.out.println("Allready in the List\n");
					}else{
					AckList.add(Integer.parseInt(temp[2]));
					
					//System.out.println("--------------------- 	Val is now : " + val);
					if (val) {
						val = false;
						int tmp = AckList.get(0);
						
						Socket sk1 = Utility.Nodemap.get(tmp);
						
						
						Utility.SendMsg(sk1, Caller.serial);
						
				}
	
					}
					}
				
				
				/*if (str2.equals(Messge.ACK)) {
					
					
						TreeNode.add(Integer.parseInt(temp[2]));		
						System.out.println(TreeNode);
				
				}*/
				
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
}
