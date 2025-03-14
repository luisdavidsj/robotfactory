package com.robotronics.robotexample.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "robots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Robot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer anioFabricacion;

    @Column(nullable = false)
    private Boolean estadoActivo = true;

    @OneToMany(mappedBy = "robot", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Marca el lado "principal"
    private List<Mantenimiento> mantenimientos;
}
