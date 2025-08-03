package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Accreditation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditationRepository extends JpaRepository<Accreditation, Long> {
}