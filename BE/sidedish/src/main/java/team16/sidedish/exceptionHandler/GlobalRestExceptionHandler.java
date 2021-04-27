package team16.sidedish.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team16.sidedish.dto.response.ApiResult;
import team16.sidedish.exception.NotFoundException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResult<?> exception(Exception ex) {
        return ApiResult.failed(ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiResult<?> notFoundException(NotFoundException ex) {
        return ApiResult.failed(ex);
    }

}
