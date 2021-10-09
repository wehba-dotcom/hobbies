/*
package rest;

import dtos.HoppyDTO;
import entities.Hoppy;
import entities.Person;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.MissingFormatWidthException;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class HoppyResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Hoppy h1, h2, h3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }


    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }




    @BeforeEach
    void setUp()
    {
        EntityManager em = emf.createEntityManager();
        h1 = new Hoppy("sport","runing");
       //h1.addPerson(new Person("we@wew","wassem","hamore"));
        h2= new Hoppy("swaimm","fresh boday");
        //h2.addPerson(new Person("gde@ono.dk","wagr","korouni"));
        h3= new Hoppy("boxing","good armes");
        //h3.addPerson(new Person("wert@we.com","sammer","alharfoosh"));
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Hoppy.deleteAllRows").executeUpdate();

            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Test
    void hello() {
        given().
        when().get("/hoppy").
                then().statusCode(200);
    }

    @Test
    void getAllHobbies() throws HoppyNotFoundException {

     List<HoppyDTO> hoppyDTOList;
        hoppyDTOList =
                given().contentType("Application/json")
                        .when().get("/hoppy/all")
                        .then().extract().body().jsonPath().getList("", HoppyDTO.class);
            HoppyDTO h1DTO = new HoppyDTO(h1);
            HoppyDTO h2DTO = new HoppyDTO(h2);
            HoppyDTO h3DTO = new HoppyDTO(h3);
            assertThat(hoppyDTOList, containsInAnyOrder(h1DTO,h2DTO,h3DTO));

    }

    @Test
    public void addHoppy(){
        given().contentType(ContentType.JSON)
                .body(new HoppyDTO("sport","good"))
                .when()
                .post("/hoppy/add")
                .then()
                .body("name",equalTo("sport"))
                .body("description",equalTo("good"))
                .body("id",notNullValue());
    }

    @Test
    void editHoppy() {
        HoppyDTO hoppy= new HoppyDTO(h1);
        h1.setName("sport");
        given()
                .contentType(ContentType.JSON)
                .body(hoppy)
                .when()
                .put("hoppy/"+h1.getId())
                .then()
                .body("name",equalTo("sport"))
                .body("description",equalTo("runing"))
                .body("id",equalTo((int)hoppy.getId()));

    }

    @Test
    void delete()throws MissingInputException,HoppyNotFoundException {
        HoppyDTO hoppy = new HoppyDTO(h1);
        given()
                .contentType("application/json")
                .delete("hoppy/" + hoppy.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
       
    }
    }
*/
