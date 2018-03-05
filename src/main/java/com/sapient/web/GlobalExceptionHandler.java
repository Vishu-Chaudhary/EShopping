package com.sapient.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.sapient.dao.IDException;

import javassist.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger=LoggerFactory.getLogger("eshopapp");
	@ExceptionHandler(IDException.class)
	public ModelAndView handleException(IDException ex){
		logger.error(ex.getMessage());
//		logger.error(ex.printStackTrace());
		return new ModelAndView("MyErr", "err",ex.getMessage());
		
	}
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleException(NotFoundException ex){
		logger.error(ex.getMessage());
		return new ModelAndView("MyErr", "err",ex.getMessage());
	}
	@ExceptionHandler(IOException.class)
	public ModelAndView handleException(IOException ex){
		logger.error(ex.getMessage());
		return new ModelAndView("MyErr", "err",ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex){
		logger.error(ex.getMessage());
		return new ModelAndView("MyErr", "err",ex.getMessage());
	}

}
