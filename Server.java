import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server implements Runnable{

	@Override
	public void run() {

		try{
			int port = 0;
			int node;
			String[] liner;
			String filename = "Configuration";
			FileReader inputFile = new FileReader(filename);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;
          
			while((line = bufferReader.readLine()) != null){
				
				liner = line.split(" ");
				node = Integer.parseInt(liner[0]);
				
				
				if(node == Caller.serial){
					port = Integer.parseInt(liner[2]);
					break;
				}
			}
			
			bufferReader.close();
			inputFile.close();

			if (port == 0) {
				System.out
						.println("error occured while initializing port number");
				System.exit(1);
			}
			
			ServerSocket server = new ServerSocket(port);
			
			while (true) {
				Socket sock = server.accept();
				//connection.add(sock);

				
				Listen listener = new Listen(sock);
				Thread l1 = new Thread(listener);
				l1.start();

				
			}
			
		}catch(IOException E) {
			System.out.println("Caought IO Exception in Main Server.java" + E);
		}
		
	}
	

	
	
}
