package com.fw.domain;

import java.io.Serializable;

/**
 * @author yqf
 */
public class Config implements Serializable {

    private String savePath;

    private String workThread;

    private String numberOfBytes;

    private String corePoolSize;

    private String maximumPoolSize;

    private String keepAliveTime;

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

    public String getWorkThread() {
        return workThread;
    }

    public void setWorkThread(String workThread) {
        this.workThread = workThread;
    }

    public String getNumberOfBytes() {
        return numberOfBytes;
    }

    public void setNumberOfBytes(String numberOfBytes) {
        this.numberOfBytes = numberOfBytes;
    }

    public String getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(String corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public String getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(String maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public String getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(String keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Config(String savePath, String workThread, String numberOfBytes, String corePoolSize, String maximumPoolSize, String keepAliveTime) {
        this.savePath = savePath;
        this.workThread = workThread;
        this.numberOfBytes = numberOfBytes;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }
}
