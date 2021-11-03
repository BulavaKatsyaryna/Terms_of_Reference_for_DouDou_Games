import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static ServerSocket server;

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    private static void start() {
        try {
            server = new ServerSocket(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void end() {
        try {
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        while (true) {
            try {
                Socket client = server.accept();
                new ClientHandler(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ix) {}
        }
    }
}
