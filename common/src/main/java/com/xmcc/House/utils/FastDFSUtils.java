package com.xmcc.House.utils;

import com.xmcc.House.exception.CustomExcepiton;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FastDFSUtils {
    @Value("${fry.fastdfs.tracker_servers}")
    private String tracker_servers;
    @Value("${fry.fastdfs.connect_timeout_in_seconds}")
    private Integer connecttime;
    @Value("${fry.fastdfs.network_timeout_in_seconds}")
    private Integer networktime;
    @Value("${fry.fastdfs.charset}")
    private String charset;

    public void initFdfsConfig() {
        try {
            ClientGlobal.initByTrackers(tracker_servers);
            ClientGlobal.setG_connect_timeout(connecttime);
            ClientGlobal.setG_network_timeout(networktime);
            ClientGlobal.setG_charset(charset);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
            CustomExcepiton.cast(e.getMessage());
        }
    }
    public  String fastDfs_upload(MultipartFile file){
        if(file==null){
            CustomExcepiton.cast("上传文件为空");
        }
        try {
            initFdfsConfig();
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storeStorage);
            byte[] bytes = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            String suffix = StringUtils.substringAfterLast(originalFilename, ".");
            String returnId = storageClient1.upload_file1(bytes, suffix, null);
             return  returnId ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return  null ;
    }
}
