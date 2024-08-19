package agilebit.homedraw.mappers;

import agilebit.homedraw.dtos.familia.FamiliaResponseDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponsePageDTO;
import agilebit.homedraw.entities.FamiliaEntity;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class FamiliaMapper {
	
	private FamiliaMapper() {
		super();
	}
	
	public static FamiliaResponseDTO createFamiliaDTOFromEntity(FamiliaEntity familiaEntity) {
		return FamiliaResponseDTO
				.builder()
				.id(familiaEntity.getId())
				.pontuacao(familiaEntity.getPontuacao())
				.pessoas(familiaEntity.getPessoas().stream().map(PessoaMapper::createPessoaResponseDTO).collect(Collectors.toSet()))
				.build();
	}
	
	public static FamiliaResponsePageDTO createFamiliaResponsePageDTO(Page<FamiliaEntity> familiaEntities) {
		return FamiliaResponsePageDTO
				.builder()
				.content(familiaEntities.getContent().stream().map(FamiliaMapper::createFamiliaDTOFromEntity).toList())
				.numberOfElements(familiaEntities.getNumberOfElements())
				.first(familiaEntities.isFirst())
				.last(familiaEntities.isLast())
				.number(familiaEntities.getNumber())
				.size(familiaEntities.getSize())
				.totalElements(familiaEntities.getTotalElements())
				.totalPages(familiaEntities.getTotalPages())
				.build();
	}
}
