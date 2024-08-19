package agilebit.homedraw.dtos.familia;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class FamiliaRequestDTO {
	private Set<Long> pessoas;
}
