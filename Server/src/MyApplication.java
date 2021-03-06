import javafx.stage.Stage;
import model.Model;
import model.ModelManger;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyApplication extends javafx.application.Application {
    private Model model;
    @Override public void start(Stage primaryStage) throws IOException, SQLException {
        model = new ModelManger();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }

    @Override public void stop() throws RemoteException {
        model.close();
    }
}
