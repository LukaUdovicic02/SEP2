package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.net.URL;

public enum View {
    PLACE_ORDER("PlaceOrder.fxml"),LOGIN("LoginView.fxml"),REGISTER("RegisterView.fxml"),
    ACCOUNT("AccountView.fxml"),TRANSFER("TransferView.fxml"),PORTFOLIO("Portfolio.fxml"), COMPANY_LIST("CompanyListView.fxml"), COMPANY_VIEW("CompanyView.fxml");

    private String fxmlFile;
    private ViewController viewController;

    private View(String fxmlFile){
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile(){
        return fxmlFile;
    }

    public ViewController getViewController(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        if (viewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                URL url = viewHandler.getClass().getResource(fxmlFile);
                loader.setLocation(url);
                Region root = loader.load();
                viewController = loader.getController();
                viewController.init(viewHandler, viewModelFactory, root);
                viewController.init();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            viewController.reset();
        }
        return  viewController;
    }
}
