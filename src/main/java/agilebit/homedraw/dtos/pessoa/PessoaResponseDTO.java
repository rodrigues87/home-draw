package agilebit.homedraw.dtos.pessoa;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PessoaResponseDTO {
	private Long id;
	private String nome;
	private BigDecimal renda;
	private Integer idade;
	private Boolean dependente;
}
