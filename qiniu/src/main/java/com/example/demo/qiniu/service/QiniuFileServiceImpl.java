package com.example.demo.qiniu.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 项目名称：dpqc-parent-manager-file <br/>
 * 类名称：QiniuFileServiceImpl <br/>
 * 类描述： 文件路径<br/>
 * 创建人：winby <br/>
 * 创建时间：2017年4月26日 下午3:08:26  <br/>
 *
 * @version V1.0   <br/>
 */
@Service
public class QiniuFileServiceImpl implements QiniuFileService {
    private Logger logger = LoggerFactory.getLogger(getClass());


    private static final String ACCESS_KEY = "W8M_ZnBSriPFRoMBcA71PICb0B9965I0Ep1aXt5Z";
    private static final String SECRET_KEY = "KEzb_w31qcTzNc_AJ6MiAZGKRS6s1jtzJ5YrOxoR";
    private static final String OLD_BUCKET = "aaaa";
    private static final String NEW_BUCKET = "tttt";
    private static final String PIPELINE = "winby";
    private static final String VIDEO_ENCODE = "avthumb/mp4/vcodec/libx264/acodec/libmp3lame/h264Crf/28/r/10/ab/64k/s/1920x1080";
    private static final String VIDEO_NOTIFY_URL = "http://baidu.com";
    private static final String VIDEO_TYPE = ".mp4";


    @Override
    public String encodeVideo(String fileName) throws QiniuException {
        fileName = fileName.split("\\.")[0];
        OperationManager operationManager = getOperationManager();
        String saveMp4Entry = String.format("%s:" + fileName + VIDEO_TYPE, NEW_BUCKET);
        String avthumbMp4Fop = String.format(VIDEO_ENCODE + "|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
        String persistentOpfs = StringUtils.join(new String[]{
                avthumbMp4Fop
        }, ";");
//        String result = operationManager.pfop(OLD_BUCKET, fileName + VIDEO_TYPE, persistentOpfs, PIPELINE, VIDEO_NOTIFY_URL, true);
        String result = operationManager.pfop(OLD_BUCKET, fileName + VIDEO_TYPE, persistentOpfs, PIPELINE, true);
        Response response = removeFile(fileName+".mp4");
//        response.statusCode != 200
//        logger.debug(persistentOpfs, result);
        return null;
    }

    @Override
    public Response removeFile(String fileName) throws QiniuException {
        BucketManager bucketManager = getBucketManager();
        return bucketManager.delete(OLD_BUCKET, fileName);
    }

    @Override
    public String getToken() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(OLD_BUCKET);
    }


    private OperationManager getOperationManager() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Configuration cfg = new Configuration(Zone.zone0());
        return new OperationManager(auth, cfg);
    }

    private BucketManager getBucketManager() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Configuration cfg = new Configuration(Zone.zone0());
        return new BucketManager(auth, cfg);
    }
}
