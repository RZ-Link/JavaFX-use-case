package org.example.demo.view.module2;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Module2View implements FxmlView<Module2ViewModel>, Initializable {

    @InjectViewModel
    private Module2ViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
