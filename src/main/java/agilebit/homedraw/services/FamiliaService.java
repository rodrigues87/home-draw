package agilebit.homedraw.services;

import agilebit.homedraw.dtos.familia.FamiliaRequestDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponseDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponsePageDTO;
import org.springframework.data.domain.Pageable;

public interface FamiliaService {
	
	FamiliaResponseDTO save(FamiliaRequestDTO familia);
	
	FamiliaResponseDTO findById(Long id);
	
	FamiliaResponsePageDTO findAll(Pageable pageable);
	
	FamiliaResponseDTO update(Long id, FamiliaRequestDTO familia);
	
	void deleteById(Long id);
}
