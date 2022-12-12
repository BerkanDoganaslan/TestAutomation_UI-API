package stepdefinition;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utilities.ConfigReader;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TrelloApiStepDef {
    String listId;
    String[] cardsIdArr;
    String boardId;

    @Before
    public static void setup(){
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");
        RestAssured.basePath = ConfigReader.getProperty("basePath");
    }
    @Given("User creates a board on trello")
    public void User_creates_a_board_on_trello() {
        boardId =
                given()
                        .contentType("application/json").
                        when()
                        .queryParam("key", ConfigReader.getProperty("key"))
                        .queryParam("token", ConfigReader.getProperty("token"))
                        .queryParam("name", "TrelloBoard")
                        .post("/boards").
                        then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).
                        assertThat()
                        .body("name", equalTo("TrelloBoard"))
                        .extract().path("id");

        listId =
                given()
                        .contentType("application/json")
                        .when()
                        .queryParam("key", ConfigReader.getProperty("key"))
                        .queryParam("token", ConfigReader.getProperty("token"))
                        .queryParam("name","TrelloList")
                        .post("/boards/"+boardId+"/lists")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .assertThat()
                        .body("name", equalTo("TrelloList"))
                        .extract().path("id");
    }
    @Then("User creates two cards on the created board")
    public void User_creates_two_cards_on_the_created_board() {
        cardsIdArr = new String[2];


        for (int i = 0; i < 2; i++) {
            cardsIdArr[i] = given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", ConfigReader.getProperty("key"))
                    .queryParam("token", ConfigReader.getProperty("token"))
                    .queryParam("name","newCard"+i)
                    .queryParam("idList",listId)
                    .post("/cards").
                    then()
                    .statusCode(200)
                    .contentType(ContentType.JSON).
                    assertThat()
                    .body("name", equalTo("newCard"+i))
                    .extract().path("id");
        }
    }
    @Then("User updates a random number of created cards")
    public void User_updates_a_random_number_of_created_cards() {
        Random rn = new Random();
        int a = rn.nextInt(2);
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigReader.getProperty("key"))
                .queryParam("token", ConfigReader.getProperty("token"))
                .queryParam("name","newCardRandom")
                .queryParam("desc","edited Test Description")
                .put("/cards/"+cardsIdArr[a]).
                then()
                .statusCode(200)
                .contentType(ContentType.JSON).
                assertThat()
                .body("desc", equalTo("edited Test Description"))
                .extract().path("id");
    }
    @Then("User deletes created cards")
    public void User_deletes_created_cards() {
        for (int i = 0; i < 2; i++) {
            given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", ConfigReader.getProperty("key"))
                    .queryParam("token", ConfigReader.getProperty("token"))
                    .delete("/cards/"+cardsIdArr[i]).
                    then()
                    .statusCode(200);
        }
    }
    @Then("User deletes created board")
    public void User_deletes_created_board() {
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigReader.getProperty("key"))
                .queryParam("token", ConfigReader.getProperty("token"))
                .pathParam("id",boardId)
                .delete("/boards/{id}").
                then()
                .statusCode(200);
    }
}
