package agilebit.homedraw.util;

import agilebit.homedraw.dtos.familia.FamiliaRequestDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponseDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponsePageDTO;
import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.entities.PessoaEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class FamiliaBuilder {
	
	public static FamiliaResponsePageDTO createFamiliaResponsePageDTO() {
		return FamiliaResponsePageDTO.builder()
				.content(List.of(createFamiliaResponseDTO()))
				.build();
	}
	
	public static FamiliaRequestDTO createFamiliaRequestDTO() {
		FamiliaRequestDTO requestDTO = new FamiliaRequestDTO();
		requestDTO.setPessoas(Set.of(1L, 2L, 3L));
		return requestDTO;
	}
	
	public static FamiliaResponseDTO createFamiliaResponseDTO() {
		return FamiliaResponseDTO.builder()
				.id(1L)
				.pessoas(PessoaBuilder.createPessoaDTO())
				.build();
	}
	
	public static FamiliaEntity createFamiliaEntity() {
		return FamiliaEntity.builder()
				.id(1L)
				.pessoas(Set.of(PessoaEntity
						.builder()
								.idade(10)
								.dependente(true)
								.nome("João")
								.renda(BigDecimal.valueOf(1000))
						.build()))
				.build();
	}
}
