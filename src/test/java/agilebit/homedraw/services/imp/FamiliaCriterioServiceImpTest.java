package agilebit.homedraw.services.imp;

import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.entities.PessoaEntity;
import agilebit.homedraw.exceptions.NotFoundFamiliaException;
import agilebit.homedraw.repositories.CriterioRepository;
import agilebit.homedraw.repositories.FamiliaRepository;
import agilebit.homedraw.util.CriterioBuilder;
import agilebit.homedraw.util.FamiliaBuilder;
import agilebit.homedraw.util.PessoaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FamiliaCriterioServiceImpTest {
	@Mock
	private CriterioRepository criterioRepository;
	
	@Mock
	private FamiliaRepository familiaRepository;
	
	@InjectMocks
	private FamiliaCriterioServiceImp familiaCriterioServiceImp;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void sincronizarCriteriosSuccessfully() {
		when(criterioRepository.findAll(any(Pageable.class)))
				.thenReturn(new PageImpl<>(List.of(CriterioBuilder.createCriterioEntityDTO())));
		when(familiaRepository.findAll(any(Pageable.class)))
				.thenReturn(new PageImpl<>(List.of(FamiliaBuilder.createFamiliaEntity())));
		
		familiaCriterioServiceImp.sincronizarCriterios();
		
		verify(familiaRepository, atLeastOnce()).saveAll(anyList());
	}
	
	@Test
	void updatePontuacaoFamiliaSuccessfully() {
		FamiliaEntity familiaEntity = FamiliaBuilder.createFamiliaEntity();
		when(criterioRepository.findAll()).thenReturn(List.of(CriterioBuilder.createCriterioEntityDTO()));
		
		familiaCriterioServiceImp.updatePontuacaoFamilia(familiaEntity);
		
		verify(familiaRepository).save(familiaEntity);
	}
	
	@Test
	void findFamiliaByIdSuccessfully() {
		Long id = 1L;
		FamiliaEntity familiaEntity = new FamiliaEntity();
		when(familiaRepository.findById(id)).thenReturn(Optional.of(familiaEntity));
		
		FamiliaEntity result = familiaCriterioServiceImp.findFamiliaById(id);
		
		assertEquals(familiaEntity, result);
	}
	
	@Test
	void findFamiliaByIdThrowsNotFoundFamiliaException() {
		Long id = 1L;
		when(familiaRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundFamiliaException.class, () -> familiaCriterioServiceImp.findFamiliaById(id));
	}
	
	@Test
	void createPontuacaoCalculatesCorrectly() {
		Set<PessoaEntity> pessoas = Set.of(PessoaBuilder.createPessoaEntity());
		List<CriterioEntity> criterios = List.of(CriterioBuilder.createCriterioEntityDTO());
		
		Integer result = familiaCriterioServiceImp.createPontuacao(pessoas, criterios);
		
		assertNotNull(result);
	}
}