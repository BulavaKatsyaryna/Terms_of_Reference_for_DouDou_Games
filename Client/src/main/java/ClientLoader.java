import java.net.Socket;

public class ClientLoader {

    private static Socket socket;

    public static void main(String[] args) {
        connect();
    }

    private static void connect() {
        try {
            socket = new Socket("localhost", 8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handle() {

    }

    private static void end() {

    }
}
