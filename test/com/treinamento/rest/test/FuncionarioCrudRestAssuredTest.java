package com.treinamento.rest.test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Funcionario;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

class FuncionarioCrudRestAssuredTest {
	private static Funcionario funcionario;
	private static int CodigoEsperado;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		funcionario = new Funcionario();
		CodigoEsperado = 200;
	}

	@Test
	void testFuncionarioCRUDRestAssured() {
		
		//Teste de criação - CREATE
		
		funcionario.setNome("TesteInsertREST");
		funcionario.setDtAniversario(null);
		funcionario.setFoto("insertREST.png");
		funcionario.setIdSetor(1);
		
		Response respostaCreate = given().body(funcionario)
				 .contentType(ContentType.JSON)
				 .when()
				 .post("http://localhost:8080/projeto/rest/funcionario/criar");
		assert(respostaCreate.getStatusCode() == CodigoEsperado);
		
		// Teste de listagem - READ
		Response respostaRead = given().contentType(ContentType.JSON).when().get("http:http://localhost:8080/projeto/rest/funcionario/listar");
		
		assert(respostaRead.getStatusCode() == CodigoEsperado);
		
		// Teste de buscar pelo ID - Find one by Id
		
		Response respostaFind = given().get("http://localhost:8080/projeto/rest/funcionario/buscar/2");
		
		assert(respostaFind.getStatusCode() == CodigoEsperado);
		
		// Teste de atualização - UPDATE
		
		
		funcionario.setNome("TesteUpdateREST");
		funcionario.setDtAniversario(null);
		funcionario.setFoto("updateREST.png");
		funcionario.setIdSetor(3);
		
		Response respostaUpdate = given().body(funcionario)
				.contentType(ContentType.JSON)
				.when()
				.put("http://localhost:8080/projeto/rest/funcionario/atualizar/4");
		
		assert(respostaUpdate.getStatusCode() == CodigoEsperado);
		
		// Teste de deleção - DELETE
		
	}

}
