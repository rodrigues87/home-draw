package agilebit.homedraw.util;

import agilebit.homedraw.constants.CriterioTypeEnum;
import agilebit.homedraw.dtos.criterio.CriterioRequestDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponseDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponsePageDTO;
import agilebit.homedraw.entities.CriterioEntity;

import java.math.BigDecimal;
import java.util.List;

public class CriterioBuilder {
	public static CriterioRequestDTO createCriterioRequestDTO() {
		return CriterioRequestDTO.builder()
				.descricao("Criterio 1")
				.pontuacao(10)
				.limiteInferior(BigDecimal.ONE)
				.limiteSuperior(BigDecimal.TEN)
				.tipo(CriterioTypeEnum.DEPENDENTES)
				.build();
	}
	
	public static CriterioEntity createCriterioEntityDTO() {
		return CriterioEntity.builder()
				.id(1L)
				.descricao("Criterio 1")
				.pontuacao(10)
				.limiteInferior(BigDecimal.ONE)
				.limiteSuperior(BigDecimal.TEN)
				.tipo(CriterioTypeEnum.DEPENDENTES)
				.build();
	}
	
	public static CriterioResponseDTO createCriterioResponseDTO() {
		return CriterioResponseDTO.builder()
				.id(1L)
				.descricao("Criterio 1")
				.pontuacao(10)
				.limiteInferior(BigDecimal.ONE)
				.limiteSuperior(BigDecimal.TEN)
				.tipo(CriterioTypeEnum.DEPENDENTES)
				.build();
	}
	
	public static CriterioResponsePageDTO createCriterioResponsePageDTO() {
		return CriterioResponsePageDTO.builder()
				.content(List.of(createCriterioResponseDTO()))
				.build();
	}
}
