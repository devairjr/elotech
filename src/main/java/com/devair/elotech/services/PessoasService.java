package com.devair.elotech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devair.elotech.domain.Pessoa;
import com.devair.elotech.repository.PessoasRepository;
import com.devair.elotech.services.exceptions.PessoaNaoEncontradaException;
import com.devair.elotech.services.exceptions.PessoaValidacaoCamposNotNullExeception;

@Service
public class PessoasService {

	@Autowired
	private PessoasRepository pessoasRepository;
	
	public List<Pessoa> listar() {
		return pessoasRepository.findAll();
	}
	
	public Optional<Pessoa> buscar(String cpf) {
		Optional<Pessoa> pessoa = pessoasRepository.findById(cpf);
		
		if (pessoa.isEmpty()) {
			throw new PessoaNaoEncontradaException("A pessoa não pode ser encontrada!");
		}
		
		return pessoa;
	}

	public Pessoa salvar(Pessoa pessoa) {
		
		if (validacaoCamposNull(pessoa)) {		
			return pessoasRepository.save(pessoa);
		}else {
			throw new PessoaValidacaoCamposNotNullExeception("Os campos código, nome e cpf devem ser preenchidos!");
		}
	}

	private boolean validacaoCamposNull(Pessoa pessoa) {		
		boolean valido = true;
		
		if (pessoa.getCodigo() == null) {
			valido = false;
		}else if (pessoa.getNome() == null) {
			valido = false;
		}else if (pessoa.getCpf() == null) {
			valido = false;
		}
		
		return valido;
	}

	public void atualizar(Pessoa pessoa) {
		if (validacaoCamposNull(pessoa)) {
			verificaPessoa(pessoa);
			pessoasRepository.save(pessoa);	
		}else {
			throw new PessoaValidacaoCamposNotNullExeception("Os campos código, nome e cpf devem ser preenchidos!");
		}
		
	}

	private void verificaPessoa(Pessoa pessoa) {
		buscar(pessoa.getCpf());
	}

	public void deletar(String cpf) {
		try {
			pessoasRepository.deleteById(cpf);
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException("A pessoa não pôde ser encontrado!");
		}	
	}

}
