package com.robotronics.robotexample.service;

import com.robotronics.robotexample.dto.MantenimientoDTO;
import com.robotronics.robotexample.model.Mantenimiento;
import com.robotronics.robotexample.model.Robot;
import com.robotronics.robotexample.repository.MantenimientoRepository;
import com.robotronics.robotexample.repository.RobotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final RobotRepository robotRepository;

    public MantenimientoService(MantenimientoRepository mantenimientoRepository, RobotRepository robotRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
        this.robotRepository = robotRepository;
    }

    public Mantenimiento agregarMantenimiento(Long robotId, MantenimientoDTO mantenimientoDTO) {
        Robot robot = robotRepository.findById(robotId)
                .orElseThrow(() -> new RuntimeException("Robot no encontrado"));

        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setRobot(robot);
        mantenimiento.setDescripcion(mantenimientoDTO.getDescripcion());
        mantenimiento.setFecha(mantenimientoDTO.getFecha());

        return mantenimientoRepository.save(mantenimiento);
    }

    public Page<MantenimientoDTO> obtenerMantenimientosPorRobot(Long robotId, Pageable pageable) {
        return mantenimientoRepository.findByRobotId(robotId, pageable)
                .map(mantenimiento -> new MantenimientoDTO(
                        mantenimiento.getRobot().getId(),
                        mantenimiento.getDescripcion(),
                        mantenimiento.getFecha()
                ));
    }
}
