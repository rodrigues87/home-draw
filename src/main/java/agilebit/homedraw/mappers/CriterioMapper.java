package agilebit.homedraw.mappers;

import agilebit.homedraw.dtos.criterio.CriterioRequestDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponseDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponsePageDTO;
import agilebit.homedraw.entities.CriterioEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CriterioMapper {
	
	private CriterioMapper() {
		super();
	}
	
	public static CriterioResponseDTO createCriterioDTOFromEntity(CriterioEntity criterioEntity) {
		return CriterioResponseDTO.builder()
				.id(criterioEntity.getId())
				.descricao(criterioEntity.getDescricao())
				.pontuacao(criterioEntity.getPontuacao())
				.limiteInferior(criterioEntity.getLimiteInferior())
				.limiteSuperior(criterioEntity.getLimiteSuperior())
				.tipo(criterioEntity.getTipo())
				.build();
	}
	
	public static CriterioResponseDTO createCriterioResponseDTO(CriterioEntity criterioEntity) {
		return CriterioResponseDTO.builder()
				.id(criterioEntity.getId())
				.descricao(criterioEntity.getDescricao())
				.pontuacao(criterioEntity.getPontuacao())
				.limiteInferior(criterioEntity.getLimiteInferior())
				.limiteSuperior(criterioEntity.getLimiteSuperior())
				.tipo(criterioEntity.getTipo())
				.build();
	}
	
	public static CriterioResponsePageDTO createCriterioResponsePageDTO(Page<CriterioEntity> criterioEntityPage,
	                                                                    List<CriterioResponseDTO> criterioResponseDTOS) {
		return CriterioResponsePageDTO.builder()
				.content(criterioResponseDTOS)
				.numberOfElements(criterioEntityPage.getNumberOfElements())
				.number(criterioEntityPage.getNumber())
				.totalPages(criterioEntityPage.getTotalPages())
				.totalElements(criterioEntityPage.getTotalElements())
				.first(criterioEntityPage.isFirst())
				.last(criterioEntityPage.isLast())
				.size(criterioEntityPage.getSize())
				.build();
	}
	
	public static CriterioEntity createCriterioEntity(CriterioRequestDTO criterio) {
		return CriterioEntity.builder()
				.descricao(criterio.getDescricao())
				.pontuacao(criterio.getPontuacao())
				.limiteInferior(criterio.getLimiteInferior())
				.limiteSuperior(criterio.getLimiteSuperior())
				.tipo(criterio.getTipo())
				.build();
	}
}
