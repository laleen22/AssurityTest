package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * @author LaleenP
 */

public class StepsDeff {
    private final String BASE_URL = "https://api.tmsandbox.co.nz/v1/";
    private Response response;

    //Constructing the URl with parameters
    @Given("the user access {string}")
    public void verfiyURL(String url) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification req = RestAssured.given();
        try {
            response = req.when().get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //Verifying response code
    @Then("verify the response code is equal to {string}")
    public void verfiyResponseCode(String statuscode) {
        Assert.assertEquals(statuscode, response.then().extract().statusCode() + "");
    }

    //Verifying content-type
    @And("verify response header content-type as {string}")
    public void verifyContenttype(String header) {
        Assert.assertEquals(header, response.then().extract().header("Content-Type") + "");
    }

    //Verifying name value
    @And("verify the name is equal to {string}")
    public void verifyName(String name) {
        Assert.assertEquals(name, response.then().extract().path("Name"));
    }

    //Verifying CanRelist
    @And("verify the CanRelist is equal to {string}")
    public void verifyCanRelist(String str) {
        Assert.assertEquals(Boolean.valueOf(str), response.then().extract().path("CanRelist"));
    }

    //Verifying Promotion name & Description
    @And("verify the promotion has name {string} and contain {string} in the description")
    public void verifyProNameDescription(String promoname, String des) {
        List<Map<String, Object>> promolist = response.jsonPath().getList("Promotions");
        for (Map<String, Object> promo : promolist) {
            if (promo.get("Name").equals(promoname)) {
                Assert.assertEquals(promo.get("Description"), des);
            }
        }
    }
}
