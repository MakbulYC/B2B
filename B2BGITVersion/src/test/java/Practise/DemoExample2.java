package Practise;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;

import org.codehaus.plexus.util.IOUtil;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DemoExample2 {
	
	public static WebDriver driver;
	
	@Test(priority = 1)
	public void Example2() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/StaticDataAPI/DemoSOAPPractise.xml");

		RestAssured.baseURI="http://webservices.oorsprong.org/websamples.countryinfo"; // Add here base url
		
		 Response response =
				given()
					.header("Content-Type", "text/xml")
					.and()
					.body(IOUtil.toString(fileinputstream, "UTF-8"))
				.when()
					.post("CountryInfoService.wso?WSDL") 
				.then()
					.statusCode(200)
					.and()
					.log().all().extract().response();
	 
		 	Object response1 = response.xmlPath().getString("soap:Envelope/soap:Body/m:FullCountryInfoResponse/m:FullCountryInfoResult/m:sISOCode.text()");
		 	
//		 	if(response1 == null) {
//		 		
//		 		System.out.println("Responce getting Null");
//		 		
//		 	}else {
//		 		
//		 		System.out.println("Responce 1 :- " + response1);
//		 	}
		 	
		 	int statusCode = response.getStatusCode();
		 	
		 	if(statusCode == 200) {
		 		
		 		System.out.println("Status Code is :- " + statusCode);
		 		
		 		if(response1 != null) {
		 			
		 			System.out.println("The Response is :- " + response1);
		 		}
		 		
		 		if(response1 instanceof String ) {
		 			
		 			System.out.println("Data Type Of Responce Is String ");
		 			
		 			
		 		}else {
		 			 
		 			System.out.println("Data type is not matched with Resopnse"); 
		 			driver.quit();
		 		}
		 		
		 	} else {
		 		
		 		System.out.println("Response not getting");
		 		driver.quit();
		 	}
		 	
		 	
		 	
		 	
//		 	if(response1 == null) {
//		 		
//		 		if(response1 instanceof String) {
//		 			
//		 			System.out.println("The Resopnce is" + response1); 
//		 		}
//		 	}else {
//		 		
//		 		System.out.println("There is wrong responce display");
//		 	}
		
	}

}
