package agilebit.homedraw.strategies;

import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.PessoaEntity;

import java.util.List;

public interface PontuacaoStrategy {
	int calcularPontuacao(List<PessoaEntity> pessoas, List<CriterioEntity> criterios);
}
