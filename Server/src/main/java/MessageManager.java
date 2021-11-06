import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    private final static Map<Short, Class<? extends MessageSendingCoordinator>> managers = new HashMap<>();

    static {
        managers.put((short) 1, Authorization.class);
        managers.put((short) 2, Message.class);
    }

    public static MessageSendingCoordinator getMessageSendingCoordinator(short id) {
        try {
            return managers.get(id).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}