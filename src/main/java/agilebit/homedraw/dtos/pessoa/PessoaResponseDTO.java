package agilebit.homedraw.dtos.pessoa;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PessoaResponseDTO {
	private Long id;
	private String nome;
	private BigDecimal renda;
	private LocalDate dataDeNascimento;
	private Boolean dependente;
}
