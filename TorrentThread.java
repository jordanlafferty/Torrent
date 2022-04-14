import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TorrentThread extends Thread
{
    private Socket clientSocket;
    private Scanner textInput;
    private PrintStream textOutput;
    
    public TorrentThread(Socket clientSocket)
    {
        try
        {
            this.clientSocket = clientSocket;
            this.textInput = new Scanner(this.clientSocket.getInputStream());
            this.textOutput = new PrintStream(this.clientSocket.getOutputStream());
        }
        catch(Exception e)
        {}
        
    }

    public void run()
    {
        System.out.println("Child thread started!!!!");
        this.textOutput.println("Do you want to send or receive a file? ");
        String answer = this.textInput.nextLine();

        if(answer.equals("send"))
        {
            System.out.println("receiving a file from the client");
            try
            {
                DataInputStream dis = new DataInputStream(this.clientSocket.getInputStream());
                while(true)
                {
                    byte b = dis.readByte();
                    CORE.broadCastByte(b);
                } 
            }
            catch(EOFException e)
            {
                System.out.println("Done Receiving File");
                try 
                {
                    CORE.removeReceivers();
                    this.clientSocket.close();
                } 
                catch (IOException ioe) 
                {
                    
                }
                
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else if(answer.equals("receive"))
        {
            try
            {
                System.out.println("sending a file to the client");
                CORE.addDOS(new DataOutputStream(this.clientSocket.getOutputStream()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
