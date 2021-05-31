package com.project.bootcamp.mapper;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.project.bootcamp.model.Acoes;
import com.project.bootcamp.model.dto.AcoesDto;

@Component
public class AcoesMapper {

	//Recebe um dto do Service, salva as informações em uma Entidade, e retorna o entity para o Service.
	public Acoes toEntity( AcoesDto dto) {
		
		Acoes acoes = new Acoes();
		acoes.setId(dto.getId());
		acoes.setNome(dto.getNome());
		acoes.setPrice(dto.getPrice());
		acoes.setDate(dto.getDate());
		acoes.setVariation(dto.getVariation());
		
		return acoes;
	}

	public AcoesDto toDto( Acoes acoes) {
		AcoesDto dto2 =  new AcoesDto();
		dto2.setId(acoes.getId());
		dto2.setNome(acoes.getNome());
		dto2.setPrice(acoes.getPrice());
		dto2.setDate(acoes.getDate());
		dto2.setVariation(acoes.getVariation());
		
		return dto2;
	}
	
	public List<AcoesDto> toDto( List<Acoes> list){
		
		return list.stream().map(this::toDto).collect(Collectors.toList());
		//Stream percorre a lista, Map converte cada item da lista usando o toDto
		//a lista vira um collect
		//.collectors.tolist transforma em uma lista novamente.
	}

	//Mensagem id não encontrado
	public AcoesDto IdNull( AcoesDto dto ) {
		AcoesDto Null = new AcoesDto();
		Null.setNome("Id "+dto.getId()+" não encontrado"); 
		Null.setDate(null);
		Null.setPrice(null);
		Null.setVariation(null);
		Null.setId(null);
		return Null;
	}

	public AcoesDto IdNull(Long id) {
		AcoesDto Null = new AcoesDto();
		Null.setNome("Id "+id+" não encontrado"); 
		Null.setDate(null);
		Null.setPrice(null);
		Null.setVariation(null);
		Null.setId(null);
		return Null;
		
	}
}
