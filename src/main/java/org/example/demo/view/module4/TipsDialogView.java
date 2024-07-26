package org.example.demo.view.module4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * TipsDialogView
 *
 * @author ruiqi
 * @date 2024/7/26 9:38
 */
public class TipsDialogView {

    @FXML
    private BorderPane tipsDialogPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Label tipsLabel;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private ImageView titleImage;

    private double xOffset;
    private double yOffset;

    private Consumer<Boolean> confirmAction;

    public static enum TipsDialogType {
        SUCCESS,
        INFO,
        WARNING,
        ERROR;
    }

    private TipsDialogType tipsDialogType = TipsDialogType.INFO;
    public void initialize(String titleInfo, String tipsInfo,TipsDialogType tipsDialogType) {
        this.tipsDialogType = tipsDialogType;
        tipsDialogPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        tipsDialogPane.setOnMouseDragged(event -> {
            ((Stage) confirmBtn.getScene().getWindow()).setX(event.getScreenX() - xOffset);
            ((Stage) confirmBtn.getScene().getWindow()).setY(event.getScreenY() - yOffset);
        });

        tipsDialogPane.setStyle("-fx-border-width: 1;-fx-border-color: black");
        closeBtn.setStyle("-fx-background-color: transparent;-fx-border-color: transparent");
        titleLabel.setText(titleInfo);
        tipsLabel.setText(tipsInfo);
        setGraphicByTipsType();
        confirmBtn.setOnAction(e -> {
            confirmAction.accept(true);
            closeDialog();
        });
        cancelBtn.setOnAction(e -> {
            confirmAction.accept(false);
            closeDialog();
        });
        closeBtn.setOnAction(e -> {
            closeDialog();
        });
    }

    public void setConfirmAction(Consumer<Boolean> action) {
        this.confirmAction = action;
    }

    private void closeDialog() {
        ((Stage) confirmBtn.getScene().getWindow()).close();
    }

    public static void create(String titleInfo, String tipsInfo,TipsDialogType tipsDialogType, Consumer<Boolean> action) {
        FXMLLoader loader = new FXMLLoader(TipsDialogView.class.getResource("/org/example/demo/view/module4/TipsDialogView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TipsDialogView tipsDialogController = loader.getController();
        tipsDialogController.initialize(titleInfo, tipsInfo,tipsDialogType);
        tipsDialogController.setConfirmAction(action);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void setGraphicByTipsType(){
        // ImageView titleImage = null;
        ImageView tipsImage = null;
        switch (tipsDialogType){
            case INFO:
                // titleImage.setImage(new Image(getClass().getResourceAsStream("/org/example/client/image/tipsdialog/info-title.png")));
                // tipsImage = new ImageView(new Image(getClass().getResourceAsStream("/org/example/client/image/tipsdialog/info-tips.png")));
                break;
            case SUCCESS:
                break;
            case WARNING:
                break;
            case ERROR:
                break;
        }
        if(titleImage.getImage()==null){
            titleImage.setManaged(false);
            titleImage.setVisible(false);
        }
        if(tipsImage!=null){
            tipsImage.setFitWidth(14);
            tipsImage.setFitHeight(14);
            tipsLabel.setGraphic(tipsImage);
        }
    }

}

