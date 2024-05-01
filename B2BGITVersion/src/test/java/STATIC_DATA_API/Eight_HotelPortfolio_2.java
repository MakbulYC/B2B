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

public class Eight_HotelPortfolio_2 {
	
	public static WebDriver driver;

	@Test(priority = 1)
	public void HotelPortfolio() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/StaticDataAPI/HotelPortfolio.xml");

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
				
		 Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/@Url.text()");
		 
		 Object TimeStemp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/@TimeStamp.text()");
		 
		 Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/@IntCode.text()");
		 
		 Object JPCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/@JPCode.text()");
		 
		 Object HasSynonyms = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/@HasSynonyms.text()");
		 
		 Object HotelName = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/Name.text()");
		 
		 Object JPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[2]/Zone/@JPDCode.text()");
		 
		 Object Code = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[2]/Zone/@Code.text()");
		 
		 Object ZoneName = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[2]/Zone/Name.text()");
		 
		 Object Address = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/Address.text()");
		 
		 Object Latitude = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/Latitude.text()");
		 
		 Object Longitude = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/Longitude.text()");
		 
		 Object HotelCategory = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/HotelCategory.text()");
		 
		 Object HotelCategoryCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[1]/HotelCategory/@Code.text()");
		 
		 Object CityJPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelPortfolioResponse/HotelPortfolioRS/HotelPortfolio/Hotel[2]/City/@JPDCode.text()");
		
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
