package agilebit.homedraw.dtos.criterio;

import agilebit.homedraw.constants.CriterioTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CriterioRequestDTO {
	private String descricao;
	private CriterioTypeEnum tipo;
	private BigDecimal limiteInferior;
	private BigDecimal limiteSuperior;
	private Integer pontuacao;
}
