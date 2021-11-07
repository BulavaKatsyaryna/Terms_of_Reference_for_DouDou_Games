package сoordinator;

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
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(nickname);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
    }

    @Override
    public void handle() {
    }
}
