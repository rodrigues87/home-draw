package agilebit.homedraw.services;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponsePageDTO;
import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.entities.PessoaEntity;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PessoaService {
	PessoaResponseDTO save(PessoaRequestDTO pessoa);
	
	PessoaResponseDTO findById(Long id);
	
	PessoaResponsePageDTO findAll(Pageable pageable);
	
	PessoaResponseDTO update(Long id, PessoaRequestDTO pessoa);
	
	void deleteById(Long id);
	
	PessoaEntity findPessoaById(Long id);
	
	Set<PessoaEntity> findPessoasByIds(Set<Long> pessoas);
	
	List<FamiliaEntity> findFamiliasWithDataDeNascimentoEqualsToCurrentDay(LocalDate currentDate);
}
