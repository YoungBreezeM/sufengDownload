package com.fw.controller;

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
    public TextField serverPath;

    private ThreadPoolExecutor threadPoolExecutor;

    private Config config;

    private int i=0;



    /**
     * 初始化数据
     */
    public void initData() {
        System.out.println("下载页面数据初始化成功");
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>();
        DownloadThreadFactory downloadThreadFactory = new DownloadThreadFactory("download");
        Yaml yaml = new Yaml();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.yml");
        config = yaml.loadAs(resourceAsStream, Config.class);
        threadPoolExecutor = new ThreadPoolExecutor(
                Integer.parseInt(config.getCorePoolSize()),
                Integer.parseInt(config.getMaximumPoolSize()),
                Integer.parseInt(config.getKeepAliveTime()),
                TimeUnit.SECONDS,
                blockingQueue,
                downloadThreadFactory
        );

    }

    @FXML
    public void toDownload(MouseEvent mouseEvent) {

        String url = serverPath.getText();
        Yaml yaml = new Yaml();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.yml");
        Config config = yaml.loadAs(resourceAsStream, Config.class);
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        String filePath = config.getSavePath() + "/" + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("下载");
            startDownload(url, filePath);
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
                    startDownload(url, filePath);
                } else {
                    System.out.println("文件删除失败！");
                }
            } else {
                System.out.println("取消下载");
            }
        }


    }

    private void startDownload(String path, String filePath) {

        Download downLoad = new Download(path,filePath,Integer.parseInt(config.getNumberOfBytes()));


        MultithreadingDownLoad m = new MultithreadingDownLoad(threadPoolExecutor, Integer.parseInt(config.getWorkThread()),downLoad);
        m.executeDownLoad();
        Long fileLength = m.getFileLength();

        Service<Integer> progressService = new Service<Integer>() {
            @Override
            protected Task createTask() {
                return new ProgressTask(filePath, fileLength);
            }
        };

        Service<String> speedService = new Service<String>() {
            @Override
            protected Task<String> createTask() {
                return new SpeedTask(filePath,fileLength);
            }
        };

        TaskDownload taskDownload = new TaskDownload();
        ProgressBar progressBar = taskDownload.getProgressBar();
        Label speed = taskDownload.getSpeed();

        speed.textProperty().bind(speedService.messageProperty());
        speedService.restart();

        progressBar.progressProperty().bind(progressService.progressProperty());
        progressService.restart();

        taskList.getChildren().addAll(taskDownload);









    }



}
