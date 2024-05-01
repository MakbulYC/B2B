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

public class Two_HotelAvailCalendar {
	
	public static WebDriver driver;

	@Test(priority = 1)
	public void HotelAvailCalendar() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/AVAILABILITYAPI/HotelAvailCalendar.xml");

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
				
		 Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/@Url.text()");
		 
		 Object TimeStemp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/@TimeStamp.text()");
		 
		 Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailResponse/AvailabilityRS/@IntCode.text()");
		 
		 Object Start = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailCalendarResponse/AvailabilityRS/Results/HotelCalendarResult/@Start.text()");
		 
		 Object End = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailCalendarResponse/AvailabilityRS/Results/HotelCalendarResult/@End.text()");
		 
		 Object Code = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailCalendarResponse/AvailabilityRS/Results/HotelCalendarResult/HotelResults/HotelResult/@Code.text()");
		 
		 Object JPCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailCalendarResponse/AvailabilityRS/Results/HotelCalendarResult/HotelResults/HotelResult/@JPCode.text()");
		 
		 Object DestinationZone = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailCalendarResponse/AvailabilityRS/Results/HotelCalendarResult/HotelResults/HotelResult/@DestinationZone.text()");
		 
		 Object HotelResultJPCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailCalendarResponse/AvailabilityRS/Results/HotelCalendarResult/HotelResults/HotelResult/@JPDCode.text()");
		 
		 Object RatePlanCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelAvailCalendarResponse/AvailabilityRS/Results/HotelCalendarResult/HotelResults/HotelResult/HotelOptions/HotelOption/@RatePlanCode.text()");

		 
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
