package com.treinamento.rest.test;

import static io.restassured.RestAssured.given;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Setor;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

class SetorCrudRestAssuredTest {

	private static Setor setor;
	private static int CodigoEsperado;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setor = new Setor();
		CodigoEsperado = 200;
	}

	@Test
	void testSetorCRUDRestAssured() {

		// Teste de criação - CREATE
		
		setor.setNome("SetorInsert");

		 Response respostaCreate = given().body(setor)
				 .contentType(ContentType.JSON)
				 .when()
				 .post("/rest/setor/create");
		 
		assert(respostaCreate.getStatusCode() == CodigoEsperado);

		// Teste de listagem - READ
		
		Response respostaRead =	given().get("/rest/setores");
		
		assert(respostaRead.getStatusCode() == CodigoEsperado);

		// Teste de buscar pelo ID - Find one by Id

		Response respostaFind = given().get("/rest/setor/3");
		
		assert(respostaFind.getStatusCode() == CodigoEsperado);
		
		// Teste de atualização - UPDATE
		
		setor.setNome("Novo nome");
		Response respostaUpdate = given().body(setor)
				.contentType(ContentType.JSON)
				.when()
				.put("/rest/setor/atualizar/4");
		
		assert(respostaUpdate.getStatusCode() == CodigoEsperado);
		
		// Teste de deleção - DELETE
		
		Response respostaDelete = given().delete("/rest/setor/delete/4");
		
		assert(respostaDelete.getStatusCode() == CodigoEsperado);
	}

}
