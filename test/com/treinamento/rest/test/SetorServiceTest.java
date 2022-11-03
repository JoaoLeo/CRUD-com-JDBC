package com.treinamento.rest.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Setor;
import rest.SetorService;


class SetorServiceTest {
	private static Setor setor;
	private static SetorService service;
	private static Response resposta;
	private static int CodigoEsperado;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setor = new Setor();
		service = new SetorService();
		CodigoEsperado = 200;
	}

	@Test
	void testCreateSetor() {
		setor.setIdSetor(7);
		setor.setNome("testeService");
		try {
			resposta = service.createSetor(setor);
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void testReadSetor() {
		try {
			resposta = service.readSetor();
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void testFindSetor() {
		try {
			resposta = service.findSetor(4);
		} catch (Exception err) {
			err.printStackTrace();
		}

		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void testUpdateSetor() {
		setor.setIdSetor(7);
		setor.setNome("updateService");
		try {
			resposta = service.updateSetor(2,setor);
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

	@Test
	void testDeleteSetor() {
		try {
			resposta = service.deleteSetor(3);
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertEquals(CodigoEsperado, resposta.getStatus());
	}

}
