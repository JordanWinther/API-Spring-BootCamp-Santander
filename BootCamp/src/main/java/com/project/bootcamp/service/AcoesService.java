package com.project.bootcamp.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.message.Message;
import org.apache.tomcat.util.buf.MessageBytes;
import org.hibernate.annotations.NotFound;
import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.project.bootcamp.mapper.AcoesMapper;
import com.project.bootcamp.model.Acoes;
import com.project.bootcamp.model.dto.AcoesDto;
import com.project.bootcamp.repository.AcoesRepository;


@Service
public class AcoesService  {
	
	@Autowired
	private AcoesMapper mapper;
	
	@Autowired
	private AcoesRepository repository;

	
	//TODAY
	@Transactional
	public Optional<Object> findByToday(){
		
	return	repository.findByToday(LocalDate.now()).map(mapper::toDto);
	//LocalDate.now envia a data atual para o repository
	//.map converte a lista 
	}
	
	
	//FIND ALL
	@Transactional(readOnly = true)
	public List<AcoesDto> findAll() {
		List<Acoes> lista = repository.findAll();
		
		return mapper.toDto(lista);
		
	}
	
	
		
	//FIND BY ID
	@Transactional(readOnly = true)
	public Optional<Acoes>  findById(Long id) {
		
		if (!repository.findById(id).isPresent()) {
			 
			 //return  mapper.IdNull(id));
				}
		return repository.findById(id);
	}

	
	
	//Save recebe dto do Controller, transforma em entidade, salva no banco e retorna dto para o Controller.
	@Transactional													//Commit e Roll back 
	public AcoesDto save(AcoesDto dto) {
			 
				//Preencher a entidade com o dados do Dto
				Acoes acoes = mapper.toEntity(dto); 
				//Salva Entidade no banco
				repository.save(acoes);	
				return mapper.toDto(acoes);
				
		}
	

	@Transactional
	public AcoesDto update(AcoesDto dto)  {
		Optional<Acoes> acoesDto = repository.findById(dto.getId()); 
		Acoes acoes = mapper.toEntity(dto); 
		
			if (!acoesDto.isPresent()) {// Se o id  do dto, não existir no banco, ele entra na condição
				return	mapper.IdNull(dto);
			}
		
		repository.save(acoes);				//Id existe então salva.
		return mapper.toDto(acoes);
	}


	@Transactional
	public  AcoesDto delete( Long id) {
		Acoes acoes = repository.getById(id);
		
		 repository.delete(acoes);
		return null;
	
		
	}
	
	}
	
