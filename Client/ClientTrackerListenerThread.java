package Client;
import java.util.Scanner;

public class ClientTrackerListenerThread extends Thread
{
    private Scanner trackerInput;

    public ClientTrackerListenerThread(Scanner trackerInput)
    {
        this.trackerInput = trackerInput;
    }

    public void run()
    {
        //we want to constantly listen for a new incoming list of connected IPs
        while(true)
        {
            String listOfIPS = this.trackerInput.nextLine();
            System.out.println(listOfIPS);
            ClientCORE.updateTheConnectedClientIPs(listOfIPS);
        }
    }
}