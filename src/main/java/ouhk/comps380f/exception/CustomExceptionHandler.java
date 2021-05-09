package ouhk.comps380f.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public ModelAndView handlerException(CustomException throwable, HttpServletRequest req){
        logger.error("Request: " + req.getRequestURL() + " raised " + throwable);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", throwable.getMessage());
        modelAndView.addObject("cls", throwable.getClass().getSimpleName());
        return modelAndView;
    }


}
