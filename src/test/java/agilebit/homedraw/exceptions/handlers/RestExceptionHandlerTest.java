package agilebit.homedraw.exceptions.handlers;

import agilebit.homedraw.exceptions.NotFoundCriterioException;
import agilebit.homedraw.exceptions.NotFoundFamiliaException;
import agilebit.homedraw.exceptions.NotFoundPessoaException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestExceptionHandlerTest {
	
	private RestExceptionHandler restExceptionHandler;
	private WebRequest webRequest;
	
	@BeforeEach
	void setUp() {
		restExceptionHandler = new RestExceptionHandler();
		webRequest = mock(WebRequest.class);
	}
	
	@Test
	void handleIllegalArgumentExceptionReturnsBadRequest() {
		IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
		
		ResponseEntity<Object> response = restExceptionHandler.handleIllegalArgumentException(exception);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid argument", response.getBody());
	}
	
	@Test
	void handleMethodArgumentNotValidReturnsBadRequest() {
		MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
		
		BindingResult bindingResult = mock(BindingResult.class);
		when(exception.getBindingResult()).thenReturn(bindingResult);
		
		ResponseEntity<Object> response = restExceptionHandler.handleMethodArgumentNotValid(exception, null, HttpStatus.BAD_REQUEST, webRequest);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void onConstraintValidationExceptionReturnsBadRequest() {
		ConstraintViolationException exception = new ConstraintViolationException("Constraint violation", null);
		
		ResponseEntity<Object> response = restExceptionHandler.onConstraintValidationException(exception);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Constraint violation", response.getBody());
	}
	
	@Test
	void handleIllegalStateExceptionReturnsBadRequest() {
		IllegalStateException exception = new IllegalStateException("Illegal state");
		
		ResponseEntity<Object> response = restExceptionHandler.handleIllegalStateException(exception);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Illegal state", response.getBody());
	}
	
	@Test
	void handleIllegalAccessExceptionReturnsForbidden() {
		IllegalAccessException exception = new IllegalAccessException("Illegal access");
		
		ResponseEntity<Object> response = restExceptionHandler.handleIllegalAccessException(exception);
		
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		assertEquals("Illegal access", response.getBody());
	}
	
	@Test
	void handleNotFoundCriterioExceptionReturnsNotFound() {
		NotFoundCriterioException exception = new NotFoundCriterioException(1L);
		
		ResponseEntity<Object> response = restExceptionHandler.handleNotFoundCriterioException(exception);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Criterio com id 1 não encontrado", response.getBody());
	}
	
	@Test
	void handleNotFoundFamiliaExceptionReturnsNotFound() {
		NotFoundFamiliaException exception = new NotFoundFamiliaException(1L);
		
		ResponseEntity<Object> response = restExceptionHandler.handleNotFoundFamiliaException(exception);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Familia com id 1 não encontrada", response.getBody());
	}
	
	@Test
	void handleNotFoundPessoaExceptionReturnsNotFound() {
		NotFoundPessoaException exception = new NotFoundPessoaException(1L);
		
		ResponseEntity<Object> response = restExceptionHandler.handleNotFoundPessoaException(exception);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Pessoa com id 1 não encontrada", response.getBody());
	}
}