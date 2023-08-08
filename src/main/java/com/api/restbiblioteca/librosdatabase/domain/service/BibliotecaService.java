package com.api.restbiblioteca.librosdatabase.domain.service;

import com.api.restbiblioteca.librosdatabase.domain.repository.IBibliotecaRepository;
import com.api.restbiblioteca.librosdatabase.persistance.entity.Biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecaService {


    private final IBibliotecaRepository bibliotecaRepository;

    @Autowired
    public BibliotecaService(IBibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    // metodo save
    public Biblioteca guardarBiblioteca(Biblioteca biblioteca) {
        return this.bibliotecaRepository.save(biblioteca);
    }

    // obtener biblioteca por id
    public Biblioteca obtenerBibliotecaPorId(Integer id) {
        return this.bibliotecaRepository.findById(id).orElse(null);
    }

    public List<Biblioteca> obtenerTodasLasBibliotecas() {
        return this.bibliotecaRepository.findAll();
    }

    public Biblioteca eliminarBibliotecaPorId(Integer id) {
        Biblioteca biblioteca = this.obtenerBibliotecaPorId(id);
        this.bibliotecaRepository.deleteById(id);
        return biblioteca;
    }
}