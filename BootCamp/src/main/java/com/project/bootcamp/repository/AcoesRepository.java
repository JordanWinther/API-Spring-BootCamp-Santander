package com.project.bootcamp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.bootcamp.model.Acoes;
import com.project.bootcamp.model.dto.AcoesDto;

@Repository  
public interface AcoesRepository extends JpaRepository<Acoes, Long> {

	@Query(" select acoes from Acoes acoes where acoes.date = CURRENT_DATE")
	Optional<List<Acoes>> findByToday (LocalDate date);

	
}
