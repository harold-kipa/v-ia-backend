package com.v_ia_backend.kipa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.dto.response.RemunerationResponse;
import com.v_ia_backend.kipa.entity.Remuneration;

@Repository
public interface RemunerationRepositoriy extends JpaRepository<Remuneration, Long> {
    // @Query("""
    //     SELECT new com.v_ia_backend.kipa.dto.response.RemunerationResponse(
    //         FUNCTION('YEAR', r.movementDate),
    //         SUM(r.compensationPaidValue),
    //         SUM(r.liquidationBaseIncome)
    //     )
    //     FROM Remuneration r
    //     GROUP BY FUNCTION('YEAR', r.movementDate)
    //     ORDER BY FUNCTION('YEAR', r.movementDate)
    // """)
    // List<RemunerationResponse> findRemunerationTotalsByYear();
    
}
