package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot-blog-rest-api
 * @description: exception handler
 * @author: Yaowen Hu
 * @create: 2022-04-14 12:16
 **/

//表示这个类是个global的异常处理器
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { //这个注解的作用是将错误包装成ResponseEntity返回给client

    // handler specific exceptions
    //统一处理某一类异常，从而能够减少代码重复率和复杂度
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                         WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));  //得到url
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handlerBlogAPIException(BlogAPIException exception,
                                                                WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));  //得到url
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    //global exceptions
    //全局的错误, 处理其他系统自带的Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handlerGlobalException(Exception exception,
                                                               WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));  //得到url
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //spring中和valid注解配合起来使用的检错信息, 如果参数通不过校验返回json形式的报错
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {  //getBindingResult 用于对前端穿进来的参数进行校验，省去了大量的逻辑判断操作; 和valid注解配合起来使用
            String filedName = ((FieldError)error).getField(); // FieldError Encapsulates a field error, that is, a reason for rejecting a specific field value. 用于封装特定字段错误的原因
            String message = error.getDefaultMessage();
            errors.put(filedName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //也还可以使用方法而 ExceptionHandler注解实现相同的效果
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
//                                                                WebRequest webRequest) {
//        Map<String, String> errors = new HashMap<>();
//        exception.getBindingResult().getAllErrors().forEach((error) -> {  //getBindingResult 用于对前端穿进来的参数进行校验，省去了大量的逻辑判断操作; 和valid注解配合起来使用
//            String filedName = ((FieldError)error).getField(); // FieldError Encapsulates a field error, that is, a reason for rejecting a specific field value. 用于封装特定字段错误的原因
//            String message = error.getDefaultMessage();
//            errors.put(filedName, message);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
}
