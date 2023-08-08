package com.api.restbiblioteca.librosdatabase.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "biblioteca")
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
        for(Libro libro : libros) {
            libro.setBiblioteca(this);
        }
    }
}