package com.ansari.expensetrackerapi.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ansari.expensetrackerapi.entity.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ExpenseNotFoundException ex, WebRequest request){
		ErrorObject errorObj = new ErrorObject();
		errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObj.setMsg(ex.getMessage());
		return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
		ErrorObject errorObj = new ErrorObject();
		errorObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObj.setMsg(ex.getMessage());
		return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request){
		ErrorObject errorObj = new ErrorObject();
		errorObj.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorObj.setMsg(ex.getMessage());
		return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,Object> body = new HashMap<String, Object>();
		body.put("statusCode", HttpStatus.BAD_REQUEST);
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		
		body.put("messages",errors);
		
		return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
	}
}
