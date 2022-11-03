package com.treinamento.rest.test;

import java.util.Random;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Funcionario;


class FuncionarioCRUDTest extends JerseyTest {
	private static int CodigoEsperado;
	private static Funcionario funcionario;
	private static Response resposta;
	private static Random random;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CodigoEsperado = 200;
		funcionario = new Funcionario();
		random = new Random();

	}

	@Test
	public void testFuncionarioCRUDServices() {

		// Body da requisição para o create
		
		funcionario.setNome("testServiceCREATE");
		funcionario.setDtAniversario(null);
		funcionario.setFoto("testeServiceCreate.png");
		funcionario.setIdSetor(4);

		// Teste de criação - CREATE
		
		try {
			WebTarget webTarget = target("http://localhost:8080/projeto");
			WebTarget funcionarioTarget = webTarget.path("/rest/funcionario/criar");
			Invocation.Builder reqFuncionarioCreate = funcionarioTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqFuncionarioCreate.post(Entity.entity(funcionario, MediaType.APPLICATION_JSON));
			
			assert (resposta.getStatus() == CodigoEsperado);

		} catch (Exception err) {
			err.printStackTrace();
		}

		// Teste de listagem - READ
		
		try {
			WebTarget webTarget = target("http://localhost:8080/projeto");
			WebTarget funcionarioTarget = webTarget.path("/rest/funcionario/listar");
			Invocation.Builder reqFuncionarioRead = funcionarioTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqFuncionarioRead.get();
			
			assert (resposta.getStatus() == CodigoEsperado);

		} catch (Exception err) {
			err.printStackTrace();
		}

		// Teste de buscar um funcionario - Find one by ID
		
		try {
			int id = random.nextInt(5) + 1; // Criando um id aleatorio (1-5) para cada execução do teste
			WebTarget webTarget = target("http://localhost:8080/projeto");
			WebTarget funcionarioTarget = webTarget.path("/rest/funcionario/buscar/" + id); // Passando o id gerado
			Invocation.Builder reqFuncionarioFind = funcionarioTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqFuncionarioFind.get();
			
			assert (resposta.getStatus() == CodigoEsperado);

		} catch (Exception err) {
			err.printStackTrace();
		}

		// Teste de atualizar um funcionario - UPDATE

		try {
			// Body da requisição para o update
			
			funcionario.setNome("testServiceUpdate");
			funcionario.setDtAniversario(null);
			funcionario.setFoto("testeServiceUpdate.png");
			funcionario.setIdSetor(3);

			WebTarget webTarget = target("http://localhost:8080/projeto");
			WebTarget funcionarioTarget = webTarget.path("/rest/funcionario/atualizar/3");
			Invocation.Builder reqFuncionarioUpdate = funcionarioTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqFuncionarioUpdate.put(Entity.entity(funcionario, MediaType.APPLICATION_JSON));
			
			assert (resposta.getStatus() == CodigoEsperado);

		} catch (Exception err) {
			err.printStackTrace();
		}

		// Teste de excluir funcionarios - DELETE
		
		try {
			WebTarget webTarget = target("http://localhost:8080/projeto");
			WebTarget funcionarioTarget = webTarget.path("/rest/funcionario/excluir/2");
			Invocation.Builder reqFuncionarioDelete = funcionarioTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqFuncionarioDelete.delete();
			
			assert (resposta.getStatus() == CodigoEsperado);

		} catch (Exception err) {
			err.printStackTrace();
		}

	}

}
