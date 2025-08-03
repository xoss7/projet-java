package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Recommandation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommandationRepository extends JpaRepository<Recommandation, Long> {
}
