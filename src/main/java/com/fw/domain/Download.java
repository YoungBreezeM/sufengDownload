package com.fw.domain;

import java.io.Serializable;

/**
 * @author yqf
 */
public class Download implements Serializable {
    /**
     * 下载服务器地址
     * */
    private String serverPath;
    /**
     * 下载本地地址
     * */
    private String localPath;

    @Override
    public String toString() {
        return "DownLoad{" +
                "serverPath='" + serverPath + '\'' +
                ", localPath='" + localPath + '\'' +
                ", numberOfBytes=" + numberOfBytes +
                '}';
    }

    public Download(String serverPath, String localPath, int numberOfBytes) {
        this.serverPath = serverPath;
        this.localPath = localPath;
        this.numberOfBytes = numberOfBytes;
    }

    /**
     * 每次读取字节数
     */
    private int numberOfBytes;

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public int getNumberOfBytes() {
        return numberOfBytes;
    }

    public void setNumberOfBytes(int numberOfBytes) {
        this.numberOfBytes = numberOfBytes;
    }
}
