package com.robotronics.robotexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.robotronics.robotexample.model.Robot;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {
    List<Robot> findByTipo(String tipo);
    List<Robot> findByAnioFabricacionBetween(Integer desde, Integer hasta);
    boolean existsByNombre(String nombre);
}