import сoordinator.MessageManager;
import сoordinator.MessageSendingCoordinator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientLoader {

    private static Socket socket;

    public static void main(String[] args) {
        connect();
        readChat();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
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

//    private static void handle() {
//        sendMessage(new Authorization("Imechko"));
//    }

    private static void handle() {
        Thread handler = new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        if (dis.available() <= 0) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                            continue;
                        }
                        short id = dis.readShort();
                        MessageSendingCoordinator messageSendingCoordinator = MessageManager.getMessageSendingCoordinator(id);
                        if (messageSendingCoordinator != null) {
                            messageSendingCoordinator.read(dis);
                            messageSendingCoordinator.handle();
                        }
//                        messageSendingCoordinator.read(dis);
//                        messageSendingCoordinator.handle();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        handler.start();
        readChat();
    }

    //    The server does not shutdown when you type /end. TO CORRECT
    private static void readChat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextLine()) {
                String line = sc.nextLine();

            } else
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                }
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
