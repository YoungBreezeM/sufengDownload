package com.fw.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author yqf
 */
public class TaskDownload extends VBox {


    private Label taskName;

    private ProgressBar progressBar;

    private Label speed;

    private Label progress;

    private Button cancel;

    public Label getTaskName() {
        return taskName;
    }

    public Label getProgress() {
        return progress;
    }

    public void setProgress(Label progress) {
        this.progress = progress;
    }

    public void setTaskName(Label taskName) {
        this.taskName = taskName;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public Label getSpeed() {
        return speed;
    }

    public void setSpeed(Label speed) {
        this.speed = speed;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public TaskDownload() {
        defaultStyle();
        setComponents();
    }


    /**
     * 设置样式
     */
    private void defaultStyle() {
        this.setStyle("-fx-border-color: #9a9b91");
    }

    /**
     * 设置子组建
     */

    private void setComponents() {
        this.taskName = new Label();
        this.progressBar = new ProgressBar(0);
        this.progressBar.setPrefWidth(280);
        this.speed = new Label();
        this.cancel = new Button("取消");
        this.progress = new Label();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(this.progressBar,this.progress,this.speed);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(10);

        this.getChildren()
                .addAll(this.taskName,hBox);


    }


}
