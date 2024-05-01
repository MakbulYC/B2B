package STATIC_DATA_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.codehaus.plexus.util.IOUtil;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class One_HotelCatalogueData {
	
	public static WebDriver driver;
	
	@Test(priority = 1)
	public void HotelCatalogueData() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/StaticDataAPI/HotelCatalogueData.xml");

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
		 
		 Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/@Url.text()");
		 
		 Object TimeStamp = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/@TimeStamp.text()");
		 
		 Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/@IntCode.text()");
		 
		 Object RoomCategory = response.xmlPath().getString("soap:Envelope/soap:Body/HotelStaticData/RoomCategoryList/RoomCategory.text()");
		 
		 Object HotelCategory = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/HotelCategoryList/HotelCategory[1].text()");
		 
		 Object HotelCategoryType = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/@Type[1].text()");
		 
		 Object HotelTypeDescription = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/HotelTypeList/HotelType.text()");
		 
		 Object HotelType = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/HotelTypeList/HotelType/@Type.text()");
		 
		 Object RoomCategory2 = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/RoomCategoryList/RoomCategory[1].text()");; 
		 
		 Object RoomCategoryType = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/RoomCategoryList/RoomCategory/@Type[1].text()");

		 Object Board = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/BoardList/Board[1].text()");
		 
		 Object BoardType = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/BoardList/Board/@Type[1].text()");

		 Object OfferSupplementType = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/OfferSupplementTypeList/OfferSupplementType[1].text()");
		 
		 Object OfferSupplementTypeCode = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/OfferSupplementTypeList/OfferSupplementType/@Code[1].text()");

		 Object OfferSupplementTypeCode2 = response.xmlPath().getString("soap:Envelope/soap:Body/CatalogueDataRS/HotelStaticData/SpecialSupplementTypeList/SpecialSupplementType/@Code.text()");
		 
		 
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
