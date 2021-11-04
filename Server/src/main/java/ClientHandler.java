import lombok.Data;

import java.io.DataInputStream;
import java.net.Socket;

@Data
public class ClientHandler extends Thread {

    private final Socket client;
    private String nickname = "nickname";

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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
            if (dis.available() <= 0)
                return;
            short id = dis.readShort();
            MessageSendingCoordinator messageSendingCoordinator = MessageManager.getMessageSendingCoordinator(id);
            messageSendingCoordinator.setSocket(client);
            messageSendingCoordinator.read(dis);
            messageSendingCoordinator.handle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void invalidate() {
        ServerLoader.invalidate(client);
    }
}
