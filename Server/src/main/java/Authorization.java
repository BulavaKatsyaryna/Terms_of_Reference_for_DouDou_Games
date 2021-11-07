import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
public class Authorization extends MessageSendingCoordinator {

    private String nickname;

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        nickname = dis.readUTF();
    }

    @Override
    public void handle() {
        ServerLoader.getHandler(getSocket()).setNickname(nickname);
        System.out.println("Authorized new socket with nickname " + nickname);
    }
}