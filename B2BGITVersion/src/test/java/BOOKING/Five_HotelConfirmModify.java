package BOOKING;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

import org.codehaus.plexus.util.IOUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Five_HotelConfirmModify {
	
	public static WebDriver driver;

	@Test(priority = 1)
	public void HotelConfirmModify() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/BookingAPIFiles/HotelConfirmModify.xml");

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
				
		 	Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/@Url.text()");
		 	
		 	Object TimeStamp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/@TimeStamp.text()");
		 
		 	Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/@IntCode.text()");
		 	
		 	Object Locator = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/@Locator.text()");
		 	
		 	Object Status = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/@Status.text()");
		 	
		 	Object ItemId = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/Items/HotelItem/@ItemId.text()");
		 	
		 	Object HotelItemStatus = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Items/HotelItem/@Status.text()");
		 	
		 	Object Comment = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/Comments/Comment.text()");
		 	
		 	Object CommentType = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/Comments/Comment/@Type.text()");
		 	
		 	Object HotelInfoCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/HotelInfo/@Code.text()");
		 	
		 	Object HotelInfoDestinationZone = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/HotelInfo/@DestinationZone.text()");
		 	
		 	Object HotelInfoJPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/HotelInfo/@JPDCode.text()");
		 	
		 	Object Name = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/HotelInfo/Name.text()");	
		 	
		 	Object Board = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/Board.text()");
		 	
		 	Object IdPax = response.xmlPath().getString("soap:Envelope/soap:Body/HotelConfirmModifyResponse/ConfirmModifyRS/Reservations/Reservation/HotelRooms/HotelRoom/RelPaxes/RelPax[1]/@IdPax.text()");
		 	
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
