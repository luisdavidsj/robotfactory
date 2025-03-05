package com.robotronics.robotexample.model;

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
}
