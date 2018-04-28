package com.example.demo.qiniu.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

public interface QiniuFileService {
	/**
	 * @author winby <br/>
	 * @Title: encodeVideo  <br/>
	 * @Description: 视频转码 <br/>
	 * @param fileName
	 * @return
	 * @version V1.0  <br/>
	 */
	String  encodeVideo(String fileName) throws QiniuException;
	/**
	 * @author winby <br/>
	 * @Title: removeFile  <br/>
	 * @Description: 删除文件 <br/>
	 * @param
	 * @return
	 * @version V1.0  <br/>
	 */
	Response removeFile(String fileName) throws QiniuException;
	/**
	 * @author winby <br/>
	 * @Title: getToken  <br/>
	 * @Description: 获取token <br/>
	 * @return
	 * @version V1.0  <br/>
	 */
	String  getToken();


}








