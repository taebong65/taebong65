package kr.co.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import kr.co.controller.SampleController;

@ControllerAdvice
// @ControllerAdvice는 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리 
public class CommonExceptionAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	//@ExceptionHandler는 해당 메서드가 () 들어가는 예외 타입을 처리 
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		logger.error("Excepiton......"+ex.getMessage());
		model.addAttribute("exception",ex);
		logger.error(""+model);
		
		return "error_page";
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handel404(NoHandlerFoundException ex) {
		return "custom404";
	}
	
}
