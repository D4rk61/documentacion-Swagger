package com.api.restbiblioteca.librosdatabase.domain.repository;

import com.api.restbiblioteca.librosdatabase.persistance.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer> {

    Libro findById(int id);

}
