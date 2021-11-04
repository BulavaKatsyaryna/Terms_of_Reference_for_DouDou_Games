import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerLoader {

    private static ServerSocket server;
    private static Map<Socket, ClientHandler> handlers = new HashMap<>();

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    private static void handle() {
        while (true) {
            try {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
                handlers.put(client, handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ix) {}
        }
    }

    private static void start() {
        try {
            server = new ServerSocket(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void end() {
        try {
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ClientHandler getHandler(Socket socket) {
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket) {
        handlers.remove(socket);
    }
}
