import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class DeleteUserTest {
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
                expectStatusCode(204).
                build();
    }

    @Test
    public void validateSuccessfulDeletionOfExistingUserAndCheckStatusCode(){
        given().
                spec(requestSpecification).
        when().
                delete("/users/2").
        then().
                assertThat().
                spec(responseSpecification);
    }
}
