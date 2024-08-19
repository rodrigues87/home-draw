package agilebit.homedraw.exceptions;

public class NotFoundPessoaException extends RuntimeException {
	public NotFoundPessoaException(Long id) {
		super(String.format("Pessoa com id %s não encontrada", id));
	}
}
