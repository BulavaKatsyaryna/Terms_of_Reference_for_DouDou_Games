import сoordinator.MessageSendingCoordinator;

import java.io.DataOutputStream;
import java.net.Socket;

public class ClientLoader {

    private static Socket socket;

    public static void main(String[] args) {
        connect();
        handle();
        end();
    }

    private static void connect() {
        try {
            socket = new Socket("localhost", 8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(MessageSendingCoordinator messageSendingCoordinator) {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeShort(messageSendingCoordinator.getId());
            messageSendingCoordinator.write(dos);
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(1212);
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void end() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
