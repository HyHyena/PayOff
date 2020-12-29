package ru.dreamteam.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.dreamteam.models.Error;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlingController {

/*  I know this is bad practice, but I did it for the sake of new features and as a small exception handler.
    A typical project should have multiple exception handlers. For db, wrong requests, etc. */
    // btw I fix that @Y
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> exceptionHandler(HttpServletRequest req, Exception ex){
          log.error("Your application is having a problem. The event: " + ex.getLocalizedMessage());
          log.error("Bad class that threw exception: " + ex.getClass());
          log.error("Someone tried to connect to this uri: " + req.getRequestURI() + " with " +  req.getMethod() + " method");
          return new ResponseEntity<>(Error.builder().message("error occurred").build(), HttpStatus.OK);
    }

}
