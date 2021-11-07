package сoordinator;

import lombok.NoArgsConstructor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@NoArgsConstructor
public class Message extends MessageSendingCoordinator {

    private String sender;

    private String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        sender = dis.readUTF();
        message = dis.readUTF();
    }

    @Override
    public void handle() {
        System.out.println(String.format("[%s] %s", sender, message));
    }
}
