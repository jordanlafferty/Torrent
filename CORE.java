import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class CORE 
{
    private static ArrayList<DataOutputStream> theClientDOSsss= new ArrayList<DataOutputStream>();
    private static ArrayList<String> IPList= new ArrayList<String>();
    private static ArrayList<PrintStream> theClientStreams = new ArrayList<PrintStream>();

    public synchronized static void addDOS(DataOutputStream dos)
    {
        CORE.theClientDOSsss.add(dos);
    }
    
    public static synchronized void removeReceivers()
    {
        for(DataOutputStream dos : CORE.theClientDOSsss)
        {
            try 
            {
                dos.close();
            } 
            catch (Exception e) 
            {
                
            }
            
        }
        CORE.theClientDOSsss.clear();
    }

    public synchronized static void broadCastByte(byte b)
    {
        try
        {
            for(DataOutputStream dos : CORE.theClientDOSsss)
            {
                dos.writeByte(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public synchronized static void addIP(String IP)
    {
        CORE.IPList.add(IP);
    }
    public static synchronized void addPS(PrintStream ps)
    {
       
        System.out.println("adding client thread");
        CORE.theClientStreams.add(ps);
        


    }

    public static void broadcastIP()
    {
        System.out.println("About to broadcast....");
        for (PrintStream ps : CORE.theClientStreams)
        {    
          
            ps.println(CORE.IPList);

        }
    }

}