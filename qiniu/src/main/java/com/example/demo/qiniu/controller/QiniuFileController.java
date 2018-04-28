package com.example.demo.qiniu.controller;

import com.example.demo.qiniu.service.QiniuFileService;
import com.qiniu.common.QiniuException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import winby.model.ApiResultEntity;

@RestController
@RequestMapping(value = "QiniuFileController", produces = "application/json;charset=UTF-8")
public class QiniuFileController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QiniuFileService qiniuFileService;

    /**
     * @param
     * @return
     * @author winby <br/>
     * @Title: getToken  <br/>
     * @Description: 获取token <br/>
     * @version V1.0  <br/>
     */
    @RequestMapping(value = "getToken", method = {RequestMethod.POST})
    public ApiResultEntity getToken() {
        return ApiResultEntity.success(qiniuFileService.getToken());
    }

    /**
     * @param
     * @return
     * @author winby <br/>
     * @Title: encodeVideo  <br/>
     * @Description: 视频转码 <br/>
     * @version V1.0  <br/>
     */
    @RequestMapping(value = "encodeVideo", method = {RequestMethod.POST})
    public ApiResultEntity encodeVideo(String fileName) throws QiniuException {
        return ApiResultEntity.success(qiniuFileService.encodeVideo(fileName));
    }

    /**
     * @param fileName
     * @return
     * @author winby <br/>
     * @Title: encodeVideo  <br/>
     * @Description: 删除文件 <br/>
     * @version V1.0  <br/>
     */
    @RequestMapping(value = "removeFile", method = {RequestMethod.POST})
    public ApiResultEntity removeFile(String fileName) throws QiniuException {
        return ApiResultEntity.success(qiniuFileService.removeFile(fileName));
    }
}
