import java.net.ServerSocket;

public class Main {

    private static ServerSocket server;

    public static void main(String[] args) {
        start();

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
}
