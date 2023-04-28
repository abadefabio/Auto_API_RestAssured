package br.fr.abade.rest.core;

import io.restassured.http.ContentType;

public interface Constantes {
	
	
	String APP_BASE_URL = "http://localhost:8080/";
	Integer APP_PORT = 80; //http ->80
	String APP_BASE_PATH = "";
	String CPF_RESTRICAO = "97093236014";
	String CPF_SIMULACAO = "58063164083";
	String CPF_SIMULACAO_COM_ERRO = "24094592008";
	
	String NOME = "FrSystem";
	String NOME_ALTERACAO = "frSystem Tecnologia";
	String EMAIL = "fr@gmail.com";
	String EMAIL_ALTERACAO = "frSystem@gmail.com";
	String VALOR = "1150";
	String PARCELAS = "3";
	String PARCELAS_MENOR_PERMITIDO = "1";
	String SEGURO = "false";		
	
	ContentType APP_CONTENT_TYPE = ContentType.JSON;	
	Long MAX_TIMEOUT = 8000L;
}
