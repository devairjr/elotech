package com.devair.elotech.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devair.elotech.domain.Pessoa;
import com.devair.elotech.services.PessoasService;
import com.devair.elotech.services.exceptions.PessoaNaoEncontradaException;
import com.devair.elotech.services.exceptions.PessoaValidacaoCamposNotNullExeception;

@RestController
@RequestMapping("/pessoas")
public class PessoasResource {

	@Autowired
	private PessoasService pessoasServices;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(pessoasServices.listar());
	}
	
	@RequestMapping(value = "/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("cpf") String cpf) {				
		return ResponseEntity.status(HttpStatus.OK).body(pessoasServices.buscar(cpf));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> salvar(@RequestBody Pessoa pessoa) {		
		ResponseEntity<String> response;		
		try {
			pessoa = pessoasServices.salvar(pessoa);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{cpf}").buildAndExpand(pessoa.getCpf()).toUri();
			response = ResponseEntity.created(uri).build();
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			response = ResponseEntity.badRequest().body(e.getMessage().toString());
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> atualizar(@RequestBody Pessoa pessoa) {
		ResponseEntity<String> response;		
		try {
			pessoasServices.atualizar(pessoa);
			response = ResponseEntity.noContent().build();
		} catch (PessoaNaoEncontradaException e) {
			response = ResponseEntity.badRequest().body(e.getMessage().toString());
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			response = ResponseEntity.badRequest().body(e.getMessage().toString());
		}		
		return response;
	}
	
	@RequestMapping(value = "/{cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletar(@PathVariable("cpf") String cpf) {
		ResponseEntity<String> response;		
		try {
			pessoasServices.deletar(cpf);
			response = ResponseEntity.noContent().build();
		} catch(PessoaNaoEncontradaException e) {
			response = ResponseEntity.badRequest().body(e.getMessage().toString());
		}
		return response;
	}
}
