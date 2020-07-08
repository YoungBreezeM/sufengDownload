package com.fw.domain;



import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yqf
 */
public class MultithreadingDownLoad {
    /**
     * 同时下载的线程数
     */
    private int threadCount;

    private Download downLoad;

    private ThreadPoolExecutor threadPoolExecutor;

    private Long fileLength;

    public MultithreadingDownLoad(ThreadPoolExecutor threadPoolExecutor,int threadCount, Download downLoad) {
        this.threadCount = threadCount;
        this.downLoad = downLoad;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void executeDownLoad() {

        try {
            URL url = new URL(downLoad.getServerPath());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod(HttpMethod.GET);

            int code = conn.getResponseCode();

            if (code == HttpStatus.OK.value()) {
                int length = conn.getContentLength();
                if (length != -1) {
                    fileLength = (long)length;
                    blockDownload(length);
                } else {
                    HttpHeaderIterator httpHeaderIterator = new HttpHeaderIterator();

                    Map<String, String> header = httpHeaderIterator.getHeader(conn);

                    String contentLength = header.get(HttpHeader.CONTENT_LENGTH);

                    fileLength = Long.parseLong(contentLength);

                    blockDownload(Long.parseLong(contentLength));
                }

            } else {
                System.out.println("请求异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 创建文件
     * */
    private void createFile(Long length) throws IOException {
        //创建一个临时文件存储下载的数据
        RandomAccessFile raf = new RandomAccessFile(downLoad.getLocalPath(), "rwd");
        //设置临时文件的大小
        raf.setLength(length);
        raf.close();
        System.out.println("文件总长度:" + length + "字节(B)");
    }

    private void createFile(int length) throws IOException {
        //创建一个临时文件存储下载的数据
        RandomAccessFile raf = new RandomAccessFile(downLoad.getLocalPath(), "rwd");
        //设置临时文件的大小
        raf.setLength(length);
        raf.close();
        System.out.println("文件总长度:" + length + "字节(B)");
    }

    /**
     * 分段下载
     */
    private void blockDownload(int length) throws IOException {
        //createFile(length);
        //分割文件
        int blockSize = length / threadCount;

        for (int threadId = 0; threadId <=threadCount; threadId++) {
            int startIndex = threadId * blockSize;
            int endIndex = startIndex + blockSize - 1;
            if (threadId == threadCount) {
                //最后一个线程下载的长度稍微长一点
                endIndex = length;
            };


             DownloadThread downloadThread = new DownloadThread(downLoad, startIndex, endIndex);

            threadPoolExecutor.execute(downloadThread);
        }


    }

    private void blockDownload(Long length) throws IOException {

        //createFile(length);
        //分割文件
        Long blockSize = length / threadCount;

        for (int threadId = 0; threadId <=threadCount; threadId++) {
            Long startIndex = threadId * blockSize;
            Long endIndex = startIndex + blockSize - 1;
            if (threadId == threadCount) {
                //最后一个线程下载的长度稍微长一点
                endIndex = length;
            };

            DownloadThread downloadThread = new DownloadThread(downLoad, startIndex, endIndex);

            threadPoolExecutor.execute(downloadThread);
        }

    }

    public Long getFileLength(){
        return fileLength;
    }

}
