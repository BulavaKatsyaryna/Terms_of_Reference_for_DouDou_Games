import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.Socket;

@Data
@AllArgsConstructor
public class ClientHandler implements Runnable {

    private final Socket client;

//    public ClientHandler(Socket client) {
//        this.client = client;
//    }

    @Override
    public void run() {

    }
}
