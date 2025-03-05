package com.robotronics.robotexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.robotronics.robotexample.model.Robot;
import com.robotronics.robotexample.service.RobotService;

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
    public ResponseEntity<Robot> crearRobot(@RequestBody Robot robot) {
        return ResponseEntity.ok(robotService.crearRobot(robot));
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
}
