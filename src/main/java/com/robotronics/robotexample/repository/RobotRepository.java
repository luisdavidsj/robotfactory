package com.robotronics.robotexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.robotronics.robotexample.model.Robot;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {
}