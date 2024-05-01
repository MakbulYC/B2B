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

public class Three_HotelFutureRates {

	public static WebDriver driver;
	
	@Test(priority = 1)
	public void HotelFutureRates() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/AVAILABILITYAPI/HotelFutureRates.xml");

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
				
		 Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/@Url.text()");
		 
		 Object TimeStemp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/@TimeStamp.text()");
		 
		 Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/@IntCode.text()");
		 
		 Object Start = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/ResultInfo/@Start.text()");
		 
		 Object End = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/ResultInfo/@End.text()");
		 
		 Object DestinationZone = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/ResultInfo/@DestinationZone.text()");
		 
		 Object Hotelcode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/ResultInfo/@HotelCode.text()");
		 
		 Object HotelResultCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/HotelResult/@Code.text()");
		 
		 Object HotelResultJPCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/HotelResult/@JPCode.text()");
		 
		 Object HotelResultDestinationZone = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/HotelResult/@DestinationZone.text()");
		 
		 Object JPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/HotelResult/@JPDCode.text()");
		 
		 Object RatePlanCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/HotelResult/HotelOptions/HotelOption/@RatePlanCode.text()");	
		 
		 Object HotelCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelFutureRatesResponse/FutureRatesRS/Results/HotelFutureRatesResults/HotelFutureRatesResult/ResultInfo/@HotelCode.text()");
		 
		 
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
