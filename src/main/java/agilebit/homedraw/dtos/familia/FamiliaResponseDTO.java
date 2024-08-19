package agilebit.homedraw.dtos.familia;

import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class FamiliaResponseDTO {
	private Long id;
	private Integer pontuacao;
	private Set<PessoaResponseDTO> pessoas;
}
