package agilebit.homedraw.services.imp;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponsePageDTO;
import agilebit.homedraw.entities.PessoaEntity;
import agilebit.homedraw.exceptions.NotFoundPessoaException;
import agilebit.homedraw.mappers.PessoaMapper;
import agilebit.homedraw.repositories.PessoaRepository;
import agilebit.homedraw.services.FamiliaCriterioService;
import agilebit.homedraw.services.PessoaService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Log4j2
public class PessoaServiceImp implements PessoaService {
	
	private final PessoaRepository pessoaRepository;
	private final FamiliaCriterioService familiaCriterioService;
	
	@Override
	public PessoaResponseDTO save(PessoaRequestDTO pessoaRequestDTO) {
		
		PessoaEntity entity = PessoaMapper.createPessoaEntity(pessoaRequestDTO);
		
		PessoaEntity pessoaEntity = pessoaRepository.save(entity);
		
		if (Objects.nonNull(pessoaEntity.getFamilia())) {
			familiaCriterioService.updatePontuacaoFamilia(pessoaEntity.getFamilia());
		}
		
		return PessoaMapper.createPessoaResponseDTO(pessoaEntity);
	}
	
	@Override
	public PessoaResponseDTO findById(Long id) {
		PessoaEntity pessoaEntity = findPessoaById(id);
		return PessoaMapper.createPessoaResponseDTO(pessoaEntity);
	}
	
	@Override
	public PessoaResponsePageDTO findAll(Pageable pageable) {
		Page<PessoaEntity> pessoaEntityPage = pessoaRepository.findAll(pageable);
		return PessoaMapper.createPessoaResponsePageDTO(pessoaEntityPage);
	}
	
	@Override
	public PessoaResponseDTO update(Long id, PessoaRequestDTO pessoaRequestDTO) {
		PessoaEntity pessoaEntity = findPessoaById(id);
		
		pessoaEntity.setNome(pessoaRequestDTO.getNome());
		pessoaEntity.setRenda(pessoaRequestDTO.getRenda());
		
		familiaCriterioService.updatePontuacaoFamilia(pessoaEntity.getFamilia());
		
		return PessoaMapper.createPessoaResponseDTO(pessoaEntity);
	}
	
	@Override
	public void deleteById(Long id) {
		PessoaEntity pessoaEntity = findPessoaById(id);
		
		pessoaRepository.delete(pessoaEntity);
		
		familiaCriterioService.updatePontuacaoFamilia(pessoaEntity.getFamilia());
	}
	
	@Override
	public PessoaEntity findPessoaById(Long id) {
		Optional<PessoaEntity> pessoaEntityOptional = pessoaRepository.findById(id);
		if (pessoaEntityOptional.isPresent()) {
			return pessoaEntityOptional.get();
		} else {
			throw new NotFoundPessoaException(id);
		}
	}
	
	@Override
	public Set<PessoaEntity> findPessoasByIds(Set<Long> pessoas) {
		return pessoas.stream().map(this::findPessoaById).collect(Collectors.toSet());
	}
	
	@Override
	public void saveAll(Set<PessoaEntity> pessoaEntities) {
		pessoaRepository.saveAll(pessoaEntities);
	}
}
