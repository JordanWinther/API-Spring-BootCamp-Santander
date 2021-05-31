package com.project.bootcamp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bootcamp.mapper.AcoesMapper;
import com.project.bootcamp.model.Acoes;
import com.project.bootcamp.model.dto.AcoesDto;
import com.project.bootcamp.repository.AcoesRepository;
import com.project.bootcamp.service.AcoesService;


@CrossOrigin
@RequestMapping( value = "/acoes")
@RestController
public class AcoesController{
	
	@Autowired
	private AcoesService service;
	
	@Autowired
	private AcoesMapper mapper;

	@Autowired
	private AcoesRepository repository;
	//Consumes = Diz o tipo de mídia que o método vai receber
	//Produces = Diz o tipo de Mídia que vai enviar para o Front
	
		// SAVE
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
		public  ResponseEntity<AcoesDto> save(@Valid @RequestBody  AcoesDto dto ){
			
			return ResponseEntity.ok(service.save(dto));
			
			}
		
		
		// UPDATE
		@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<AcoesDto> update(@Valid @RequestBody AcoesDto dto){
			
			return ResponseEntity.ok(service.update(dto));
		}
		
		// FIND ALL
		@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<AcoesDto>> findAll(){
			
			return ResponseEntity.ok(service.findAll());
			
		}
		 
		// FIND BY ID
		@GetMapping( value =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Optional<Acoes>> findById(@PathVariable Long id){
			
			return ResponseEntity.ok(service.findById(id));
			
			
		}
		
		@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<AcoesDto> delete(@PathVariable Long id){
			
			return ResponseEntity.ok(service.delete(id));
			
		}
		//TODAY
		@GetMapping(value = "/today")
		public ResponseEntity<Optional<Object>> findByToday(){
			return ResponseEntity.ok(service.findByToday());
		}
		
		
}
