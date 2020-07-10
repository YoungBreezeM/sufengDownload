package com.fw.domain;

import java.io.Serializable;

/**
 * @author yqf
 */
public class Config implements Serializable {

    private String savePath;

    private int workThread;

    private int numberOfBytes;

    private int corePoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    @Override
    public String toString() {
        return "Config{" +
                "savePath='" + savePath + '\'' +
                ", workThread='" + workThread + '\'' +
                ", numberOfBytes='" + numberOfBytes + '\'' +
                ", corePoolSize='" + corePoolSize + '\'' +
                ", maximumPoolSize='" + maximumPoolSize + '\'' +
                ", keepAliveTime='" + keepAliveTime + '\'' +
                '}';
    }

    public Config() {
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public int getWorkThread() {
        return workThread;
    }

    public void setWorkThread(int workThread) {
        this.workThread = workThread;
    }

    public int getNumberOfBytes() {
        return numberOfBytes;
    }

    public void setNumberOfBytes(int numberOfBytes) {
        this.numberOfBytes = numberOfBytes;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Config(String savePath, int workThread, int numberOfBytes, int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.savePath = savePath;
        this.workThread = workThread;
        this.numberOfBytes = numberOfBytes;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }
}
