package br.fr.abade.rest.core;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTest implements Constantes {

	@BeforeClass
	public static void setup() {
		System.out.println("Passou aqui");
		RestAssured.basePath = APP_BASE_URL;
		System.out.println(APP_BASE_URL);
		RestAssured.port = APP_PORT;
		RestAssured.basePath = APP_BASE_PATH;
		
		RequestSpecBuilder reqBuild = new RequestSpecBuilder();
		reqBuild.setContentType(APP_CONTENT_TYPE);
		RestAssured.requestSpecification = reqBuild.build();
		
		ResponseSpecBuilder resBuild = new ResponseSpecBuilder();
		resBuild.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
