package org.babareko.NTITEAM.repository;

import org.babareko.NTITEAM.model.Lord;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface LordRepository extends JpaRepository<Lord, Integer> {

    List<Lord> getAllByPlanetsIsNull();

    @Query("SELECT l FROM Lord l order by l.age asc")
    List<Lord> getTopByAge(PageRequest of);
}
