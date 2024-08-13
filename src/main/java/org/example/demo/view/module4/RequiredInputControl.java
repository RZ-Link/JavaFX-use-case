package org.example.demo.view.module4;

import javafx.beans.property.*;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * RequiredInputControl
 *
 * @author ruiqi
 * @date 2024/7/27 11:17
 */
public class RequiredInputControl extends VBox {
    private Node control = new TextField();
    private Label tipsLabel;

    private StringProperty tips = new SimpleStringProperty();

    public String getTips() {
        return tips.get();
    }

    public StringProperty tipsProperty() {
        return tips;
    }

    public RequiredInputControl() {
        tipsLabel = new Label();
        initialize();
    }

    private void initialize() {
        if (control != null) {
            this.getChildren().add(control);
        }
        this.getChildren().add(tipsLabel);
        tipsLabel.setVisible(false);
        tipsLabel.setTextFill(Color.RED);
    }

    private void resetControl() {
        this.getChildren().clear();
        initialize();
    }

    public void setTips(String tips){
        tipsLabel.setText(tips);
    }

    public Node getControl() {
        return control;
    }

    public void setControl(Node control) {
        this.control=control;
        resetControl();
    }

    public void setRequiredInputControlState(boolean isDanger){
        control.pseudoClassStateChanged(PseudoClass.getPseudoClass("danger"), isDanger);
        tipsLabel.setVisible(isDanger);
    }

}
