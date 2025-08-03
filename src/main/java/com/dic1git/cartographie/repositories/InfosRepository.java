package com.dic1git.cartographie.repositories;

import com.dic1git.cartographie.entities.Infos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfosRepository extends JpaRepository<Infos, Long> {
}
