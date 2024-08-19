package agilebit.homedraw.exceptions;

public class NotFoundCriterioException extends RuntimeException {
	public NotFoundCriterioException(Long id) {
		super(String.format("Criterio com id %s não encontrado", id));
	}
}
