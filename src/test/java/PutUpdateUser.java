import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutUpdateUser {
    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                setContentType(ContentType.JSON).
                build();
    }

    @BeforeClass
    public static void createResponseSpecification() {
        responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(200).
                build();
    }
    @DataProvider(name = "User data")
    public Object[][] createUserData(){
        return new Object[][]{
                {1,"ahmed" , "builder"},
                {2,"mazen" , "developer"},
                {3,"ali", "carpenter"}
        } ;
    }
    @Test( dataProvider = "User data")
    public void validateSuccessfulUpdateOfExistingUser(int id, String name, String job){
        JSONObject request = new JSONObject();
        request.put("name" , name);
        request.put("job" , job);
        given().
                spec(requestSpecification).
                pathParam("id" , id).
        when().
                body(request.toJSONString()).
                put("/users/{id}").
        then().
                assertThat().
                spec(responseSpecification).
        and().
                assertThat().
                body("name" , equalTo(name) , "job" , equalTo(job));
    }
}
