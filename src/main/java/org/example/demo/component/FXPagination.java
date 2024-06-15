package org.example.demo.component;

import cn.hutool.core.util.StrUtil;
import de.saxsys.mvvmfx.MvvmFX;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.example.demo.event.EventConsts;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;


public class FXPagination extends HBox {

    // 总条目数
    private SimpleLongProperty totalItemCount;
    // 每页显示条目个数
    private SimpleLongProperty pageSize;
    // 当前页数
    private SimpleLongProperty currentPage;

    private Label totalItemCountLabel;
    private ComboBox<String> pageSizeComboBox;
    private Button prevButton;
    private Button currentPageButton;
    private Button nextButton;
    private Label gotoLabel;
    private TextField gotoTextField;
    private Label totalPageCountLabel;

    /**
     * @param totalItemCount        总条目数
     * @param pageSize              每页显示条目个数
     * @param currentPage           当前页数
     * @param pageSizes             每页显示个数选择器的选项设置
     * @param prevButtonClickEvent  用户点击上一页按钮改变当前页时触发MvvmFX.getNotificationCenter().publish(prevButtonEvent);
     * @param nextButtonClickEvent  用户点击下一页按钮改变当前页时触发MvvmFX.getNotificationCenter().publish(nextButtonEvent);
     * @param gotoEnterPressedEvent 用户回车跳转事件触发MvvmFX.getNotificationCenter().publish(gotoEnterPressedEvent);
     */
    public FXPagination(Long totalItemCount, Long pageSize, Long currentPage, ArrayList<Long> pageSizes, String prevButtonClickEvent, String nextButtonClickEvent, String gotoEnterPressedEvent) {

        this.totalItemCount = new SimpleLongProperty(totalItemCount);
        this.pageSize = new SimpleLongProperty(pageSize);
        this.currentPage = new SimpleLongProperty(currentPage);

        this.totalItemCountLabel = new Label(StrUtil.format("Total item count {}", this.totalItemCount.get()));

        this.pageSizeComboBox = new ComboBox<>();
        for (Long item : pageSizes) {
            this.pageSizeComboBox.getItems().add(StrUtil.format("{}/page", item));
        }
        this.pageSizeComboBox.setValue(StrUtil.format("{}/page", pageSize));
        this.pageSizeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            Long newPageSize = Long.valueOf(newValue.replace("/page", ""));
            this.pageSize.set(newPageSize);

            Long newPageCount = Math.round(Math.ceil((double) this.totalItemCount.get() / (double) newPageSize));
            if (this.currentPage.get() > newPageCount) {
                this.currentPage.set(newPageCount);
            }

            this.update(this.totalItemCount.get(), this.pageSize.get(), this.currentPage.get());

        });

        this.prevButton = new Button();
        this.prevButton.setGraphic(FontIcon.of(Feather.CHEVRON_LEFT));
        this.prevButton.setOnAction(event -> {
            MvvmFX.getNotificationCenter().publish(prevButtonClickEvent, this.pageSize.get(), this.currentPage.get() - 1);
        });
        if (this.currentPage.get() == 1) {
            this.prevButton.setDisable(true);
        } else {
            this.prevButton.setDisable(false);
        }

        this.currentPageButton = new Button(String.valueOf(this.currentPage.get()));
        this.currentPageButton.setDisable(true);

        this.nextButton = new Button();
        this.nextButton.setGraphic(FontIcon.of(Feather.CHEVRON_RIGHT));
        this.nextButton.setOnAction(event -> {
            MvvmFX.getNotificationCenter().publish(nextButtonClickEvent, this.pageSize.get(), this.currentPage.get() + 1);
        });
        Long pageCount = Math.round(Math.ceil((double) this.totalItemCount.get() / (double) this.pageSize.get()));
        if (this.currentPage.get() == pageCount) {
            this.nextButton.setDisable(true);
        } else {
            this.nextButton.setDisable(false);
        }

        this.gotoLabel = new Label("Go to");

        this.gotoTextField = new TextField();
        this.gotoTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    String text = this.gotoTextField.getText();
                    Long gotoPage = Long.parseLong(text);
                    if (gotoPage >= 1 && gotoPage <= Math.round(Math.ceil((double) this.totalItemCount.get() / (double) this.pageSize.get()))) {
                        MvvmFX.getNotificationCenter().publish(gotoEnterPressedEvent, this.pageSize.get(), gotoPage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this.totalPageCountLabel = new Label(StrUtil.format("Total page count {}", pageCount));

        this.getChildren().addAll(this.totalItemCountLabel, this.pageSizeComboBox, this.prevButton, this.currentPageButton, this.nextButton, this.gotoLabel, this.gotoTextField, this.totalPageCountLabel);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
    }

    public void update(Long totalItemCount, Long pageSize, Long currentPage) {
        this.totalItemCount.set(totalItemCount);
        this.pageSize.set(pageSize);
        this.currentPage.set(currentPage);

        this.update();
    }

    private void update() {
        this.totalItemCountLabel.setText(StrUtil.format("Total item count {}", this.totalItemCount.get()));

        if (this.currentPage.get() == 1) {
            this.prevButton.setDisable(true);
        } else {
            this.prevButton.setDisable(false);
        }

        this.currentPageButton.setText(String.valueOf(this.currentPage.get()));

        Long pageCount = Math.round(Math.ceil((double) this.totalItemCount.get() / (double) this.pageSize.get()));
        if (this.currentPage.get() == pageCount) {
            this.nextButton.setDisable(true);
        } else {
            this.nextButton.setDisable(false);
        }

        this.totalPageCountLabel.setText(StrUtil.format("Total page count {}", pageCount));

    }
}
