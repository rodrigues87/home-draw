package agilebit.homedraw.services;

import agilebit.homedraw.dtos.criterio.CriterioRequestDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponseDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponsePageDTO;
import agilebit.homedraw.entities.CriterioEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CriterioService {
	CriterioResponseDTO save(CriterioRequestDTO criterio);
	
	CriterioResponseDTO findById(Long id);
	
	CriterioResponsePageDTO findAll(Pageable pageable);
	
	CriterioResponseDTO update(Long id, CriterioRequestDTO criterio);
	
	void deleteById(Long id);
	
	List<CriterioEntity> findAllEntities();
}
