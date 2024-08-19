package agilebit.homedraw.services.imp;

import agilebit.homedraw.dtos.familia.FamiliaRequestDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponseDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponsePageDTO;
import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.entities.PessoaEntity;
import agilebit.homedraw.mappers.FamiliaMapper;
import agilebit.homedraw.repositories.FamiliaRepository;
import agilebit.homedraw.repositories.PessoaRepository;
import agilebit.homedraw.services.CriterioService;
import agilebit.homedraw.services.FamiliaCriterioService;
import agilebit.homedraw.services.FamiliaService;
import agilebit.homedraw.services.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class FamiliaServiceImp implements FamiliaService {
	
	private final FamiliaRepository familiaRepository;
	private final FamiliaCriterioService familiaCriterioService;
	private final PessoaService pessoaService;
	private final PessoaRepository pessoaRepository;
	private final CriterioService criterioService;
	
	@Override
	public FamiliaResponseDTO save(FamiliaRequestDTO familiaRequestDTO) {
		Set<PessoaEntity> pessoaEntities = pessoaService.findPessoasByIds(familiaRequestDTO.getPessoas());
		
		List<CriterioEntity> criterios = criterioService.findAllEntities();
		
		FamiliaEntity familiaEntity = FamiliaEntity
				.builder()
				.pessoas(pessoaEntities)
				.pontuacao(familiaCriterioService.createPontuacao(pessoaEntities, criterios))
				.build();
		
		FamiliaEntity familia = familiaRepository.save(familiaEntity);
		
		pessoaEntities.forEach(pessoaEntity -> pessoaEntity.setFamilia(familia));
		pessoaRepository.saveAll(pessoaEntities);
		
		return FamiliaMapper.createFamiliaDTOFromEntity(familia);
	}
	
	@Override
	public FamiliaResponseDTO findById(Long id) {
		FamiliaEntity familiaEntity = familiaCriterioService.findFamiliaById(id);
		return FamiliaMapper.createFamiliaDTOFromEntity(familiaEntity);
	}
	
	@Override
	public FamiliaResponsePageDTO findAll(Pageable pageable) {
		Page<FamiliaEntity> familiaEntities = familiaRepository.findAll(pageable);
		return FamiliaMapper.createFamiliaResponsePageDTO(familiaEntities);
	}
	
	@Override
	public FamiliaResponseDTO update(Long id, FamiliaRequestDTO familiaRequestDTO) {
		FamiliaEntity familiaEntity = familiaCriterioService.findFamiliaById(id);
		
		Set<PessoaEntity> pessoaEntities = pessoaService.findPessoasByIds(familiaRequestDTO.getPessoas());
		
		familiaEntity.setPessoas(pessoaEntities);
		
		List<CriterioEntity> criterios = criterioService.findAllEntities();
		
		familiaEntity.setPontuacao(familiaCriterioService.createPontuacao(familiaEntity.getPessoas(), criterios));
		
		FamiliaEntity familiaUpdated = familiaRepository.save(familiaEntity);
		
		return FamiliaMapper.createFamiliaDTOFromEntity(familiaUpdated);
	}
	
	@Override
	public void deleteById(Long id) {
		familiaCriterioService.findFamiliaById(id);
		familiaRepository.deleteById(id);
	}
}
