package com.robotronics.robotexample.service;

import org.springframework.stereotype.Service;

import com.robotronics.robotexample.dto.RobotDTO;
import com.robotronics.robotexample.model.Robot;
import com.robotronics.robotexample.repository.RobotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RobotService {

    private final RobotRepository robotRepository;

    public RobotService(RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }

    public List<Robot> crearRobots(List<RobotDTO> robotDTOs) {
        List<Robot> robotsCreados = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        for (RobotDTO dto : robotDTOs) {
            if (robotRepository.existsByNombre(dto.getNombre())) {
                errores.add("Ya existe un robot con el nombre: " + dto.getNombre());
                continue;
            }

            Robot robot = new Robot();
            robot.setNombre(dto.getNombre());
            robot.setTipo(dto.getTipo());
            robot.setAnioFabricacion(dto.getAnioFabricacion());

            robotsCreados.add(robotRepository.save(robot));
        }

        if (!errores.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errores));
        }

        return robotsCreados;
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

    public List<Robot> buscarPorTipo(String tipo) {
        return robotRepository.findByTipo(tipo);
    }

    public List<Robot> buscarPorRangoDeFechas(Integer desde, Integer hasta) {
        return robotRepository.findByAnioFabricacionBetween(desde, hasta);
    }

    public Robot cambiarEstado(Long id) {
        return robotRepository.findById(id).map(robot -> {
            robot.setEstadoActivo(!robot.getEstadoActivo()); // Cambia entre true y false
            return robotRepository.save(robot);
        }).orElseThrow(() -> new RuntimeException("Robot no encontrado"));
    }
}
