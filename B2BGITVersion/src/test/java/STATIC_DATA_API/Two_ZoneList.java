package STATIC_DATA_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.codehaus.plexus.util.IOUtil;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Two_ZoneList {

	public static WebDriver driver;
	
	@Test(priority = 1)	
	public void ZoneList() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/StaticDataAPI/ZoneList.xml");

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
				
		 
		 Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/@Url.text()"); 
		 
		 Object TimeStamp = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/@TimeStamp.text()");
		 
		 Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/@IntCode.text()");
		 
		 Object JPDCodeUrl = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/Zone/@JPDCodeUrl[1].text()");
		 
		 Object ParentJPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/Zone/@ParentJPDCode[1].text()");
		 
		 Object JSearchable = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/Zone/@JSearchable[1].text()");
		 
		 Object ZoneListCode = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/Zone/@Code[1].text()");
		 
		 Object ZoneName = response.xmlPath().getString("soap:Envelope/soap:Body/ZoneListResponse/ZoneListRS/Zone/Name[1].text()");
		 
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
