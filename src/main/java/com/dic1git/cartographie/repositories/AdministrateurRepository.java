package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
}