package br.fr.abade.rest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.fr.abade.rest.core.BaseTest;
import br.fr.abade.rest.utils.DataUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.hamcrest.Matchers;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)   //executar os testes em ordem alfabetica.
public class DesafioTest extends BaseTest{


	/**
	 * Funcionalidade: Realizar a requisi��o GET /api/v1/restricoes/{cpf}
	 * Cenario: 01_Consultar uma restri��o pelo CPF
	 * Resultado esperado: codigo de retorno Status 200.
	 */
	@Test
	public void t01_consultarRestricaoPeloCpf() {
		given()
		.when()
			.log().all()
			.get("http://localhost:8080/api/v1/restricoes/" + CPF_RESTRICAO)
		.then()
			.statusCode(200)	
			.body("mensagem", containsString("O CPF 97093236014 possui restri��o"))							
		;
	}
	
	/**
	 * Funcionalidade: Realizar a requisi��o POST <host>/api/v1/simulacoes
	 * Cenario: Cadastrar simula��o com sucesso
	 *  Resultado esperado: codigo de retorno Status 201.
	 */
	@Test
	public void t02_deveIncluirSimulacaoComSucesso() {
		
		Map<String, String> simulacao = new HashMap<String, String>();
		simulacao.put("cpf", CPF_SIMULACAO);
		simulacao.put("nome", NOME);
		simulacao.put("email", EMAIL);
		simulacao.put("valor", VALOR);
		simulacao.put("parcelas", PARCELAS);
		simulacao.put("seguro", SEGURO);
		
		 given()
				.body(simulacao)				
		.when()
			.post("http://localhost:8080/api/v1/simulacoes") 
		.then()
		.log().all()
			.statusCode(201)		
			.body("nome", Matchers.is(NOME))
			.body("cpf", Matchers.is(CPF_SIMULACAO));		 
			Assert.assertEquals(NOME, "FrSystem");
		
	}
	
	/**
	 * Funcionalidade: Realizar a requisi��o POST <host>/api/v1/simulacoes
	 * Cenario: Cadastrar simula��o com algum problema
	 *  Resultado esperado: codigo de retorno Status 400.
	 */
	@Test
	public void t03_deveIncluirSimulacaoComErro() {
		
		Map<String, String> simulacao = new HashMap<String, String>();
		simulacao.put("cpf", CPF_SIMULACAO_COM_ERRO);
		simulacao.put("nome", NOME);
		simulacao.put("email", EMAIL);
		simulacao.put("valor", VALOR);
		simulacao.put("parcelas", PARCELAS_MENOR_PERMITIDO);
		simulacao.put("seguro", SEGURO);
		
		 given()
				.log().all()
				.body(simulacao)				
		.when()
			.post("http://localhost:8080/api/v1/simulacoes") 
		.then()
		.log().all()
			.statusCode(400);
			Assert.assertThat("parcelas=Parcelas deve ser igual ou maior que 2",
					Matchers.is("parcelas=Parcelas deve ser igual ou maior que 2"));
		
	}
	
	/**
	 * Funcionalidade: Realizar a requisi��o POST <host>/api/v1/simulacoes
	 * Cenario: Cadastrar simula��o com o mesmo cpf
	 *  Resultado esperado: codigo de retorno Status 409, mensagem "CPF ja Existente"
	 */
	@Test
	public void t04_deveIncluirSimulacaoComMesmoCpf() {
		
		Map<String, String> simulacao = new HashMap<String, String>();
		simulacao.put("cpf", CPF_SIMULACAO);
		simulacao.put("nome", NOME);
		simulacao.put("email", EMAIL);
		simulacao.put("valor", VALOR);
		simulacao.put("parcelas", PARCELAS);
		simulacao.put("seguro", SEGURO);
		
		 given()
				.log().all()
				.body(simulacao)				
		.when()
			.post("http://localhost:8080/api/v1/simulacoes") 
		.then()
		.log().all()
			.statusCode(409)
			.body("mensagem", Matchers.is("CPF j� existente"));
	}
	
	/**
	 * Funcionalidade: Realizar a requisi��o PUT <host>/api/v1/simulacoes/{cpf}
	 * Cenario: Alterar uma simula��o ja existente com sucesso
	 * Resultado esperado: codigo de retorno Status 200.
	 */
	@Test
	public void t05_deveAlterarSimulacaoComSucesso() {
		
		Map<String, String> simulacao = new HashMap<String, String>();
		simulacao.put("cpf", CPF_SIMULACAO);
		simulacao.put("nome", NOME_ALTERACAO);
		simulacao.put("email", EMAIL_ALTERACAO);
		simulacao.put("valor", VALOR);
		simulacao.put("parcelas", PARCELAS);
		simulacao.put("seguro", SEGURO);
		
		given()		
			.body(simulacao)
		.when()
			.put("http://localhost:8080/api/v1/simulacoes/" + CPF_SIMULACAO)
		.then()
		.log().all()
		.statusCode(200)
		.body("cpf", Matchers.is(CPF_SIMULACAO))
		.body("nome", Matchers.is(NOME_ALTERACAO))
		.body("email", Matchers.is(EMAIL_ALTERACAO))
		;				
	}
	
	/**
	 * Funcionalidade: Realizar a requisi��o GET <host>/api/v1/simulacoes
	 * Cenario: Listar as simula��o cadastradas
	 *  Resultado esperado: codigo de retorno Status 200.
	 */
	@Test
	public void t06_consultarTodasSimulacoesCadastradas() {
		
		given()
		.when()
			.get("http://localhost:8080/api/v1/simulacoes")
		.then()
		.log().all()
		.statusCode(200)
		;				
	}
	
	/**
	 * Funcionalidade: Realizar a requisi��o GET <host>/api/v1/simulacoes/{cpf}
	 * Cenario: Consultar uma simula��o pelo CPF.
	 *  Resultado esperado: codigo de retorno Status 200.
	 */
	@Test
	public void t07_consultarSimulacaoPeloCpf() {
		
		given()
		.when()
			.get("http://localhost:8080/api/v1/simulacoes/" + CPF_SIMULACAO)
		.then()
		.log().all()
		.statusCode(200)
		.body("cpf", Matchers.is(CPF_SIMULACAO))
		;				
	}
	
	/**
	 * Funcionalidade: Realizar a requisi��o DELETE <host>/api/v1/simulacoes/{id}
	 * Cenario: Remove uma simula��o previamente cadastrada pelo seu ID.
	 *  Resultado esperado: codigo de retorno Status 204.
	 */
	@Test
	public void t08_removerSimulacao() {
		
		Response response = RestAssured.request(Method.GET, "http://localhost:8080/api/v1/simulacoes/");		
		
		//jsonpath
		JsonPath jpath = new JsonPath(response.asString());
		
		//from  //pegando o numero do id
		int id = JsonPath.from(response.asString()).getInt("[0].id");
		System.out.println("id");		
		
		given()
		.when()
			.delete("http://localhost:8080/api/v1/simulacoes/" + id)
		.then()
		.log().all()
		.statusCode(204)
		.body(is(not(nullValue())))
		;				
	}	
	
}
