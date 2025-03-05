package com.robotronics.robotexample.service;

import org.springframework.stereotype.Service;

import com.robotronics.robotexample.model.Robot;
import com.robotronics.robotexample.repository.RobotRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RobotService {

    private final RobotRepository robotRepository;

    public RobotService(RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }

    public Robot crearRobot(Robot robot) {
        return robotRepository.save(robot);
    }

    public List<Robot> obtenerTodosLosRobots() {
        return robotRepository.findAll();
    }

    public Optional<Robot> obtenerRobotPorId(Long id) {
        return robotRepository.findById(id);
    }

    public Robot actualizarRobot(Long id, Robot robotActualizado) {
        return robotRepository.findById(id).map(robot -> {
            robot.setNombre(robotActualizado.getNombre());
            robot.setTipo(robotActualizado.getTipo());
            robot.setAnioFabricacion(robotActualizado.getAnioFabricacion());
            return robotRepository.save(robot);
        }).orElseThrow(() -> new RuntimeException("Robot no encontrado"));
    }

    public void eliminarRobot(Long id) {
        if (!robotRepository.existsById(id)) {
            throw new RuntimeException("Robot no encontrado");
        }
        robotRepository.deleteById(id);
    }
}
