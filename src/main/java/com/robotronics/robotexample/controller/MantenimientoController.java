package com.robotronics.robotexample.controller;

import com.robotronics.robotexample.dto.MantenimientoDTO;
import com.robotronics.robotexample.service.MantenimientoService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/robots/{robotId}/mantenimientos")
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @PostMapping
    public ResponseEntity<String> agregarMantenimiento(
            @PathVariable Long robotId,
            @Valid @RequestBody MantenimientoDTO mantenimientoDTO) {

        mantenimientoService.agregarMantenimiento(robotId, mantenimientoDTO);
        return ResponseEntity.ok("Mantenimiento agregado con éxito");
    }

    @GetMapping
    public ResponseEntity<Page<MantenimientoDTO>> obtenerMantenimientosPorRobot(
            @PathVariable Long robotId, Pageable pageable) {

        Page<MantenimientoDTO> mantenimientos = mantenimientoService.obtenerMantenimientosPorRobot(robotId, pageable);
        return ResponseEntity.ok(mantenimientos);
    }
}
