package com.robotronics.robotexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.robotronics.robotexample.dto.RobotDTO;
import com.robotronics.robotexample.model.Robot;
import com.robotronics.robotexample.service.RobotService;

import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/robots")
public class RobotController {

    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping
    public ResponseEntity<?> crearRobots(@Valid @RequestBody List<RobotDTO> robotDTOs) {
        try {
            List<Robot> robotsCreados = robotService.crearRobots(robotDTOs);
            return ResponseEntity.status(HttpStatus.CREATED).body(robotsCreados);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Robot>> obtenerTodosLosRobots() {
        return ResponseEntity.ok(robotService.obtenerTodosLosRobots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Robot> obtenerRobotPorId(@PathVariable Long id) {
        Optional<Robot> robot = robotService.obtenerRobotPorId(id);
        return robot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Robot> actualizarRobot(@PathVariable Long id, @RequestBody Robot robot) {
        try {
            return ResponseEntity.ok(robotService.actualizarRobot(id, robot));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRobot(@PathVariable Long id) {
        try {
            robotService.eliminarRobot(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Robot>> obtenerRobotsPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(robotService.buscarPorTipo(tipo));
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Robot>> obtenerRobotsPorRangoFechas(
            @RequestParam Integer desde,
            @RequestParam Integer hasta) {
        return ResponseEntity.ok(robotService.buscarPorRangoDeFechas(desde, hasta));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Robot> cambiarEstadoRobot(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(robotService.cambiarEstado(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
