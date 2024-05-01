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

public class Three_HotelList {
	
	public static WebDriver driver;

	@Test(priority = 1)
	public void HotelList() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/StaticDataAPI/HotelList.xml");

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
				
		 
		 Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelListResponse/HotelListRS/@Url.text()");	 
		 
		 Object TimeStemp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelListResponse/HotelListRS/@TimeStamp.text()");
		 
		 Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelListResponse/HotelListRS/@IntCode.text()");
		
		 Object HotelList = response.xmlPath().getString("soap:Envelope/soap:Body/HotelListResponse/HotelListRS/HotelList/Hotel/Name[1].text()");
		 
		 Object HotelCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelListResponse/HotelListRS/HotelList/Hotel/@Code.text()");
		 
		 Object HasSynonyms = response.xmlPath().getString("soap:Envelope/soap:Body/HotelListResponse/HotelListRS/HotelList/Hotel/@HasSynonyms.text()");
		 
		 Object HotelName = response.xmlPath().getString("soap:Envelope/soap:Body/HotelListResponse/HotelListRS/HotelList/Hotel/Name[2].text()");
	
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
