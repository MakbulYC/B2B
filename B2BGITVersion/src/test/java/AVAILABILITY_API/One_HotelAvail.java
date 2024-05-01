package AVAILABILITY_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;

import org.codehaus.plexus.util.IOUtil;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class One_HotelAvail {

	public static WebDriver driver;
	
	@Test(priority = 1)
	public void HotelAvail() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/AVAILABILITYAPI/HotelAvail.xml");

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
				
		 String URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/@Url.text()");
		 
		 String TimeStamp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/@TimeStamp.text()");
		 
		 String IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/@IntCode.text()");
		 
		 String HotelResultCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/Results/HotelResult/@Code.text()");
		 
		 String JPCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/Results/HotelResult/@JPCode.text()");
		 
		 String DestinationZone = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/Results/HotelResult/@DestinationZone.text()");
		 
		 String JPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/Results/HotelResult/@JPDCode.text()");
		 
		 String BestDeal = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/Results/HotelResult/@BestDeal.text()");
		 
		 // URL Validation Checking
		 
		 int statusCode = response.getStatusCode();
		 
		 if(statusCode == 200) {
		 		
		 		System.out.println("Status Code is :- " + statusCode);
		 		
		 		if(URL != null) {
		 			
		 			System.out.println("The Response is :- " + URL);
		 		}
		 		
		 		if(URL instanceof String) {
		 			
		 			System.out.println("Data Type Of Responce Is String ");
		 			
		 			
		 		}else {
		 			 
		 			System.out.println("Data type is not matched with Resopnse"); 
		 			driver.quit();
		 		}
		 		
		 	} else {
		 		
		 		System.out.println("Response not getting");
		 		driver.quit();
		 	} 
	}
}
