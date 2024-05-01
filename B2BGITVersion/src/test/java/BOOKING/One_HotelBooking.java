package BOOKING;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;

import org.codehaus.plexus.util.IOUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class One_HotelBooking {
	
	public static WebDriver driver;

	@Test(priority = 1)
	public void HotelBooking() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/BookingAPIFiles/HotelBooking.xml");

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
				
		 	Object URL = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/@Url.text()");
		 	
		 	Object TimeStamp = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/@TimeStamp.text()");
		 
		 	Object IntCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/@IntCode.text()");
		 	
		 	Object Locator = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Reservation/@Locator.text()");
		 	
		 	Object Status = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Reservation/@Status.text()");
		 	
		 	Object ItemId = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Reservation/Items/HotelItem/@ItemId.text()");
		 	
		 	Object HotelItemStatus = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/@Status.text()");
		 	
		 	Object Comment = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/Comments/Comment[1].text()");
		 	
		 	Object CommentType = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/Comments/Comment[1]/@Type.text()");
		 	
		 	Object Code = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/HotelInfo/@Code.text()");
		 	
		 	Object DestinationZone = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/HotelInfo/@DestinationZone.text()");
		 	
		 	Object JPDCode = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/HotelInfo/@JPDCode.text()");
		 	
		 	Object Name = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/HotelInfo/Name.text()");	
		 	
		 	Object Board = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/Board.text()");
		 	
		 	Object IdPax = response.xmlPath().getString("soap:Envelope/soap:Body/HotelBookingResponse/BookingRS/Reservations/Items/HotelItem/HotelRooms/HotelRoom[1]/RelPaxes/RelPax/@IdPax.text()");
		 	
		 	
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
