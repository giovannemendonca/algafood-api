package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import javax.swing.plaf.PanelUI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	private static final int COZINHA_ID_INEXISTENTE = 100;

	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;


	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;




	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/v1/cozinhas";
		databaseCleaner.clearTables();
		prepararDados();
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
						"/json/correto/cozinha-chinesa.json");
	}


	@Test
	public void deveRetornaStatus200_QuandoConsultarCozinha() {

		given()
						.accept(ContentType.JSON)
						.when()
						.get()
						.then()
						.statusCode(HttpStatus.OK.value());

	}

	/*@Test
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {

		given()
						.accept(ContentType.JSON)
						.when()
						.get()
						.then()
						.body("", hasSize(quantidadeCozinhasCadastradas))
						.body("nome", hasItems("Tailandesa"));

	}
*/
	@Test
	public void deveRetornaStatus201_QuandoCadastrarCozinha() {

		given()
						.body(jsonCorretoCozinhaChinesa)
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.when()
						.post()
						.then()
						.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornaRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {

		given()
						.pathParam("cozinhaId", cozinhaAmericana.getId())
						.accept(ContentType.JSON)
						.when()
							.get("/{cozinhaId}")
						.then()
							.statusCode(HttpStatus.OK.value())
							.body("nome", equalTo("Americana"));
	}

	@Test
	public void deveRetornaStatus404_QuandoConsultarCozinhaInexistente() {

		given()
						.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
						.accept(ContentType.JSON)
						.when()
						.get("/{cozinhaId}")
						.then()
						.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados(){
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Tailandesa");
		cozinhaRepository.save(cozinha);

		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);

		quantidadeCozinhasCadastradas =(int) cozinhaRepository.count();

	}


}
