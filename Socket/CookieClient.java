import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;



public class CookieClient {

    public static void main(String[] args) {

	if (args.length != 2) {
	      System.err.println("Usage: java Simulator "  +
				 "<host: hostname>" +
			         "<port: number>");
	      System.exit(1);
	}

	String host = args[0];
	int port = Integer.parseInt(args[1]);	
	System.out.println("Client connecting to " + host + ": " + port + " ...");
	init(host, port);      
    } 

/*
  init() initializes Socket on user specified host and port.
  If Socket not connected to server, it runs recursively. If Socket
  connected to Server call listen().
*/
    public static void init(String host, int port) {

	try {
	    Socket client = new Socket(host, port);
	    if (!client.isConnected()) {
		init(host, port);
	    } else {		
		System.out.println("Connection established");
	        listen(client);
	    }
	} catch (IOException e) {
	     System.out.println(e);
	}
    }

/*
    listen() creates a BufferedReader and StringBuilder.
    BufferedReader created with InputStream and InputStreamReader. 
    While BufferedReader.readLine() is not empty and InputStream
    has contents, StringBuilder adds contents to String
    
*/
    public static void listen (Socket client) {

	 BufferedReader buffered_reader;
	 StringBuilder string_builder = new StringBuilder();
	 String line;
	 try {
	     InputStream file = client.getInputStream();
	     buffered_reader = new BufferedReader(new InputStreamReader(file));
	     while ((line = buffered_reader.readLine()) != null) {
		  string_builder.append(line);
	     }
	  } catch (IOException e) {
		   e.printStackTrace();
	  } 
	 System.out.println("Your fortune: " + string_builder.toString());
	 exit();
    }

    public static void exit() {

	System.out.println("Exiting");
	System.exit(1);
    }    
}
