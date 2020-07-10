package com.fw.core.domain;

import com.fw.core.http.HttpStatus;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author yqf
 */
public class DownloadThread implements Runnable {

    /**
     * 下载文件
     */
    private FileDownload fileDownload;
    /**
     * 多线程读取字节计数变量
     */
    private int count = 0;
    /**
     * 下载起始位置
     */
    private long startIndex;
    /**
     * 下载结束位置
     */
    private long endIndex;
    /***
     * 线程id
     * */
    private int threadId;


    public DownloadThread(int threadId,FileDownload fileDownload, long startIndex, long endIndex) {
        this.threadId = threadId;
        this.fileDownload = fileDownload;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * 获取要下在文件流
     */
    private InputStream getFileStream() {
        try {
            URL url = new URL(fileDownload.getServerPath());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            //支持分段下载
            if (code == HttpStatus.PARTIAL_CONTENT.value()) {
                //返回资源
                System.out.println(Thread.currentThread().getName() + "下载" + startIndex + "-" + endIndex);
                return conn.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 写入文件
     */
    private void writeFileStream(InputStream file) {
        try {
            //随机写文件的时候从哪个位置开始写
            RandomAccessFile raf = new RandomAccessFile(fileDownload.getLocalPath(), "rwd");
            //定位文件
            raf.seek(startIndex);

            int len;

            byte[] buffer = new byte[ThreadPoolConfig.NUMBER_OF_BYTES];

            while ((len = file.read(buffer)) != -1) {

                raf.write(buffer, 0, len);
                count += len;
                this.fileDownload.setWorkFileSize(threadId,count);
            }

            file.close();
            raf.close();
            System.out.println(threadId + "下载结束");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void run() {
        InputStream fileStream = getFileStream();
        if (fileStream != null) {
            writeFileStream(fileStream);
        }

    }
}
