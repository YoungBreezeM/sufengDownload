package com.fw.domain;

import javafx.concurrent.Task;

import java.io.File;

/**
 * 下载进度任务
 * @author yqf
 */
public class ProgressTask extends Task {

    private String filePath;

    private Long totalSize;


    public ProgressTask(String filePath, Long totalSize) {
        this.filePath = filePath;
        this.totalSize = totalSize;
    }

    @Override
    protected Object call() throws Exception {
        File file = new File(this.filePath);
        while (true) {
            long length = file.length();
            updateProgress(length, totalSize);
            if(length==totalSize){
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
