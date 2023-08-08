package com.api.restbiblioteca.librosdatabase.web.controller;

import com.api.restbiblioteca.librosdatabase.domain.repository.IBibliotecaRepository;
import com.api.restbiblioteca.librosdatabase.domain.service.BibliotecaService;
import com.api.restbiblioteca.librosdatabase.persistance.entity.Biblioteca;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-biblioteca")
public class BibliotecaController {

    @Autowired
    private final IBibliotecaRepository bibliotecaRepository;

    @Autowired
    private final BibliotecaService bibliotecaService;
    public BibliotecaController(IBibliotecaRepository bibliotecaRepository, BibliotecaService bibliotecaService) {
        this.bibliotecaRepository = bibliotecaRepository;
        this.bibliotecaService = bibliotecaService;
    }


    // C = create
    @PostMapping
    public ResponseEntity<Biblioteca> guardarBiblioteca(@Valid @RequestBody Biblioteca biblioteca) {

        // realiza una validacion con http para cuando la instancia se pueda guardar y cuando no

        if(isValidBibliotca(biblioteca)) {
            return ResponseEntity.ok(this.bibliotecaService.guardarBiblioteca(biblioteca));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    // R = read
    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> obtenerBiblioteca(@PathVariable int id) {

        // realiza una validacion por http

        if(!this.bibliotecaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(this.bibliotecaService.obtenerBibliotecaPorId(id));
        }

    }

    @GetMapping
    public ResponseEntity<List<Biblioteca>> obtenerTodasLasBibliotecas() {

        // genera tambien una validacion por http

        if(this.bibliotecaRepository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok(this.bibliotecaService.obtenerTodasLasBibliotecas());
        }
    }

    // D = delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable int id) {

        if(!this.bibliotecaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(this.bibliotecaService.eliminarBibliotecaPorId(id));
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Biblioteca> actualizarBiblioteca(@PathVariable Integer id, @Valid @RequestBody Biblioteca biblioteca) {

        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);
        if(!bibliotecaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {

            biblioteca.setId(bibliotecaOptional.get().getId());
            bibliotecaRepository.save(biblioteca);

            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/bibliotecas")
    public ResponseEntity<Page<Biblioteca>> listarBibliotecas(Pageable pageable) {
        return ResponseEntity.ok(bibliotecaRepository.findAll(pageable));
    }
    private boolean isValidBibliotca(Biblioteca biblioteca) {
        return biblioteca.getNombre() != null && !biblioteca.getNombre().isEmpty();
    }
}
