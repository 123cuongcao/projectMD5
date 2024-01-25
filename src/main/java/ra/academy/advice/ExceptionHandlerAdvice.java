package ra.academy.advice;

import com.google.api.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.academy.exception.*;
import ra.academy.model.dto.response.ErrorResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handlerValidateSignUpForm(MethodArgumentNotValidException e) {
//        Map<String, String> map = new HashMap<>();
//        e.getBindingResult().getFieldErrors().forEach(err ->
//                map.put(err.getField(), err.getDefaultMessage()));
//        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ra.academy.model.dto.response.ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errorsDetails = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errorsDetails.put(error.getField(), error.getDefaultMessage());
        }
        ra.academy.model.dto.response.ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "ValidationFailed", errorsDetails);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
//
//
//    @ExceptionHandler(LoginFailException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String loginFail(LoginFailException e){
//        return e.getMessage();
//    }


    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> loginFail(LoginFailException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> notFoundCategory(NoSuchElementException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CatalogExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> CatalogExist(CatalogExist exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> ProductExist(ProductExist e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> ExistRole(ExistRole e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT,e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ExistWishList(WishListExist e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> UnauthorizedException(UnauthorizedException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED,e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> EmailExistException(EmailExistException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT,e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String PhoneExistException(PhoneExistException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PasswordNotMatchException(PasswordNotMatchException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PasswordExistException(PasswordExistException e) {
        return e.getMessage();
    }

}