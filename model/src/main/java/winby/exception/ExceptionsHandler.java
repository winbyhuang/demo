package winby.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import winby.model.ResultEntity;

/**
 * @author Winby
 * @date 2017/11/30  15:09
 * @update
 * @since v1.0.0
 */
@ControllerAdvice
public class ExceptionsHandler {
    private static final String SYSTEM_ERROR = "系统出错!";

    @ExceptionHandler(GenericException.class)
    public ResultEntity customGenericExceptionHnadler(GenericException exception) {
        ResultEntity resultEntity = new ResultEntity(ResultEntity.ERROR);
        resultEntity.setMsg(exception.getMsg());
        return resultEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResultEntity allExceptionHandler(Exception exception) {
        ResultEntity resultEntity = new ResultEntity(ResultEntity.ERROR);
        resultEntity.setMsg(SYSTEM_ERROR);
        return resultEntity;
    }
}
