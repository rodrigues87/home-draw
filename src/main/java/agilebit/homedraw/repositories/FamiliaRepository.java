package agilebit.homedraw.repositories;

import agilebit.homedraw.entities.FamiliaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepository extends JpaRepository<FamiliaEntity, Long> {
}
