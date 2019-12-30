package com.devair.elotech;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devair.elotech.domain.Pessoa;
import com.devair.elotech.services.PessoasService;
import com.devair.elotech.services.exceptions.PessoaNaoEncontradaException;
import com.devair.elotech.services.exceptions.PessoaValidacaoCamposNotNullExeception;

@SpringBootTest
public class PessoasServicesTest {
	
	@Autowired
	private PessoasService pessoasService;
	
	private Pessoa inserePessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo("1");
		pessoa.setNome("Devair");
		pessoa.setCpf("04873127912");		
		pessoasService.salvar(pessoa);
		return pessoa;
	}
	
	@Test
	public void testeBuscaPessoaPorCpf() {		
		Pessoa pessoa = inserePessoa();
		
		Optional<Pessoa> pessoaBusca = pessoasService.buscar("04873127912");
		assertTrue(pessoaBusca.get().getNome().equals(pessoa.getNome()));
	}
	
	@Test
	public void testeAlteraPessoa() {		
		Pessoa pessoaAlteracao = inserePessoa();
		pessoaAlteracao.setNome("Devair Canedo");
		pessoasService.salvar(pessoaAlteracao);
		
		Optional<Pessoa> pessoaBusca = pessoasService.buscar("04873127912");		
		assertTrue(pessoaBusca.get().getNome().equals(pessoaAlteracao.getNome()));
	}
	
	@Test
	public void testeDeletaPessoaPorCpf() {		
		Pessoa pessoa = inserePessoa();
		pessoasService.deletar(pessoa.getCpf());
		
		try {
			pessoasService.buscar("04873127912");
		} catch (PessoaNaoEncontradaException e) {
			assertTrue("A pessoa não pode ser encontrada!".equals(e.getMessage().toString()));
		}
	}
	
	@Test
	public void testeInserePessoaCpfNull() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo("1");
		pessoa.setNome("Devair");
		try {
			pessoasService.salvar(pessoa);
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			assertTrue("Os campos código, nome e cpf devem ser preenchidos!".equals(e.getMessage().toString()));
		}
	}
	
	@Test
	public void testeInserePessoaNomeNull() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo("1");
		pessoa.setCpf("04873127912");
		try {
			pessoasService.salvar(pessoa);
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			assertTrue("Os campos código, nome e cpf devem ser preenchidos!".equals(e.getMessage().toString()));
		}
	}
	
	@Test
	public void testeInserePessoaCodigoNull() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Devair");
		pessoa.setCpf("04873127912");
		try {
			pessoasService.salvar(pessoa);
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			assertTrue("Os campos código, nome e cpf devem ser preenchidos!".equals(e.getMessage().toString()));
		}
	}
	
	@Test
	public void testeAtualizaPessoaCpfNull() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo("1");
		pessoa.setNome("Devair");
		try {
			pessoasService.atualizar(pessoa);
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			assertTrue("Os campos código, nome e cpf devem ser preenchidos!".equals(e.getMessage().toString()));
		}
	}
	
	@Test
	public void testeAtualizaPessoaNomeNull() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo("1");
		pessoa.setCpf("04873127912");
		try {
			pessoasService.atualizar(pessoa);
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			assertTrue("Os campos código, nome e cpf devem ser preenchidos!".equals(e.getMessage().toString()));
		}
	}
	
	@Test
	public void testeAtualizaPessoaCodigoNull() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Devair");
		pessoa.setCpf("04873127912");
		try {
			pessoasService.atualizar(pessoa);
		} catch (PessoaValidacaoCamposNotNullExeception e) {
			assertTrue("Os campos código, nome e cpf devem ser preenchidos!".equals(e.getMessage().toString()));
		}
	}
}
