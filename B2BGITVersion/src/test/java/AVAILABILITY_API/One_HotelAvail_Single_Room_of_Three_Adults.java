package AVAILABILITY_API;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

import org.codehaus.plexus.util.IOUtil;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class One_HotelAvail_Single_Room_of_Three_Adults {

	@Test(priority = 1)
	public void HotelAvail() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/AVAILABILITYAPI/HotelAvail_Single_Room_of_Three_Adults.xml");

		RestAssured.baseURI=""; // Add here base url
		
		 Response response =
				given()
					.header("Content-Type", "text/xml")
					.and()
					.body(IOUtil.toString(fileinputstream, "UTF-8"))
				.when()
					.post("") // End point URL
				.then()
					.statusCode(200)
					.and()
					.log().all().extract().response();
				
		 XmlPath xmlpath = new XmlPath(response.asString());
		 String rate = xmlpath.getString(""); // add here field name in soap api whcih you want to print
		
	}
}
