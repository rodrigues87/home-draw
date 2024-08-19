package agilebit.homedraw.services.imp;

import agilebit.homedraw.dtos.criterio.CriterioRequestDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponseDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponsePageDTO;
import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.exceptions.NotFoundCriterioException;
import agilebit.homedraw.mappers.CriterioMapper;
import agilebit.homedraw.repositories.CriterioRepository;
import agilebit.homedraw.services.CriterioService;
import agilebit.homedraw.services.FamiliaCriterioService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class CriterioServiceImp implements CriterioService {
	
	private final CriterioRepository criterioRepository;
	private final FamiliaCriterioService familiaCriterioService;
	private final CriterioMapper criterioMapper;
	
	@Override
	@CacheEvict(value = "criterios", allEntries = true)
	public CriterioResponseDTO save(CriterioRequestDTO criterio) {
		CriterioEntity entity = CriterioMapper.createCriterioEntity(criterio);
		
		CriterioEntity criterioEntity = criterioRepository.save(entity);
		
		familiaCriterioService.sincronizarCriterios();
		
		return CriterioMapper.createCriterioResponseDTO(criterioEntity);
	}
	
	@Override
	@CacheEvict(value = "criterios", allEntries = true)
	public CriterioResponseDTO update(Long id, CriterioRequestDTO criterio) {
		CriterioEntity criterioEntity = findCriterioById(id);
		criterioEntity.setDescricao(criterio.getDescricao());
		criterioEntity.setPontuacao(criterio.getPontuacao());
		
		CriterioEntity updatedCriterio = criterioRepository.save(criterioEntity);
		
		familiaCriterioService.sincronizarCriterios();
		
		return CriterioMapper.createCriterioResponseDTO(updatedCriterio);
	}
	
	@Override
	public CriterioResponseDTO findById(Long id) {
		CriterioEntity criterioEntity = findCriterioById(id);
		return CriterioMapper.createCriterioResponseDTO(criterioEntity);
	}
	
	@Override
	public CriterioResponsePageDTO findAll(Pageable pageable) {
		Page<CriterioEntity> criterioEntityPage = criterioRepository.findAll(pageable);
		
		List<CriterioResponseDTO> criterioResponseDTOS = criterioEntityPage.getContent()
				.stream()
				.map(CriterioMapper::createCriterioDTOFromEntity)
				.toList();
		
		return CriterioMapper.createCriterioResponsePageDTO(criterioEntityPage, criterioResponseDTOS);
	}
	
	@Override
	@CacheEvict(value = "criterios", allEntries = true)
	public void deleteById(Long id) {
		CriterioEntity criterioEntity = findCriterioById(id);
		criterioRepository.delete(criterioEntity);
		familiaCriterioService.sincronizarCriterios();
	}
	
	@Override
	@Cacheable(value = "criterios")
	public List<CriterioEntity> findAllEntities() {
		log.info("Buscando todos os criterios");
		return criterioRepository.findAll();
	}
	
	private CriterioEntity findCriterioById(Long id) {
		Optional<CriterioEntity> criterioEntity = criterioRepository.findById(id);
		if (criterioEntity.isPresent()) {
			return criterioEntity.get();
		} else {
			throw new NotFoundCriterioException(id);
		}
	}
}
