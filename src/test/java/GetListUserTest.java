import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetListUserTest {
    private static RequestSpecification requestSpecification ;
    private static ResponseSpecification responseSpecification ;
    @BeforeClass
    public static void createRequestSpecification(){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                build();
    }
    @BeforeClass
    public static void createResponseSpecification(){
        responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(200).
                build();
    }

    @Test
    public void validateGetListUserStatusCodeAndNumberOfDataEntries(){
        given().
                spec(requestSpecification).
        when().
                get("/users?page=2").
        then().
                assertThat().
                spec(responseSpecification).
        and().
                body("data" , hasSize(6)) ;
    }
}
