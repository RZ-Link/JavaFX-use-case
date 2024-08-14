package org.example.demo.view.module4;

import de.saxsys.mvvmfx.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * SimpleFormView
 *
 * @author ruiqi
 * @date 2024/8/13 18:30
 */
public class SimpleFormView implements FxmlView<SimpleFormViewModel>, Initializable {
    public RequiredInputControl activityForm;
    public RequiredInputControl activityResource;
    public RequiredInputControl activityTime;
    public RequiredInputControl activityRegion;
    public RequiredInputControl activityName;
    public RadioButton radio1;
    public RadioButton radio2;
    public RequiredInputControl activityProperties;
    public CheckBox property1;
    public CheckBox property2;
    public CheckBox property3;
    public CheckBox property4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(radio1,radio2);
    }

    @FXML
    private void onSaveButtonAction() {
        List<RequiredInputControl> list = List.of(activityName,activityForm,activityRegion,activityProperties,activityResource,activityTime);
        for(RequiredInputControl requiredInputControl : list){
            requiredInputControl.setRequiredInputControlState(false);
        }
        if(activityName.getControl() instanceof TextField){
            if(((TextField) activityName.getControl()).getText()==null||((TextField) activityName.getControl()).getText().length()==0){
                activityName.setRequiredInputControlState(true);
            }
        }
        if(activityRegion.getControl() instanceof ComboBox<?>){
            if(((ComboBox) activityRegion.getControl()).getValue()==null){
                activityRegion.setRequiredInputControlState(true);
            }
        }
        if(activityTime.getControl() instanceof DatePicker){
            if(((DatePicker) activityTime.getControl()).getValue()==null){
                activityTime.setRequiredInputControlState(true);
            }
        }
        if(activityProperties.getControl() instanceof VBox){
            if(!property1.isSelected()&&!property2.isSelected()&&!property3.isSelected()&&!property4.isSelected()){
                activityProperties.setRequiredInputControlState(true);
            }
        }
        if(activityResource.getControl() instanceof HBox){
            if(!radio1.isSelected()&&!radio2.isSelected()){
                activityResource.setRequiredInputControlState(true);
            }
        }
        if(activityForm.getControl() instanceof TextArea){
            if(((TextArea) activityForm.getControl()).getText()==null||((TextArea) activityForm.getControl()).getText().length()==0){
                activityForm.setRequiredInputControlState(true);
            }
        }
    }

    @FXML
    private void onResetButtonAction() {
        List<RequiredInputControl> list = List.of(activityName,activityForm,activityRegion,activityResource,activityProperties,activityTime);
        for(RequiredInputControl requiredInputControl : list){
            requiredInputControl.setRequiredInputControlState(false);
        }
        if(activityName.getControl() instanceof TextField){
            ((TextField) activityName.getControl()).setText(null);
        }
        if(activityRegion.getControl() instanceof ComboBox<?>){
            ((ComboBox<?>) activityRegion.getControl()).setValue(null);
        }
        if(activityTime.getControl() instanceof DatePicker){
            ((DatePicker) activityTime.getControl()).setValue(null);
        }
        if(activityResource.getControl() instanceof HBox){
            radio1.setSelected(false);
            radio2.setSelected(false);
        }
        if(activityProperties.getControl() instanceof VBox){
            property1.setSelected(false);
            property2.setSelected(false);
            property3.setSelected(false);
            property4.setSelected(false);
        }
        if(activityForm.getControl() instanceof TextArea){
            ((TextArea) activityForm.getControl()).setText(null);
        }
    }
}
