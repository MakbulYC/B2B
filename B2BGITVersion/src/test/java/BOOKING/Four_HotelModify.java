package BOOKING;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

import org.codehaus.plexus.util.IOUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Four_HotelModify {
	
	public static WebDriver driver;
	

	@Test(priority = 1)
	public void HotelModifyWithaddGuest() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("C:../B2B/BookingAPIFiles/HotelModifyWithaddGuest.xml");

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
		 
		 Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/@Url.text()");
		 
		 Object TimeStamp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/@TimeStamp.text()");
		 
		 Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/@IntCode.text()");
				
		 Object HotelResultCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/HotelModifyResult/HotelResult/@Code.text()");
		 
		 Object HotelResultDestinationZone = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/HotelModifyResult/HotelResult/@DestinationZone.text()");
		
		 Object HotelResultJPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/HotelModifyResult/HotelResult/@JPDCode.text()");
		 
		 Object HotelResultStart = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/HotelModifyResult/HotelResult/@Start.text()");
		 
		 Object HotelResultEnd = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/HotelModifyResult/HotelResult/@End.text()");

		 Object ModifyCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelModifyResponse/ModifyRS/HotelModifyResult/HotelResult/HotelOptions/HotelOption/ModifyCode.text()"); 
		
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
