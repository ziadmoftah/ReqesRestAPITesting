import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetSingleUserTest {
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


    @DataProvider ( name = "get single user data")
    public Object[][] getSingleUserData(){
        return new Object[][]{
                {1 , "george.bluth@reqres.in" , "George" , "Bluth" , "https://reqres.in/img/faces/1-image.jpg"},
                {2 , "janet.weaver@reqres.in" , "Janet" , "Weaver" , "https://reqres.in/img/faces/2-image.jpg"},
                {3, "emma.wong@reqres.in" , "Emma" , "Wong" , "https://reqres.in/img/faces/3-image.jpg"}
        };
    }

    @Test ( dataProvider = "get single user data")
    public void validateGetSingleUserStatusCodeAndDataReturned(int id , String email, String firstName , String lastName , String avatar){
        given().
                spec(requestSpecification).
                pathParam("id" , id).
        when().
                get("/users/{id}").
        then().
                assertThat().
                spec(responseSpecification).
        and().
                assertThat().
                body("data.email", equalTo(email),
                        "data.first_name", equalTo(firstName),
                        "data.last_name" , equalTo(lastName),
                        "data.avatar" , equalTo(avatar));

    }

}
