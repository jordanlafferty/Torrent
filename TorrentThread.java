import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.net.Inet4Address;

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
            String x = Inet4Address.getLocalHost().getHostAddress();
            CORE.addIP(x);
            CORE.addPS(textOutput);
        }
        catch(Exception e)
        {}
        
    }

    public void run()
    {
        //get the torrent they want to participate in
        String torrentName = this.textInput.nextLine();
        String ip_address = this.textInput.nextLine();
        String port_number = this.textInput.nextLine();
       
    }
}
