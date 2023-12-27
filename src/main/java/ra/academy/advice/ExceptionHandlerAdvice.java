package ra.academy.advice;

import com.google.api.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.academy.exception.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidateSignUpForm(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err ->
                map.put(err.getField(), err.getDefaultMessage()));
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginFail(LoginFailException e){
        return e.getMessage();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundCategory(NoSuchElementException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(CatalogExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String CatalogExist(CatalogExist exception){
        return exception.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ProductExist(ProductExist e){
        return e.getMessage();
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ExistRole(ExistRole e){
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ExistWishList(WishListExist e){
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String UnauthorizedException(UnauthorizedException e){
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String EmailExistException(EmailExistException e){
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PhoneExistException(PhoneExistException e){
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PasswordNotMatchException(PasswordNotMatchException e){
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PasswordExistException(PasswordExistException e){
        return e.getMessage();
    }

}