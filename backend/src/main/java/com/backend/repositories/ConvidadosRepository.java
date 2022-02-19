package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.domain.Convidados;
@Repository
public interface ConvidadosRepository extends JpaRepository<Convidados, Integer>{
	
	@Transactional(readOnly = true)
	Convidados findByEmail(String email);

}
