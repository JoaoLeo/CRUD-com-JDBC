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

import entity.Setor;



class SetorCRUDTest extends JerseyTest {
	private static int CodigoEsperado;
	private static Setor setor;
	private static Response resposta;
	private static Random random;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CodigoEsperado = 200;
		setor = new Setor();
		random = new Random();
	}

	@Test
	void testSetorCRUDServices() {
		// Body da requisição para o create
		
		setor.setNome("TesteService");

		// Teste de criação - CREATE
		
		try {
			WebTarget webTarget = target("http://localhost:8080/treinamento");
			WebTarget setorTarget = webTarget.path("/rest/setor/create");
			Invocation.Builder reqSetorCreate = setorTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqSetorCreate.post(Entity.entity(setor, MediaType.APPLICATION_JSON));
			
			assert (resposta.getStatus() == CodigoEsperado);

		} catch (Exception err) {
			err.printStackTrace();
		}

		// Teste de listagem - READ

		try {
			WebTarget webTarget = target("http://localhost:8080/treinamento");
			WebTarget setorTarget = webTarget.path("/rest/setores");
			Invocation.Builder reqSetorRead = setorTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqSetorRead.get();
			
			assert (resposta.getStatus() == CodigoEsperado);

		} catch (Exception err) {
			err.printStackTrace();
		}

		// Teste de buscar um setor - Find one by ID

		try {
			int id = random.nextInt(4) + 1; // Criando um id aleatorio (1-4) para cada execução do teste
			WebTarget webTarget = target("http://localhost:8080/treinamento");
			WebTarget setorTarget = webTarget.path("/rest/setor/" + id);
			Invocation.Builder reqSetorRead = setorTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqSetorRead.get();
			
			assert (resposta.getStatus() == CodigoEsperado);
			
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		// Teste de atualizar um setor - UPDATE
		
		try {
			// Body da requisição para o update
			setor.setIdSetor(3);
			setor.setNome("UpdateServiceTest");
			
			WebTarget webtarget = target("http://localhost:8080/treinamento");
			WebTarget setorTarget = webtarget.path("/rest/setor/update");
			Invocation.Builder reqSetorUpdate = setorTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqSetorUpdate.put(Entity.entity(setor, MediaType.APPLICATION_JSON));
			
			assert(resposta.getStatus() == CodigoEsperado);
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		// Teste de excluir setores - DELETE
		
		try {
			WebTarget webtarget = target("http://localhost:8080/treinamento");
			WebTarget setorTarget = webtarget.path("/rest/setor/delete/4");
			Invocation.Builder reqSetorUpdate = setorTarget.request(MediaType.APPLICATION_JSON);
			resposta = reqSetorUpdate.delete();
			
			assert(resposta.getStatus() == CodigoEsperado);
			
		} catch(Exception err) {
			err.printStackTrace();
		}
	}
}
