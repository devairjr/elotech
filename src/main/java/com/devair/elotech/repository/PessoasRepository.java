package com.devair.elotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devair.elotech.domain.Pessoa;

public interface PessoasRepository extends JpaRepository<Pessoa, String>{

}
