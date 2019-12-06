package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView badRequest(Exception e){
        log.error("Bad Request! "+e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.setViewName("exceptions/badrequest");

        return mav;
    }

}
