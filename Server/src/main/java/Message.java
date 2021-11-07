import lombok.NoArgsConstructor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@NoArgsConstructor
public class Message extends MessageSendingCoordinator{

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
        dos.writeUTF(sender);
        dos.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        message = dis.readUTF();
    }

    @Override
    public void handle() {
        sender = ServerLoader.getHandler(getSocket()).getNickname();
        ServerLoader.handlers.keySet().forEach(key -> ServerLoader.sendCoordinator(key, this));
        System.out.println(String.format("[%s] %s", sender, message));
    }
}