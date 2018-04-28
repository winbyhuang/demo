package winby.exception;

/**
 * @author Winby
 * @date 2017/11/30  15:07
 * @update
 * @since v1.0.0
 */
public class GenericException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GenericException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public GenericException(String msg) {
        this.msg = msg;
    }
}
