package agilebit.homedraw.strategies;

import agilebit.homedraw.constants.CriterioTypeEnum;
import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.PessoaEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PontuacaoRendaStrategy implements PontuacaoStrategy {
	@Override
	public int calcularPontuacao(List<PessoaEntity> pessoas, List<CriterioEntity> criterios) {
		BigDecimal rendaTotal = pessoas.stream()
				.map(PessoaEntity::getRenda)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return criterios.stream()
				.filter(criterio -> criterio.getTipo().equals(CriterioTypeEnum.RENDA))
				.filter(criterio -> rendaTotal.compareTo(criterio.getLimiteSuperior()) <= 0
						&& rendaTotal.compareTo(criterio.getLimiteInferior()) >= 0)
				.mapToInt(CriterioEntity::getPontuacao)
				.sum();
	}
}
