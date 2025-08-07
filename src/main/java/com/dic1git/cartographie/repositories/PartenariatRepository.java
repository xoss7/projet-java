package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.entities.Partenaire;
import com.dic1git.cartographie.entities.Partenariat;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartenariatRepository extends JpaRepository<Partenariat, Long> {

    @Query("SELECT p.partenaire FROM Partenariat p WHERE p.etablissement.id=:etablissementId")
    List<Partenaire> findPartenaireByEtablissementId(@Param("etablissementId") Long etablissementId);

    @Query("SELECT p.etablissement FROM Partenariat p WHERE p.partenaire.id=:partenaireId")
    List<Etablissement> findEtablissementByPartenaireId(@Param("partenaireId") Long partenaireId);

}
