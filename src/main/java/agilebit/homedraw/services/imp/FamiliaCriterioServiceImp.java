package agilebit.homedraw.services.imp;

import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.entities.PessoaEntity;
import agilebit.homedraw.exceptions.NotFoundFamiliaException;
import agilebit.homedraw.repositories.CriterioRepository;
import agilebit.homedraw.repositories.FamiliaRepository;
import agilebit.homedraw.services.FamiliaCriterioService;
import agilebit.homedraw.strategies.PontuacaoStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor
public class FamiliaCriterioServiceImp implements FamiliaCriterioService {
	
	final CriterioRepository criterioRepository;
	final FamiliaRepository familiaRepository;
	final List<PontuacaoStrategy> pontuacaoStrategies;
	
	@Override
	@Async
	public void sincronizarCriterios() {
		long startTime = System.nanoTime();
		log.info("Sincronizando regras de pontuação");
		
		List<CriterioEntity> criterios = criterioRepository.findAll();
		int pageSize = 1000;
		int pageNumber = 0;
		while (true) {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<FamiliaEntity> familiaEntityPage = familiaRepository.findAll(pageable);
			
			familiaEntityPage.getContent().forEach(familia ->
					familia.setPontuacao(createPontuacao(familia.getPessoas(), criterios)));
			
			familiaRepository.saveAll(familiaEntityPage.getContent());
			if (familiaEntityPage.isLast()) break;
			pageNumber++;
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1_000_000;
		log.info("Sincronização de regras de pontuação finalizada em {} ms", duration);
	}
	
	@Override
	public void updatePontuacaoFamilia(FamiliaEntity familiaEntity) {
		List<CriterioEntity> criterios = criterioRepository.findAll();
		familiaEntity.setPontuacao(createPontuacao(familiaEntity.getPessoas(), criterios));
		familiaRepository.save(familiaEntity);
	}
	
	@Override
	public FamiliaEntity findFamiliaById(Long familiaId) {
		Optional<FamiliaEntity> familiaEntityOptional = familiaRepository.findById(familiaId);
		if (familiaEntityOptional.isPresent()) {
			return familiaEntityOptional.get();
		} else {
			throw new NotFoundFamiliaException(familiaId);
		}
	}
	
	
	@Override
	public Integer createPontuacao(Set<PessoaEntity> pessoaEntities, List<CriterioEntity> criterios) {
		if (pessoaEntities == null || pessoaEntities.isEmpty() || criterios == null || criterios.isEmpty()) {
			return 0;
		}
		return pontuacaoStrategies.stream()
				.mapToInt(strategy -> strategy.calcularPontuacao(pessoaEntities.stream().toList(), criterios))
				.sum();
	}
}
