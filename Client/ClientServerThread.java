package Client;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientServerThread extends Thread 
{
    private int listennnnningPort;
    

    public ClientServerThread(int portNumber)
    {
        this.listennnnningPort = portNumber;
    }


    public void run()
    {
        try
        {
            ServerSocket ss = new ServerSocket(this.listennnnningPort);
            Socket aNewPeer = ss.accept();
            Scanner sc = new Scanner(aNewPeer.getInputStream());

            
            System.out.println("just entered the client server");
            //listen for incoming peer connections
            while(true)
            {
                System.out.println("Request?");
                String request = sc.nextLine();
                if(request.equals("yes"))
                {
                    ClientSendThread cst = new ClientSendThread(aNewPeer); //listens for incoming byte requests
                    cst.start();
                    (new ClientRequestThread(aNewPeer)).start(); //sends byte requests to other clients
                    aNewPeer = ss.accept();
                }
                
                
            }
        }
        catch(Exception e)
        {
            //whatever
        }
         
    }
// javac ClientDriver.java
// java ClientDriver
//javac Tracker.java
// java
}