import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SpanningTree {

	public static ArrayList<Integer> aList = new ArrayList<Integer>();

	public static void Checkforneighbor() {
		try {
			int port;
			String ipaddr;
			int node;
			String neighbor_list;
			String line;
			String[] liner;
			String filename = "Configuration";
			FileReader inputFile1 = new FileReader(filename);
			BufferedReader bufferReader1 = new BufferedReader(inputFile1);

			while ((line = bufferReader1.readLine()) != null) {

				liner = line.split(" ");
				node = Integer.parseInt(liner[0]);
				ipaddr = liner[1];
				port = Integer.parseInt(liner[2]);

				if (Caller.serial == node) {

					neighbor_list = liner[3];

					String[] a = neighbor_list.split(",");
					for (int p = 0; p < a.length; p++) {

						aList.add(Integer.parseInt(a[p]));

					}
					// System.out.println(aList);
					Utility.Getsocket();

					//Utility.printHashMap();

					if (Utility.Nmap.size() > 0) {
						// for (int i = 0; i < Utility.Nmap.size(); i++) {

						Iterator it = Utility.Nmap.entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							

							Socket Nsk = (Socket) pair.getValue();
							DataOutputStream dout = new DataOutputStream(Nsk.getOutputStream());
							dout.writeUTF("From node " + Caller.serial + " REQ neighbor?\n");
						}
					}
				}

			}

			bufferReader1.close();
			inputFile1.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
