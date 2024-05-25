package com.example.project_dto.exception;

import com.example.project_dto.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceException {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse>checkRunTimeException(AppException appException){
        ErrException errException=appException.getErrException();
        ApiResponse apiResponse=new ApiResponse<>();
        apiResponse.setCode(errException.getCode());
        apiResponse.setMessage(errException.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse>checkValidation(MethodArgumentNotValidException exception){
        String enumKey=exception.getFieldError().getDefaultMessage();
        ErrException errException=ErrException.INVALID_KEY;
        try {
            errException=ErrException.valueOf(enumKey);
        }catch (IllegalArgumentException e){

        }
        ApiResponse apiResponse=new ApiResponse<>();
        apiResponse.setCode(errException.getCode());
        apiResponse.setMessage(errException.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
//    @ExceptionHandler(value = RuntimeException.class)
//    public ResponseEntity<ApiResponse> handlingAutomaticException(RuntimeException exception){
//        ApiResponse apiResponse=new ApiResponse<>();
//        apiResponse.setCode(ErrException.ERR_EXCEPTION.getCode());
//        apiResponse.setMessage(ErrException.ERR_EXCEPTION.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
}
