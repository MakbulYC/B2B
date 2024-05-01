package BOOKING;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

import org.codehaus.plexus.util.IOUtil;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Four_HotelModifyExample2 {
	
	
	@Test(priority = 1)
	public void HotelModifyWithNameAndSurnameOFPassengers() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/BookingAPIFiles/HotelModifyWithNameAndSurnameOFPassengers.xml");

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
