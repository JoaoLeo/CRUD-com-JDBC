package com.treinamento.persistence.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import entity.Setor;
import persistence.SetorDAO;


public class SetorDAOTest {
	private static SetorDAO dao;
	private static  Setor setor;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dao = new persistence.SetorDAO();
		setor = new Setor();
	}

	@Test
	void testCreateSetor() {
		setor.setNome("teste1");
		try {
			dao.create(setor);
		} catch (Exception err) {
			err.printStackTrace();
		}
		System.out.print("Setor criado");
	}

	@Test
	void testReadSetor() {
		List<Setor> setores = dao.read();
		for (Setor s : setores) {
			System.out.print("ID: " + s.getIdSetor());
			System.out.print("Nome: " + s.getNome());
		}
		assertNotNull(setores);
	}

	@Test
	void findSetor() {
		List<Setor> setorFound = new ArrayList<Setor>();
		try {
			setorFound = dao.findSetor(1);
		} catch (Exception err) {
			err.printStackTrace();
		}
		assertNotNull(setorFound);
	}
	@Test
	void updateSetor() {
		setor.setNome("Tech");
		
		try {
			dao.update(1, setor);
		}catch (Exception err) {
			err.printStackTrace();
		}
		System.out.print("setor atualizado");
		
	}

	@Test
	void deleteSetor() {
		try {
			dao.delete(1);
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
}
