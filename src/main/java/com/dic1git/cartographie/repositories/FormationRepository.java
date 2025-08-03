package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}
