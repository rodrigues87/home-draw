package agilebit.homedraw.mappers;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponsePageDTO;
import agilebit.homedraw.entities.PessoaEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public class PessoaMapper {
	
	private PessoaMapper() {
		super();
	}
	
	public static PessoaEntity createPessoaEntity(PessoaRequestDTO pessoaRequestDTO) {
		return PessoaEntity.builder()
				.nome(pessoaRequestDTO.getNome())
				.renda(pessoaRequestDTO.getRenda())
				.dataDeNascimento(pessoaRequestDTO.getDataDeNascimento())
				.dependente(pessoaRequestDTO.getDependente())
				.build();
	}
	
	public static PessoaResponseDTO createPessoaResponseDTO(PessoaEntity pessoaEntity) {
		return PessoaResponseDTO.builder()
				.id(pessoaEntity.getId())
				.nome(pessoaEntity.getNome())
				.renda(pessoaEntity.getRenda())
				.dataDeNascimento(pessoaEntity.getDataDeNascimento())
				.dependente(pessoaEntity.getDependente())
				.build();
	}
	
	public static PessoaResponsePageDTO createPessoaResponsePageDTO(Page<PessoaEntity> pessoaEntityPage) {
		
		return PessoaResponsePageDTO.builder()
				.content(createPessoaResponseDTOList(pessoaEntityPage.getContent()))
				.numberOfElements(pessoaEntityPage.getNumberOfElements())
				.number(pessoaEntityPage.getNumber())
				.totalPages(pessoaEntityPage.getTotalPages())
				.totalElements(pessoaEntityPage.getTotalElements())
				.first(pessoaEntityPage.isFirst())
				.last(pessoaEntityPage.isLast())
				.size(pessoaEntityPage.getSize())
				.build();
	}
	
	private static List<PessoaResponseDTO> createPessoaResponseDTOList(List<PessoaEntity> content) {
		return content.stream().map(PessoaMapper::createPessoaResponseDTO).toList();
	}
}
