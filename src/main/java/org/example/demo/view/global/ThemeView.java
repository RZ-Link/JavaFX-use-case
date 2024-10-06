package org.example.demo.view.global;

import atlantafx.base.theme.NordLight;
import atlantafx.base.theme.PrimerLight;
import de.saxsys.mvvmfx.FxmlView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemeView implements FxmlView<ThemeViewModel>, Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onPrimerLightButtonClick(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
    }

    public void onNordLightButtonClick(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
    }

    public void onGreenThemeButtonClick(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet("/org/example/demo/css/GreenTheme.css");
    }
}
