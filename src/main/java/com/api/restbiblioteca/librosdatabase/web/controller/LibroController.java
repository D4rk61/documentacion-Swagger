package com.api.restbiblioteca.librosdatabase.web.controller;

import com.api.restbiblioteca.librosdatabase.domain.repository.IBibliotecaRepository;
import com.api.restbiblioteca.librosdatabase.domain.repository.ILibroRepository;
import com.api.restbiblioteca.librosdatabase.persistance.entity.Biblioteca;
import com.api.restbiblioteca.librosdatabase.persistance.entity.Libro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-libros")
public class LibroController {
    @Autowired
    private final ILibroRepository libroRepository;

    @Autowired
    private final IBibliotecaRepository bibliotecaRepository;
    public LibroController(ILibroRepository libroRepository, IBibliotecaRepository bibliotecaRepository) {
        this.libroRepository = libroRepository;
        this.bibliotecaRepository = bibliotecaRepository;
    }


    @PostMapping
    public ResponseEntity<Libro> guardarLibro(@Valid @RequestBody Libro libro) {

        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if(!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            libro.setBiblioteca(bibliotecaOptional.get());
            Libro libroGuardado = libroRepository.save(libro);

            URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(libroGuardado.getId()).toUri();


            return ResponseEntity.created(ubicacion).body(libroGuardado);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable("id") Integer id, @RequestBody Libro libro) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if(!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();

        }

        Optional<Libro> libroOptional = libroRepository.findById(id);

        if(!libroOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        libro.setBiblioteca(bibliotecaOptional.get());
        libro.setId(libroOptional.get().getId());

        libroRepository.save(libro);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> eliminarLibro(@PathVariable("id") Integer id) {
        // genera el codigo con .build

        Optional<Libro> libroOptional = libroRepository.findById(id);

        if(!libroOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            libroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        if(this.libroRepository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(this.libroRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable("id") Integer id) {
        // realizalo con optional y .build
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if(!libroOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            return ResponseEntity.ok(libroOptional.get());
        }
    }


}
