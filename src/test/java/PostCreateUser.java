import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PostCreateUser {
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
                expectStatusCode(201).
                build();
    }
    @DataProvider(name = "User data")
    public Object[][] createUserData(){
        return new Object[][]{
                {"ahmed" , "builder"},
                {"mazen" , "developer"},
                {"ali", "carpenter"}
        } ;
    }
    @Test ( dataProvider = "User data")
    public void validateSuccessfulCreateOfNewUser(String name, String job){
        JSONObject request = new JSONObject();
        request.put("name" , name);
        request.put("job" , job);
        given().
                spec(requestSpecification).
        when().
                body(request.toJSONString()).
                post("/users").
        then().
                assertThat().
                spec(responseSpecification).
        and().
                assertThat().
                body("name" , equalTo(name) , "job" , equalTo(job));
    }
}
