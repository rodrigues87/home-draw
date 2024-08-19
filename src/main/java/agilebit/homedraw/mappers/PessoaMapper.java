package agilebit.homedraw.mappers;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponsePageDTO;
import agilebit.homedraw.entities.PessoaEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PessoaMapper {
	
	private PessoaMapper() {
		super();
	}
	
	public static PessoaEntity createPessoaEntity(PessoaRequestDTO pessoaRequestDTO) {
		return PessoaEntity.builder()
				.nome(pessoaRequestDTO.getNome())
				.renda(pessoaRequestDTO.getRenda())
				.idade(pessoaRequestDTO.getIdade())
				.dependente(pessoaRequestDTO.getDependente())
				.build();
	}
	
	public static PessoaResponseDTO createPessoaResponseDTO(PessoaEntity pessoaEntity) {
		return PessoaResponseDTO.builder()
				.id(pessoaEntity.getId())
				.nome(pessoaEntity.getNome())
				.renda(pessoaEntity.getRenda())
				.idade(pessoaEntity.getIdade())
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
	
	public static Set<PessoaEntity> createPessoaEntities(Set<PessoaRequestDTO> pessoas) {
		return pessoas.stream().map(PessoaMapper::createPessoaEntity).collect(Collectors.toSet());
	}
}
