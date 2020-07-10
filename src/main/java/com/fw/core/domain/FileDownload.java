package com.fw.core.domain;

import java.io.Serializable;

/**
 * @author yqf
 */
public class FileDownload implements Serializable {
    /**下载文件路径*/
    private String serverPath;
    /**文件保存路径*/
    private String localPath;
    /**下载中文件大小*/
    private long[] workFileSize;
    /**文件大小*/
    private long fileLength =0;

    public FileDownload(String serverPath, String localPath,int block) {
        this.serverPath = serverPath;
        this.workFileSize = new long[block+1];
        this.localPath = localPath;
    }

    public String getServerPath() {
        return serverPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public synchronized void setWorkFileSize(int threadId,long size){
        workFileSize[threadId] = size;
    }

    public long[] getWorkFileSize() {
        return workFileSize;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    @Override
    public String toString() {
        return "FileDownload{" +
                "serverPath='" + serverPath + '\'' +
                ", localPath='" + localPath + '\'' +
                '}';
    }




}
