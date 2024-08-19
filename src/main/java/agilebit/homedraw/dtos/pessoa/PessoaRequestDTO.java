package agilebit.homedraw.dtos.pessoa;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PessoaRequestDTO {
	@NotNull(message = "O nome é obrigatório")
	@NotEmpty(message = "O nome não pode ser vazio")
	private String nome;
	
	@NotNull(message = "A renda é obrigatória")
	private BigDecimal renda;
	
	@NotNull(message = "A idade é obrigatória")
	private Integer idade;
	
	@NotNull(message = "O dependente é obrigatório")
	private Boolean dependente;
}
