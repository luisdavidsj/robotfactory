package com.robotronics.robotexample.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RobotDTO {
    
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo no puede estar vacío")
    private String tipo;

    @Min(value = 1900, message = "El año de fabricación no puede ser menor a 1900")
    @Max(value = 2100, message = "El año de fabricación no puede ser mayor a 2100")
    private Integer anioFabricacion;

    private Boolean estadoActivo = true;
}
