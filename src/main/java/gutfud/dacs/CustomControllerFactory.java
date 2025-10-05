package gutfud.dacs;

import gutfud.dacs.AppController;
import gutfud.dacs.BUS.DataStreamManager;
import gutfud.dacs.DTO.User;
import javafx.util.Callback;

public class CustomControllerFactory implements Callback<Class<?>, Object> {

    private DataStreamManager manager;
    private User user;

    public CustomControllerFactory(DataStreamManager manager, User user) {
        this.manager = manager;
        this.user = user;
    }

    @Override
    public Object call(Class<?> type) {
        if (type == AppController.class) { // Replace with your actual controller class name
            return new AppController(manager, user); // Inject both objects
        }
        try {
            return type.getDeclaredConstructor().newInstance(); // Fallback for other controllers
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
