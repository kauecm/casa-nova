package com.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.domain.Convidados;
import com.backend.domain.Presentes;
@Repository
public interface PresentesRepository extends JpaRepository<Presentes, Integer> {

	@Transactional(readOnly=true)
	Page<Presentes> findByConvidados(Convidados convidado, Pageable page);
}
