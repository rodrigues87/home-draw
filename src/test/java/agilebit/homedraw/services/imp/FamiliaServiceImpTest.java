package agilebit.homedraw.services.imp;

import agilebit.homedraw.dtos.familia.FamiliaRequestDTO;
import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.entities.PessoaEntity;
import agilebit.homedraw.exceptions.NotFoundFamiliaException;
import agilebit.homedraw.mappers.FamiliaMapper;
import agilebit.homedraw.repositories.FamiliaRepository;
import agilebit.homedraw.repositories.PessoaRepository;
import agilebit.homedraw.services.CriterioService;
import agilebit.homedraw.services.FamiliaCriterioService;
import agilebit.homedraw.services.PessoaService;
import agilebit.homedraw.util.CriterioBuilder;
import agilebit.homedraw.util.FamiliaBuilder;
import agilebit.homedraw.util.PessoaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FamiliaServiceImpTest {
	
	@Mock
	private FamiliaRepository familiaRepository;
	
	@Mock
	private FamiliaCriterioService familiaCriterioService;
	
	@Mock
	private PessoaService pessoaService;
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	@Mock
	private CriterioService criterioService;
	@Mock
	private FamiliaMapper familiaMapper;
	@InjectMocks
	private FamiliaServiceImp familiaServiceImp;
	
	private FamiliaRequestDTO requestDTO = FamiliaBuilder.createFamiliaRequestDTO();
	private Set<PessoaEntity> pessoaEntities = Set.of(PessoaBuilder.createPessoaEntity());
	private List<CriterioEntity> criterios = List.of(CriterioBuilder.createCriterioEntityDTO());
	private FamiliaEntity familiaEntity = FamiliaBuilder.createFamiliaEntity();
	private FamiliaEntity savedFamilia = FamiliaBuilder.createFamiliaEntity();
	private Page<FamiliaEntity> familiaEntities = new PageImpl<>(List.of(FamiliaBuilder.createFamiliaEntity()));
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void findByIdThrowsNotFoundFamiliaException() {
		Long id = 1L;
		
		when(familiaCriterioService.findFamiliaById(id)).thenThrow(new NotFoundFamiliaException(id));
		
		assertThrows(NotFoundFamiliaException.class, () -> familiaServiceImp.findById(id));
	}
	
	@Test
	void deleteByIdSuccessfully() {
		Long id = 1L;
		
		when(familiaCriterioService.findFamiliaById(id)).thenReturn(familiaEntity);
		
		familiaServiceImp.deleteById(id);
		
		verify(familiaCriterioService).findFamiliaById(id);
		verify(familiaRepository).deleteById(id);
	}
}