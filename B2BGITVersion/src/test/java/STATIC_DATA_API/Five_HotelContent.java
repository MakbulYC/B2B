package STATIC_DATA_API;

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

public class Five_HotelContent {
	
	public static WebDriver driver;
	
	@Test(priority = 1)
	public void HotelContent() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/StaticDataAPI/HotelContent.xml");

		RestAssured.baseURI=""; // Add here base url
		
		 Response response =
				given()
					.header("Content-Type", "text/xml")
					.and()
					.body(IOUtil.toString(fileinputstream, "UTF-8"))
				.when()
					.post("") 
				.then()
					.statusCode(200)
					.and()
					.log().all().extract().response();
				
		 
		 String URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/@Url.text()");
		 
		 String TimeStemp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/@TimeStamp.text()");
		 
		 String IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/@IntCode.text()");
		 
		 String JPCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/HotelContent/@JPCode.text()");
		 
		 String Type = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/HotelContent/HotelCategory/@Type.text()");
		 
		 String ZoneCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/HotelContent/Zone/@Code.text()");
		 
		 String JPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/HotelContent/Zone/@JPDCode.text()");
		 
		 String FeatureType = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/HotelContent/Features/Feature/@Type[1].text()");
		 
		 String HotelRoom = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/HotelContent/HotelRooms/HotelRoom/@Code.text()");
		 
		 String JRCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelContentResponse/ContentRS/HotelContent/JPRooms/JPRoom/@JRCode.text()");
	
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
