package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Infos;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfosRepository extends JpaRepository<Infos, Long> {
    Optional<Infos> findByIdAndEtablissement_Id(Long id, Long idEtablissement);

    List<Infos> findAllByEtablissement_Id(Long id);

    void deleteByIdAndEtablissement_Id(Long id, Long idEtablissement);
}
