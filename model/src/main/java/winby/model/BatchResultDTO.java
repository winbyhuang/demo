package winby.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winby
 */
public class BatchResultDTO<T> extends BaseResultDTO {

	/**
	 * 失败列表
	 */
	private Map<Long, String> faileds = new HashMap<>();

	private List<T> module;

	public List<T> getModule() {
		return module;
	}

	public void setModule(List<T> module) {
		this.module = module;
	}

	/**
	 * 添加操作失败记录到失败列表
	 *
	 * @param
	 * @param errorCode
	 */
	public void addFailed(Long id, String errorCode) {
		setResultCode(errorCode);
		faileds.put(id, errorCode);
	}

	/**
	 * 获取操作失败记录以及对应失败的原因
	 *
	 * @return
	 */
	public Map<Long, String> getFaileds() {
		return faileds;
	}

	public BatchResultDTO() {
	}

	public BatchResultDTO(Boolean success) {
		super.success = success;
	}

	@Override
	public String toString() {
		return "BatchResultDTO{" +
				"faileds=" + faileds +
				", module=" + module +
				", success=" + success +
				", resultCode='" + resultCode + '\'' +
				", errorDetail='" + errorDetail + '\'' +
				'}';
	}
}
