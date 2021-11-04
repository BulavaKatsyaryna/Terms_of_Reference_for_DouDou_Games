import lombok.Data;

import java.io.DataInputStream;
import java.net.Socket;

@Data
public class ClientHandler extends Thread {

    private final Socket client;

//    public ClientHandler(Socket client) {
//        this.client = client;
//    }

    @Override
    public void run() {
        while (true) {
            readData();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ix) {
            }
        }
    }

    private void readData() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() <= 0);
//            return;

            short id = dis.readShort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
