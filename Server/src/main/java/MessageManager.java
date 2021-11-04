import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    private final static Map<Short, Class<? extends MessageSendingCoordinator>> managers = new HashMap<>();

    static {
        managers.put((short) 1, Authorization.class);
    }

    public static MessageSendingCoordinator getMessageSendingCoordinator(short id) {
        try {
            return managers.get(id).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//
//    public static void read(short id, DataInputStream dis) {
//        try {
//            //Добавлен .getDeclaredConstructor(), голый .newInstance() - депрекейтед
//            MessageSendingCoordinator messageSendingCoordinator = managers.get(id).getDeclaredConstructor().newInstance();
//            messageSendingCoordinator.read(dis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
