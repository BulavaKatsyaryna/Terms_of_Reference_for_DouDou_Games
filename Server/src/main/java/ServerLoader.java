import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLoader {

    private static ServerSocket server;
    private static ServerHandler handler;
    static Map<Socket, ClientHandler> handlers = new HashMap<>();

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    private static void handle() {
        handler = new ServerHandler(server);
        handler.start();
        readChat();
    }

//    The server does not shutdown when you type /end. TO CORRECT
    private static void readChat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("/end"))
                    end();
                else {
                    System.out.println("Unknown command!");
                }
            } else
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {}
        }
    }

    public static ServerHandler getServerHandler() {
        return handler;
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
