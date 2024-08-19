package agilebit.homedraw.util;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponsePageDTO;
import agilebit.homedraw.entities.PessoaEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class PessoaBuilder {
	
	
	public static Set<PessoaResponseDTO> createPessoaDTO() {
		return Set.of(PessoaResponseDTO.builder()
				.id(1L)
				.nome("João")
				.build());
	}
	
	public static PessoaRequestDTO createPessoaRequestDTO() {
		return PessoaRequestDTO.builder()
				.nome("João")
				.build();
	}
	
	public static PessoaResponseDTO createPessoaResponseDTO() {
		return PessoaResponseDTO.builder()
				.id(1L)
				.nome("João")
				.build();
	}
	
	public static PessoaResponsePageDTO createPessoaResponsePageDTO() {
		return PessoaResponsePageDTO.builder()
				.content(List.of(createPessoaResponseDTO()))
				.build();
	}
	
	public static PessoaEntity createPessoaEntity() {
		return PessoaEntity.builder()
				.id(1L)
				.nome("João")
				.idade(10)
				.dependente(true)
				.renda(BigDecimal.valueOf(1000))
				.build();
	}
}
