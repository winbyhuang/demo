package winby.model;

import winby.enums.BaseEnum;
import winby.enums.ResultType;

import java.io.Serializable;

public class ApiResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object code;	//结果码Integer/String
	private Object msg;	//描述 //string
	private Object result;		//返回数据
	
	/**
	 * 构建成功的响应并设置响应数据
	 * @param data
	 * @return
	 */
	public static ApiResultEntity success(Object data) {
		ApiResultEntity apiResponse = new ApiResultEntity();
		apiResponse.setCode(ResultType.SUCCESS.getCode());
		apiResponse.setResult(data);
		return apiResponse;
	}
	
	/**
	 * 构建失败的响应
	 * @param flag
	 * @param message
	 * @return
	 */
	public static ApiResultEntity fail(Object flag, String message) {
		ApiResultEntity apiResponse = new ApiResultEntity();
		apiResponse.setCode(flag);
		apiResponse.setMsg(message);
		return apiResponse;
	}
	
	/**
	 * 构建失败的响应
	 * @param baseEnum
	 * @return
	 */
	public static ApiResultEntity fail(BaseEnum baseEnum) {
		ApiResultEntity apiResponse = new ApiResultEntity();
		if(null == baseEnum){
			baseEnum = ResultType.FAIL;
		}
		apiResponse.setCode(baseEnum.getCode());
		apiResponse.setMsg(baseEnum.getValue());
		return apiResponse;
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
