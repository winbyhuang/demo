package winby.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: ResultDTO
 * @Description: 当对象返回
 * @author
 * @date
 */
public class ResultDTO<T> extends BaseResultDTO implements Serializable{

	private static final long serialVersionUID = -1654503515179180525L;

	protected T module;

	/**
	 * 保存详细的校验错误信息
 	 */
	protected Map<String, String> checkErrorInfo;


	public ResultDTO() {
	}
	
	public ResultDTO(Boolean success) {
		super.success = success;
	}

	public ResultDTO(T module) {
		this.module = module;
	}

	public T getModule() {
		return module;
	}

	public void setModule(T module) {
		this.module = module;
	}

	public void addCheckErrorInfo(String code, String message) {
		checkErrorInfo.put(code, message);
	}

	public void addCheckErrorInfos(Map<String, String> infos) {
		checkErrorInfo.putAll(infos);
	}

	public Map<String, String> getCheckErrorInfo() {
		return checkErrorInfo;
	}

	public static <T> ResultDTO<T> getResult() {
		return new ResultDTO<T>();
	}

	@Override
	public String toString() {
		return "ResultDTO{" +
				"module=" + module +
				", checkErrorInfo=" + checkErrorInfo +
				", success=" + success +
				", resultCode='" + resultCode + '\'' +
				", errorDetail='" + errorDetail + '\'' +
				'}';
	}
}
