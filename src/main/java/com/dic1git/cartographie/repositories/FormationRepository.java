package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    Optional<Formation> findByIdAndEtablissement_Id(Long id, Long etablissementId);

    List<Formation> findByEtablissement_Id(Long id);
}