package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Recommandation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommandationRepository extends JpaRepository<Recommandation, Long> {
}
