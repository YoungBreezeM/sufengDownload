package com.fw.domain;

import javafx.concurrent.Task;

import java.io.File;

/**
 * @author yqf
 */
public class SpeedTask extends Task {
    private String filePath;

    private Long totalSize;


    public SpeedTask(String filePath, Long totalSize) {
        this.filePath = filePath;
        this.totalSize = totalSize;
    }

    @Override
    protected Object call() throws Exception {
        File file = new File(this.filePath);
        long oldSize = 0;
        while (true) {
            long length = file.length();
            updateMessage(new NetSpeed((int)(length-oldSize)).compute());
            oldSize = length;
            if(length==totalSize){
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "end";
    }
}
