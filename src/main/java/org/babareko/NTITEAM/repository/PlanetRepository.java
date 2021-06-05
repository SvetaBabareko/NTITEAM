package org.babareko.NTITEAM.repository;

import org.babareko.NTITEAM.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PlanetRepository extends JpaRepository<Planet,Integer> {
   @Modifying
    @Transactional
    @Query("DELETE FROM Planet p WHERE p.id=:id ")
    int deleteById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Planet d WHERE d.name=:name ")
    int deleteByName(@Param("name") String name);

   // Optional<Planet> getByName(String name);

    @Query("SELECT p FROM Planet p")
    List<Planet> findAll();

}
