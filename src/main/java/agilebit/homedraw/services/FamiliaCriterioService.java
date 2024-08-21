package agilebit.homedraw.services;

import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.entities.PessoaEntity;

import java.util.List;
import java.util.Set;

public interface FamiliaCriterioService {
	void sincronizarCriterios();
	
	void updatePontuacaoFamilia(FamiliaEntity familia);
	
	FamiliaEntity findFamiliaById(Long familiaId);
	
	Integer createPontuacao(Set<PessoaEntity> pessoaEntities, List<CriterioEntity> criterios);
}
