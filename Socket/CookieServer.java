import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Random;



public class CookieServer {

    public static void main(String[] args) {

       if (args.length != 1) {
	      System.err.println("Usage: java Simulator "  +
			         "<port number>");
	      System.exit(1);
	}

	int port = Integer.parseInt(args[0]);	
	init(port);     
    } 

/*
     init() initializes server on specified port. 
*/
    public static void init(int port) {

	try {
	    ServerSocket server = new ServerSocket(port);
	    System.out.println("Listening on port " + port + " ...");
	    connect(server);

	} catch (IOException e) {
	      System.out.println(e);
	}
    }
    
/*
   connect() looks for client connection. If connection found, listen() is called.
   If no connection found, run recursively. 
*/
    public static void connect(ServerSocket server) {

	Socket socket;
       	try { 	    
	    socket = server.accept();
	    if (!socket.isConnected()) {	    
		connect(server);	   
	    } else {
		
		System.out.println("Connection established");
		listen(socket);		
	    }	  
	} catch (IOException e) {
	    System.out.println(e);
	}       
    }
/*
   listen() calls getFortune() to get a random fortune. Converts fortune
   to byte array and writes fortune to OutputStream.  
*/
    public static void listen(Socket socket) {

	String fortune = getFortune();		
	try {
	    byte[] b = fortune.getBytes();
	    OutputStream file = socket.getOutputStream();
	    file.write(b);
	    System.out.println("Fortune sent");
	    exit();	  
	} catch (IOException e) {
	    System.out.println(e);
	}
    }

/*
  getFortune() returns a random fortune from array of strings.
*/
     public static String getFortune() {
	 
	String[] fortunes  = new String[10]; 
        Random rand = new Random();
	fortunes[0] = "The fortune you seek is in another cookie.";
	fortunes[1] = "A closed mouth gathers no feet.";
	fortunes[2] = "A cynic is only a frustrated optimist.";
	fortunes[3] = "A foolish man listens to his heart. A wise man listens to cookies.";
	fortunes[4] = "You will die alone and poorly dressed.";
	fortunes[5] = "A fanatic is one who can't change his mind, and won't change the subject.";
	fortunes[6] = "If you look back, you'll soon be going that way.";
	fortunes[7] = "You will live long enough to open many fortune cookies.";
	fortunes[8] = "He who throws dirt is losing ground.";
	fortunes[9] = "Help! I am being held prisoner in a fortune cookie factory.";	
	return fortunes[rand.nextInt(10)];
    }
    
    public static void exit() {
	
	System.out.println("Exiting");
	System.exit(1);
    }

    

}
