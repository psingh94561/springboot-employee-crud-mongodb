	package springboot.employee.mongodb.exception;
	
	import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.MethodArgumentNotValidException;
	import org.springframework.web.bind.annotation.ControllerAdvice;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.context.request.WebRequest;
	import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
	
	@ControllerAdvice
	public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
			List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
			ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),details);
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
	
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
			List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
			ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), details);
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@Override
		  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		      HttpHeaders headers, HttpStatus status, WebRequest request) {
			List<String> details = new ArrayList<>();
	        details.add(ex.getMessage());
		    ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
		    		details);
		    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
		  } 
	}
