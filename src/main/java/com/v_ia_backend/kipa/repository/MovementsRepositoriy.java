package com.v_ia_backend.kipa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.Movements;

@Repository
public interface MovementsRepositoriy extends JpaRepository<Movements, Long> {

}
