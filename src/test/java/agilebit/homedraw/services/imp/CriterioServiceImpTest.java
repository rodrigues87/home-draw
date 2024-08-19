package agilebit.homedraw.services.imp;

import agilebit.homedraw.dtos.criterio.CriterioRequestDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponseDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponsePageDTO;
import agilebit.homedraw.entities.CriterioEntity;
import agilebit.homedraw.exceptions.NotFoundCriterioException;
import agilebit.homedraw.mappers.CriterioMapper;
import agilebit.homedraw.repositories.CriterioRepository;
import agilebit.homedraw.services.FamiliaCriterioService;
import agilebit.homedraw.util.CriterioBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CriterioServiceImpTest {
	
	@Mock
	private CriterioRepository criterioRepository;
	
	@Mock
	private FamiliaCriterioService familiaCriterioService;
	
	@InjectMocks
	private CriterioServiceImp criterioServiceImp;
	
	private CriterioRequestDTO requestDTO;
	private CriterioEntity entity;
	private CriterioEntity entitySaved;
	
	private CriterioResponseDTO responseDTO;
	private CriterioResponsePageDTO responsePageDTO;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		requestDTO = CriterioBuilder.createCriterioRequestDTO();
		entity = CriterioBuilder.createCriterioEntityDTO();
		entitySaved = CriterioBuilder.createCriterioEntityDTO();
		
		responseDTO = CriterioBuilder.createCriterioResponseDTO();
		responsePageDTO = CriterioBuilder.createCriterioResponsePageDTO();
	}
	
	@Test
	void saveCriterioSuccessfully() {
		try (MockedStatic<CriterioMapper> mockedMapper = mockStatic(CriterioMapper.class)) {
			mockedMapper.when(() -> CriterioMapper.createCriterioEntity(requestDTO)).thenReturn(entity);
			when(criterioRepository.save(entity)).thenReturn(entitySaved);
			mockedMapper.when(() -> CriterioMapper.createCriterioResponseDTO(entitySaved)).thenReturn(responseDTO);
			
			CriterioResponseDTO result = criterioServiceImp.save(requestDTO);
			
			assertEquals(responseDTO, result);
			verify(familiaCriterioService, times(1)).sincronizarCriterios();
		}
	}
	
	@Test
	void updateCriterioSuccessfully() {
		Long id = 1L;
		
		try (MockedStatic<CriterioMapper> mockedMapper = mockStatic(CriterioMapper.class)) {
			mockedMapper.when(() -> CriterioMapper.createCriterioEntity(requestDTO)).thenReturn(entity);
			when(criterioRepository.findById(id)).thenReturn(Optional.of(entity));
			when(criterioRepository.save(entity)).thenReturn(entitySaved);
			mockedMapper.when(() -> CriterioMapper.createCriterioResponseDTO(entitySaved)).thenReturn(responseDTO);
			
			CriterioResponseDTO result = criterioServiceImp.update(id, requestDTO);
			
			assertEquals(responseDTO, result);
			verify(familiaCriterioService, times(1)).sincronizarCriterios();
		}
	}
	
	@Test
	void findCriterioByIdThrowsNotFoundException() {
		Long id = 1L;
		
		when(criterioRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundCriterioException.class, () -> criterioServiceImp.findById(id));
	}
	
	@Test
	void findAllCriteriosSuccessfully() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<CriterioEntity> page = new PageImpl<>(Collections.singletonList(entity));
		List<CriterioResponseDTO> responseDTOList = Collections.singletonList(responseDTO);
		
		when(criterioRepository.findAll(pageable)).thenReturn(page);
		
		try (MockedStatic<CriterioMapper> mockedMapper = mockStatic(CriterioMapper.class)) {
			mockedMapper.when(() -> CriterioMapper.createCriterioDTOFromEntity(entity)).thenReturn(responseDTO);
			mockedMapper.when(() -> CriterioMapper.createCriterioResponsePageDTO(page, responseDTOList)).thenReturn(responsePageDTO);
			
			CriterioResponsePageDTO result = criterioServiceImp.findAll(pageable);
			
			assertEquals(responsePageDTO, result);
		}
	}
	
	@Test
	void deleteCriterioByIdSuccessfully() {
		Long id = 1L;
		
		when(criterioRepository.findById(id)).thenReturn(Optional.of(entity));
		
		criterioServiceImp.deleteById(id);
		
		verify(criterioRepository, times(1)).delete(entity);
		verify(familiaCriterioService, times(1)).sincronizarCriterios();
	}
	
	@Test
	void findAllEntitiesSuccessfully() {
		List<CriterioEntity> entityList = Collections.singletonList(entity);
		
		when(criterioRepository.findAll()).thenReturn(entityList);
		
		List<CriterioEntity> result = criterioServiceImp.findAllEntities();
		
		assertEquals(entityList, result);
	}
}