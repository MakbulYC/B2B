package VALUATION_API;

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


// Not Find Nodes in responce
/* 
 * /VirtualCreditCardInfo/@Currency	
 * VirtualCreditCardInfo/@ValidFrom
 * VirtualCreditCardInfo/@IncreaseDays
 * VirtualCreditCardInfo/@Email
 * /Supplier/@Code
 * /Preference/@Code
 * /Preference/Description
 * /AllowedCreditCards/CreditCard
 * /CreditCard/@Code
 * */

public class Two_HotelBookingRules {
	
	public static WebDriver driver;
	
	@Test(priority = 1)
	public void HotelBookingRules() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/Valuation/HotelBookingRules.xml");

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
				
		 String URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelCheckAvailResponse/CheckAvailRS/@Url.text()");
		 
		 String TimeStemp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelCheckAvailResponse/CheckAvailRS/@TimeStamp.text()");
		 
		 String IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelCheckAvailResponse/CheckAvailRS/@IntCode.text()");
		 
		 String BookingCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingRulesResponse/BookingRulesRS/Results/HotelResult/HotelOptions/HotelOption/BookingCode[1].text()");
		 
		 String ExpirationDate = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingRulesResponse/BookingRulesRS/Results/HotelResult/HotelOptions/HotelOption/BookingCode[1]/@ExpirationDate.text()");
		 
		 String CommentType = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingRulesResponse/BookingRulesRS/Results/HotelResult/HotelOptions/HotelOption/OptionalElements/Comments/Comment/@Type.text()");

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
