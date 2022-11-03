package com.treinamento.rest.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Funcionario;
import rest.FuncionarioService;


class FuncionarioServiceTest {
	private static int CodigoEsperado;
	private static FuncionarioService service;
	private static Funcionario funcionario;
	private static Response resposta;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		service = new FuncionarioService();
		funcionario = new Funcionario();
		CodigoEsperado = 200;

	}

	@Test
	void testCreateFuncionario() {

		funcionario.setNome("testeService");
		funcionario.setDtAniversario(null);
		funcionario.setFoto("testeService.png");
		funcionario.setIdSetor(1);
		try {
			resposta = service.createFuncionario(funcionario);
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertEquals(CodigoEsperado, resposta.getStatus());

	}

	@Test
	void testReadFuncionario() {
		try {
			resposta = service.readFuncionario();
			
		} catch (Exception err) {
			err.printStackTrace();
		}
		// assertTrue(resposta.getStatus() == CodigoEsperado); - Outra possivel forma de validar
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void testFindFuncionario() {
		try {
			resposta = service.findFuncionario(2);

		} catch (Exception err) {
			err.printStackTrace();
		}
		
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void testUpdateFuncionario() {
		funcionario.setId(1);
		funcionario.setNome("testeUpdate");
		funcionario.setDtAniversario(null);
		funcionario.setFoto("testeUpdate.png");
		funcionario.setIdSetor(4);
		try {
			resposta = service.updateFuncionario(5,funcionario);
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void testDeleteFuncionario() {
		try {
			resposta = service.deleteFuncionario(2);
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void test() {
		assertTrue(true);
	}
}
