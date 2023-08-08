package com.api.restbiblioteca.librosdatabase.domain.repository;

import com.api.restbiblioteca.librosdatabase.persistance.entity.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBibliotecaRepository extends JpaRepository<Biblioteca, Integer> {


}
