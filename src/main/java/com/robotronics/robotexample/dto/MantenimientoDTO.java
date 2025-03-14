package com.robotronics.robotexample.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoDTO {
    
    private Long robotId; // No obligatorio en el request, se asigna en el servicio

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @FutureOrPresent(message = "La fecha no puede estar en el pasado")
    private LocalDate fecha;
    
}
