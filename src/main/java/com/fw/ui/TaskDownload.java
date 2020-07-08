package com.fw.ui;

import com.fw.domain.MultithreadingDownLoad;
import com.fw.domain.NetSpeed;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.concurrent.*;

/**
 * @author yqf
 */
public class TaskDownload extends HBox {


    private Label taskName;

    private ProgressBar progressBar;

    private Label speed;

    private Button cancel;

    public Label getTaskName() {
        return taskName;
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
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(20);
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
        this.getChildren()
                .addAll(taskName,progressBar,speed,cancel);


    }


}
