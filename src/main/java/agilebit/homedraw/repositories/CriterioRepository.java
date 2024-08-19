package agilebit.homedraw.repositories;

import agilebit.homedraw.entities.CriterioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioRepository extends JpaRepository<CriterioEntity, Long> {
}
