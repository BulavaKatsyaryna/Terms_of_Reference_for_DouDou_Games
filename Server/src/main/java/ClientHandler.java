import java.io.DataInputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataInputStream dis = new DataInputStream(client.getInputStream());
                if (dis.available() > 0) {
                    // reading & handling message
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ix) {
            }
        }
    }
}
