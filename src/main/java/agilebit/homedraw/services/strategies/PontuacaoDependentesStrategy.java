package agilebit.homedraw.services.strategies;

import agilebit.homedraw.constants.CriterioTypeEnum;
import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.PessoaEntity;
import agilebit.homedraw.utils.AgeCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PontuacaoDependentesStrategy implements PontuacaoStrategy {
	@Override
	public int calcularPontuacao(List<PessoaEntity> pessoas, List<CriterioEntity> criterios) {
		BigDecimal numeroDependentes = BigDecimal.valueOf(pessoas.stream()
				.filter(pessoa -> pessoa.getDependente() && AgeCalculator.isOver18(pessoa.getDataDeNascimento()))
				.count());
		
		return criterios.stream()
				.filter(criterio -> criterio.getTipo().equals(CriterioTypeEnum.DEPENDENTES))
				.filter(criterio -> (criterio.getLimiteSuperior() != null
						&& numeroDependentes.compareTo(criterio.getLimiteInferior()) >= 0
						&& numeroDependentes.compareTo(criterio.getLimiteSuperior()) <= 0)
						|| (criterio.getLimiteSuperior() == null
						&& numeroDependentes.compareTo(criterio.getLimiteInferior()) >= 0))
				.mapToInt(CriterioEntity::getPontuacao)
				.sum();
	}
}
