package winby.model;

/**
 * @author winby
 */
public class BaseResultDTO {

    public static final Boolean SUCCESS = true;
    public static final Boolean ERROR = false;

    protected Boolean success;

    protected String resultCode;

    protected String errorDetail;


    public BaseResultDTO() {
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailed() {
        return !success;
    }

    @Override
    public String toString() {
        return "BaseResultDTO{" +
                "success=" + success +
                ", resultCode='" + resultCode + '\'' +
                ", errorDetail='" + errorDetail + '\'' +
                '}';
    }
}
