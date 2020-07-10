package com.fw.controller;

import com.fw.core.domain.DownloadTask;

import com.fw.core.domain.FileDownload;
import com.fw.core.domain.ThreadPoolConfig;
import com.fw.core.utils.NetSpeed;
import com.fw.domain.*;
import com.fw.factory.DownloadThreadFactory;

import com.fw.ui.TaskDownload;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.omg.PortableInterceptor.INACTIVE;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author yqf
 */
public class DownloadController {

    @FXML
    public VBox taskList;
    @FXML
    public Button download;
    @FXML
    public TextField server;

    private Config config;


    /**
     * 初始化数据
     */
    public void initData() {
        System.out.println("下载页面数据初始化成功");
        Yaml yaml = new Yaml();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.yml");
        config = yaml.loadAs(resourceAsStream, Config.class);

        System.out.println(config);

    }

    @FXML
    public void toDownload(MouseEvent mouseEvent) throws InterruptedException {

        String servePath = server.getText();
        String fileName = servePath.substring(servePath.lastIndexOf("/") + 1);
        String filePath = config.getSavePath() + "/" + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("下载");

            startDownload(fileName,servePath, filePath);

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("提示");
            alert.setHeaderText("文件" + fileName + "在" + config.getSavePath() + "已经存在");
            alert.setContentText("是否删除重新下载");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("删除文件");
                if (file.delete()) {
                    System.out.println(file.getName() + " 文件已被删除！");
                    startDownload(fileName,servePath, filePath);
                } else {
                    System.out.println("文件删除失败！");
                }
            } else {
                System.out.println("取消下载");
            }
        }


    }

    private void startDownload(String fileName,String servePath, String filePath) throws InterruptedException {

        FileDownload fileDownload = new FileDownload(
                servePath,
                filePath,
                config.getWorkThread()
        );


        Service<Integer> downloadService = new Service<Integer>() {
            @Override
            protected Task createTask() {
                return new DownloadTask(fileDownload);
            }
        };

        TaskDownload taskDownload = new TaskDownload();
        System.out.println(fileName);

        taskDownload.getTaskName().setText(fileName);
        taskDownload.getProgressBar().progressProperty().bind(downloadService.progressProperty());
        taskDownload.getProgress().textProperty().bind(downloadService.messageProperty());
        taskDownload.getSpeed().textProperty().bind(downloadService.titleProperty());


        downloadService.restart();


        taskList.getChildren().addAll(taskDownload);


    }


}
