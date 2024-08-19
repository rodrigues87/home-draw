package agilebit.homedraw.exceptions.handlers;

import agilebit.homedraw.exceptions.NotFoundCriterioException;
import agilebit.homedraw.exceptions.NotFoundFamiliaException;
import agilebit.homedraw.exceptions.NotFoundPessoaException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		log.error("Invalid Arguments: {} ", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		log.error("MethodArgumentNotValid: {}", ex.getMessage(), ex);
		Map<String, String> errors = handleValidationExceptions(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> onConstraintValidationException(final ConstraintViolationException ex) {
		log.error("onConstraintValidationException: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
		log.error("Illegal State: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<Object> handleIllegalAccessException(IllegalAccessException ex) {
		log.error("Illegal Access: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}
	
	@ExceptionHandler(NotFoundCriterioException.class)
	public ResponseEntity<Object> handleNotFoundCriterioException(NotFoundCriterioException ex) {
		log.error("Criterio não encontrado: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(NotFoundFamiliaException.class)
	public ResponseEntity<Object> handleNotFoundFamiliaException(NotFoundFamiliaException ex) {
		log.error("Familia não encontrada: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(NotFoundPessoaException.class)
	public ResponseEntity<Object> handleNotFoundPessoaException(NotFoundPessoaException ex) {
		log.error("Pessoa não encontrada: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
}
