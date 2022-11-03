package com.treinamento.persistence.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import entity.Funcionario;
import entity.Setor;
import persistence.FuncionarioDAO;

public class FuncionarioDAOTest {
	private static Funcionario funcionario;
	private static FuncionarioDAO dao;
	private static Setor setor;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		funcionario = new Funcionario();
		dao = new FuncionarioDAO();
		setor = new Setor();

	}

	@Test
	void testFuncionarioRead() throws Exception {
		List<Funcionario> registros = dao.read();
		for (Funcionario f : registros) {
			System.out.println("---------------------------------");
			System.out.print("ID: ");
			System.out.println(f.getId());
			System.out.print("Nome: ");
			System.out.println(f.getNome());
			System.out.print("Setor: ");
			System.out.println(f.getSetor());
			

		}
		assertNotNull(registros);

	}

	@Test
	void testFuncionarioCreate() throws Exception {
		funcionario.setNome("Teste!");
		funcionario.setDtAniversario(null);
		funcionario.setFoto("teste.png");
		funcionario.setIdSetor(1);
		try {
			dao.create(funcionario);
		} catch (Exception err) {
			err.printStackTrace();
		}

	}

	@Test
	void testFuncionarioUpdate() throws Exception {
		funcionario.setNome("Teste1");
		funcionario.setIdSetor(1);
		funcionario.setDtAniversario(null);
		funcionario.setFoto("novoTesteUpdate");;
		setor.setIdSetor(1); 	  // ID do setor que pertence o funcionario a ser alterado
		try {
			dao.update(1, funcionario);
		} catch (Exception err) {
			err.printStackTrace();
		}
		
	}

	@Test
	void testFuncionarioDelete() {
		try {
			dao.delete(5);
		}catch (Exception err) {
			err.printStackTrace();
		}
		
	}

	@Test
	void testFindFuncionario() throws Exception {
		List<Funcionario> funcionarioFound = dao.find(3);

		System.out.println("---------------------------------");
		System.out.println("ID: " + ((Funcionario) funcionarioFound).getId());
		/*
		 * System.out.println("Nome: " + funcionarioFound.getNome());
		 * System.out.println("ID do setor: " + funcionarioFound.getSetor());
		 * System.out.println("Salario: " + funcionarioFound.getSalario());
		 * System.out.println("Email: " + funcionarioFound.getEmail());
		 */
		assertNotNull(funcionarioFound);
	}
}
