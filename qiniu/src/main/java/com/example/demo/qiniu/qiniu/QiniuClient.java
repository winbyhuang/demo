package com.example.demo.qiniu.qiniu;//package com.example.demo.common.redis;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;

import java.io.UnsupportedEncodingException;

/**
 * Created by huguoju on 2016/12/29.
 */
public class QiniuClient {
    static String bucket = "aaaa";
    static String type = "mp4";
    static String ss = "avthumb/" + type + "/vcodec/libx264/acodec/libmp3lame/h264Crf/28/r/10/ab/64k/s/1280x720";

    public static String getToken() {


        String accessKey = "W8M_ZnBSriPFRoMBcA71PICb0B9965I0Ep1aXt5Z";
        String secretKey = "KEzb_w31qcTzNc_AJ6MiAZGKRS6s1jtzJ5YrOxoR";

        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        String saveMp4Entry = String.format("%s:" + ss + "." + type, bucket);
//        String saveJpgEntry = String.format("%s:vframe_test_target.jpg", bucket);
        String avthumbMp4Fop = String.format(ss + "|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
//        String avthumbMp4Fop = String.format("avthumb/mp4/vcodec/libx264|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
//        String avthumbMp4Fop = String.format("avthumb/mp4/vcodec/libx264/acodec/libmp3lame/vb/60k|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
//        String vframeJpgFop = String.format("vframe/jpg/offset/1|saveas/%s", UrlSafeBase64.encodeToString(saveJpgEntry));
//将多个数据处理指令拼接起来
        String persistentOpfs = StringUtils.join(new String[]{
                avthumbMp4Fop
//                , vframeJpgFop
        }, ";");
        putPolicy.put("persistentOps", persistentOpfs);
//数据处理队列名称，必填
        putPolicy.put("persistentPipeline", "winby");
//数据处理完成结果通知地址
//        putPolicy.put("persistentNotifyUrl", "http://api.example.com/qiniu/pfop/notify");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, "b.mp4", expireSeconds, putPolicy);
        System.out.println(upToken);
        return upToken;
    }

    public static Response delete() {
        String accessKey = "W8M_ZnBSriPFRoMBcA71PICb0B9965I0Ep1aXt5Z";
        String secretKey = "KEzb_w31qcTzNc_AJ6MiAZGKRS6s1jtzJ5YrOxoR";

        Auth auth = Auth.create(accessKey, secretKey);
        Configuration cfg = new Configuration(Zone.zone0());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            Response response = bucketManager.delete(bucket, "a.mp4");
            return response;
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dosss() throws UnsupportedEncodingException, QiniuException {
        String accessKey = "W8M_ZnBSriPFRoMBcA71PICb0B9965I0Ep1aXt5Z";
        String secretKey = "KEzb_w31qcTzNc_AJ6MiAZGKRS6s1jtzJ5YrOxoR";

        Auth auth = Auth.create(accessKey, secretKey);
        Configuration cfg = new Configuration(Zone.zone0());
        OperationManager operationManager = new OperationManager(auth, cfg);


//        String filePath = "http://ogtoywd4d.bkt.clouddn.com/resource/gogopher.jpg";
//        StringBuffer sb = new StringBuffer();
//        sb.append("mkzip/2/").append("encoding/" + UrlSafeBase64.encodeToString("utf-8") + "/");
//        sb.append("/url/").append("encoding/" + UrlSafeBase64.encodeToString("utf-8") + "/")
//                .append(UrlSafeBase64.encodeToString(filePath))
//                .append("/alias/" + UrlSafeBase64.encodeToString(filePath.getBytes("utf-8")));//使用别名，防止七牛云多文件压缩中文文件乱码
//        return sb.toString();
     String filePath1 = "http://oolktgssa.bkt.clouddn.com/1.js";
     String filePath2 = "http://oolktgssa.bkt.clouddn.com/2.js";
       String encodedfile1 = UrlSafeBase64.encodeToString(filePath1);
//        String encodedfile1 = UrlSafeBase64.encodeToString("http://oolktgssa.bkt.clouddn.com/1.js".getBytes("utf-8"));
        String encodedfileas1 = "/alias/" + UrlSafeBase64.encodeToString(filePath1);
//        String encodedfile2 = UrlSafeBase64.encodeToString("http://oolktgssa.bkt.clouddn.com/2.js");
//        String encodedfileas2 = "/alias/" + UrlSafeBase64.encodeToString(filePath2.getBytes("utf-8"));
        String enco = "encoding/" + UrlSafeBase64.encodeToString("utf-8") + "/";
        String enco1 = "encoding/utf-8/";

//        String saveMp4Entry = String.format("%s:11.zip", bucket);
////        String saveJpgEntry = String.format("%s:vframe_test_target.jpg", bucket);
//        String avthumbMp4Fop = String.format("|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));

        String fops = "mkzip/2/"+enco1+"url/"+encodedfile1+encodedfileas1;
        System.out.println(fops);
        String ss = operationManager.pfop(bucket,"1.js",fops,"winby",true);
        System.out.println(ss);
        return ss;
    }

    public static String change() throws UnsupportedEncodingException, QiniuException {
        String accessKey = "W8M_ZnBSriPFRoMBcA71PICb0B9965I0Ep1aXt5Z";
        String secretKey = "KEzb_w31qcTzNc_AJ6MiAZGKRS6s1jtzJ5YrOxoR";

        Auth auth = Auth.create(accessKey, secretKey);
        Configuration cfg = new Configuration(Zone.zone0());
        OperationManager operationManager = new OperationManager(auth, cfg);


//        String filePath1 = "http://oolktgssa.bkt.clouddn.com/1.js";
//        String filePath2 = "http://oolktgssa.bkt.clouddn.com/2.js";
//        String encodedfile1 = UrlSafeBase64.encodeToString(filePath1);
//        String encodedfileas1 = "/alias/" + UrlSafeBase64.encodeToString(filePath1);
//        String enco = "encoding/" + UrlSafeBase64.encodeToString("utf-8") + "/";
//        String enco1 = "encoding/utf-8/";

        StringMap putPolicy = new StringMap();
        String saveMp4Entry = String.format("%s:" + ss + "." + type, bucket);
        String avthumbMp4Fop = String.format(ss + "|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
        String persistentOpfs = StringUtils.join(new String[]{
                avthumbMp4Fop
        }, ";");


//        String fops = ss;
        System.out.println(persistentOpfs);
        String ss = operationManager.pfop(bucket,"a.mp4",persistentOpfs,"winby",true);
        System.out.println(ss);
        return ss;
    }

    public static Response put() {
        try {
            Configuration cfg = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(cfg);
            String localFilePath = "F:\\a.mp4";
            String key = "a.mp4";
            Response response = uploadManager.put(localFilePath, key, getToken());

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return response;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            change();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (QiniuException e) {
            e.printStackTrace();
        }
//        Response response = put();
//        Response response =delete();
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(response));
    }
}

