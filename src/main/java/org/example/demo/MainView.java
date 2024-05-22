package org.example.demo;

import de.saxsys.mvvmfx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.demo.event.EventConsts;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements FxmlView<MainViewModel>, Initializable {

    @FXML
    private BorderPane borderPane;

    @InjectViewModel
    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SideMenu sideMenu = new SideMenu();
        TabPane tabPane = new TabPane();

        borderPane.setLeft(sideMenu);
        borderPane.setCenter(tabPane);

        MvvmFX.getNotificationCenter().subscribe(EventConsts.SideMenuSelected.getKey(), (key, payload) -> {
            Tab tab = new Tab((String) payload[0]);
            switch ((String) payload[0]) {
                case "Child 1":
                    tab.setContent(FluentViewLoader.fxmlView(Child1View.class).load().getView());
                    break;
                case "Child 2":
                    tab.setContent(FluentViewLoader.fxmlView(Child2View.class).load().getView());
                    break;
                default:
                    return;
            }
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });
    }
}
