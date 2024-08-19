package agilebit.homedraw.exceptions;

public class NotFoundFamiliaException extends RuntimeException {
	public NotFoundFamiliaException(Long id) {
		super(String.format("Familia com id %s não encontrada", id));
	}
}
