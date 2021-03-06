import lombok.AllArgsConstructor;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

@AllArgsConstructor
public class ServerHandler extends Thread {

    private final ServerSocket server;

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
                ServerLoader.handlers.put(client, handler);
            } catch (SocketException se) {
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
