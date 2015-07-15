
public class Client implements Runnable{
	
	public void run() {	
		try {
			Utility.NodeEntries();		
			Thread.sleep(15000);
			SpanningTree.Checkforneighbor();
			Thread.sleep(15000);
	} catch (Exception e) {
		e.printStackTrace();
	}
				}

}
