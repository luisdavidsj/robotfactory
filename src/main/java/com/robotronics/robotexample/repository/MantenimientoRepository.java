package com.robotronics.robotexample.repository;

import com.robotronics.robotexample.model.Mantenimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    Page<Mantenimiento> findByRobotId(Long robotId, Pageable pageable);
}
