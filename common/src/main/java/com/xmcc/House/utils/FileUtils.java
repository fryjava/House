package com.xmcc.House.utils;

import com.google.common.collect.Lists;
import com.xmcc.House.common.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FileUtils {
    @Value("${file.path}")
    private String filePath;
    @Value("${file.type}")
    private String fileType;
    @Autowired
    private FastDFSUtils fastDFSUtils;

    public List<ResultResponse> upload(MultipartFile[] multipartFiles, String fileType) {
        ArrayList<ResultResponse> urls = Lists.newArrayList();
        for (MultipartFile multipartFile : multipartFiles) {
            ResultResponse upload = upload(multipartFile, fileType);
            urls.add(upload);
        }
        return urls;
    }


    public List<ResultResponse> upload(MultipartFile[] multipartFile) {
        return upload(multipartFile, this.fileType);
    }

    public ResultResponse upload(MultipartFile multipartFile) {
        return upload(multipartFile, this.fileType);
    }


    public ResultResponse upload(MultipartFile multipartFile, String fileType) {
//    文件上传成功后 返回的id(地址)
        String returnId = fastDFSUtils.fastDfs_upload(multipartFile);
     /*   StringBuffer fileUrl = new StringBuffer(filePath);
        if (StringUtils.isNotBlank(fileType)) {
            fileUrl.append(fileType).append("//");
        } else {
            fileUrl.append(this.fileType).append("//");
        }
        String suffix = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
        fileUrl.append(UUIDUtils.uuid()).append("//").append(multipartFile.getOriginalFilename());
        try {
            File file = new File(fileUrl.toString());
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
            }
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error("文件上传异常信息为:{}", e.getMessage());
            e.printStackTrace();
            return ResultResponse.failResponse("文件上传失败");
        }
        String databaseUrl = fileUrl.toString().replace(filePath, "");
        return ResultResponse.successReponse(databaseUrl);*/
        log.info("文件上传返回的Id:{}",returnId);
        returnId=returnId.replace("group1/M00/00/00/","");
        return ResultResponse.successReponse(returnId);
    }
}
