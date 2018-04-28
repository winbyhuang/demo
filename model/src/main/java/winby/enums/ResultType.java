package winby.enums;


public enum ResultType implements BaseEnum
{
	SUCCESS(1, "成功执行"),
	FAIL(0, "请求发生异常"),
	MISSING(100300, "数据不存在或者已经被删除"),
	ERROR_SECRET(100400, "秘钥错误"),
	PARAM_EMPTY(100501, "参数为空"),
	PARAM_TYPE_MISMATCH(100502, "参数格式错误"),
	PARAM_INVALID(100503, "参数验证失败"),
	HTTP_MEDIATYPE_NOT_SUPPORTED(100601, "请求格式不支持"),
	HTTP_REQUEST_METHOD_NOT_SUPPORTED(100602, "请求方法不支持"),
	HTTP_MESSAGE_NOT_READABLE(100603, "请求消息读取错误"),
	HTTP_UPLOAD_SIZE_EXCEEDED(100604, "文件超出规定大小或格式不对"),
	HTTP_UNEXPECTED_SERVER_ERROR(100605, "发生不可预料的错误"),
	LOGIN_TIMEOUT(100700, "没有登录或登录超时"),
	AUTHPERMISSION_DENIED(100801, "没有访问权限"),
	ACCESS_DENIED(100802, "访问拒绝"),
	AUTHPERMISSION_MISSING(100803, "没有身份认证"),
	OLD_PASSWORD_WRONG(100901, "原密码输入错误"),
	ENSURE_PASSWORD_DIFFERENT(100902, "确认密码与原密码不一致"),
	OPERATE_OWN_ACCOUNT(101000, "不能操作本人账号"),
	MULTI_DATA(101101, "相同主键存在多条数据"),
	REPEAT_DATA(101102, "数据重复"),
	FILE_NOT_FOUND(101201, "文件没有找到"),
	FILE_BAD_FORMAT(101202, "文件格式无效"),
	FILE_UPLOAD_FAIL(101203, "文件上传失败"),
	FILE_NOT_SELECTED(101204, "没有选择文件上传"),
	FILE_BUSSINESS_TYPE_NOT_FOUND(101205, "请确定文件类型"),
	FILE_PACK_EXCEPTION(101206, "打包文件出错"),
	FILE_UNPACK_EXCEPTION(101207, "解压文件出错"),
	BAN_SUPER_ADMIN_USER(101301, "不能操作超级管理员用户"),
	BAN_SUPER_ADMIN_ROLE(101302, "不能操作超级管理员角色"),
	ACCESS_OUT_OF_RANGE(101401, "访问数据超出权限范围");

	private Integer code;

	private String value;

	private ResultType(Integer code, String value) {
		this.code = code;
		this.value = value;
	}


	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}
}
