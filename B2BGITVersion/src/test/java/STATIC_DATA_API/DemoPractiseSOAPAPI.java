package STATIC_DATA_API;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.codehaus.plexus.util.IOUtil;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;


public class DemoPractiseSOAPAPI {
	
	String url = "http://ergast.com/api/f1/2017/circuits.xml";
	
//	@Test(priority = 1)
	public void DemoSOAP() throws Exception {
		
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
				
		 XmlPath xmlpath = new XmlPath(response.asString());
		 String countryCode = xmlpath.getString("sCountryISOCode");
		 System.out.println("Country Code :- " + countryCode );
	}

	
 //	@Test(priority = 2)
	public void DemoSOAP2() throws Exception {
		

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
					.log().all()
					.body("soap:Envelope/soap:Body/m:CapitalCityResponse/m:CapitalCityResult.text()", equalTo("New Delhi"))
					.extract().response();
				//	System.out.println("Actual Result is :- " + response);
				
//		 XmlPath xmlPath=new XmlPath(response.asString());
//         String StatusCode= xmlPath.getString("<StatusCode>");
//
//         System.out.println("Result is :- " + StatusCode);
		
		 	String response1 = response.xmlPath().getString("soap:Envelope/soap:Body/m:CapitalCityResponse/m:CapitalCityResult.text()");
		 	System.out.println("Responce 1 :- " + response1);	
	}
	
	
//	@Test(priority = 3)
	public void DemoSOAP3() throws Exception {
		
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
				//	System.out.println("Actual Result is :- " + response);
		 
		 
				
		 XmlPath xmlPath=new XmlPath(response.asString());
		 
		 assert xmlPath.get("soap:Envelope/soap:Body/m:CapitalCityResponse/m:CapitalCityResult.text()") != null : "The Tag is display";
		 
         String StatusCode= xmlPath.getString("<StatusCode>");

         System.out.println("Result is :- " + StatusCode);
		

	}
	
	@Test(priority = 4)
	public void DemoSOAP4() throws Exception {
		
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
					.assertThat()
					.body("soap:Envelope/soap:Body/m:CapitalCityResponse/m:CapitalCityResult.text()", Matchers.notNullValue())
					.extract().response();
		
		 XmlPath xmlPath=new XmlPath(response.asString());
         String StatusCode= xmlPath.getString("<StatusCode>");

         System.out.println("Result is :- " + StatusCode);
		
	}
}
