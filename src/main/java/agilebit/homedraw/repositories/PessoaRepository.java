package agilebit.homedraw.repositories;

import agilebit.homedraw.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
	List<PessoaEntity> findAllByDataDeNascimentoEquals(LocalDate currentDate);
}
