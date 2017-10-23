package com.cumulocity.processor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;







import javax.net.ssl.HttpsURLConnection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cumulocity.model.DataModel;

@RestController
public class Navigator {
	
	@RequestMapping(path="/")
	public void init(){
		System.out.println("This is init method");
	}
	@RequestMapping(method=RequestMethod.GET,value={"/s/{csvInput}"})
	public void readCsv(@PathVariable String csvInput) throws IOException{
	
		String [] fragments=csvInput.split(",");
		int count=1;
		Set<DataModel> dataSet = new HashSet<DataModel>();
		StringBuilder temp = new StringBuilder();
		for(int i=1;i<fragments.length;i++){
			temp.append("@");
			temp.append(fragments[i]);
			if(i%DataModel.NO_OF_PARAMS == 0){
				DataModel dm = new DataModel(temp.toString());
				dataSet.add(dm);
				count++;
			}
		}
		StringBuilder jSonBody = new StringBuilder();
		for(DataModel d:dataSet){
			jSonBody.append(populateDataModel(d));
		}
		
		System.out.println(jSonBody.toString());
		System.out.println("Sending to Cumulocity now");
		sendToCumulocity(jSonBody.toString());

	}
	
	
	private void sendToCumulocity(String jsonString) throws IOException{
		String urlString="https://demos.cumulocity.com/measurement/measurements";
		URL url = new URL(urlString);
		
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/vnd.com.nsn.cumulocity.measurement+json;ver=0.9;charset=UTF-8");
		con.setRequestProperty("Content-Type", "application/vnd.com.nsn.cumulocity.measurement+json;ver=0.9;charset=UTF-8");
		con.setRequestProperty("Authorization", "Basic ZWthdGVyaW5hOkthdGUwMTIzNA==");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(jsonString);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + urlString);
		System.out.println("Post parameters : " + jsonString);
		System.out.println("Response Code : " + responseCode);
		System.out.println("Response message: "+con.getResponseMessage());
	}
	
	private String populateDataModel(DataModel d){
		String json = "{"
				+ "\"type\": \"com_cumulocity_telematics_RealTimeLocationData\","
				+ "\"time\" : \""+d.getTime()+"\","
				+ "\"source\": \""+d.getSrcValue()+"\","
				+ "\"com_cumulocity_sensor_GSensor\": {"
				+ "\"zDirection\": { \"unit\": \"G\", \"value\": "+d.getzDirection()+" },"
				+ "\"yDirection\": { \"unit\": \"G\", \"value\": "+d.getyDirection()+" },"
				+ "\"xDirection\": { \"unit\": \"G\", \"value\": "+d.getxDirection()+" }"
				+ "},"
				+ "\"com_cumulocity_sensor_LocationMeasurement\": {"
				+ "\"gpsState\": { \"unit\": \"reliable\", \"value\": "+d.getGpsState()+" },"
				+ "\"gpsSpeed\": { \"unit\": \"KMH\", \"value\": "+d.getGpsSpeed()+" },"
				+ "\"bearing\": { \"unit\": \"degrees\", \"value\": "+d.getBearing()+" },"
				+ "\"longitude\": { \"unit\": \"degrees\", \"value\": "+d.getLongitude()+" },"
				+ "\"latitude\": { \"unit\": \"degrees\", \"value\": "+d.getLatitude()+" },"
				+ "\"engineState\": { \"unit\": \"ON/OFF\", \"value\": "+d.getEngineState()+" }"
				+ "},"
				+ "\"com_nsn_telematics_model_measurement_OBD\": {"
				+ "\"vehicleSpeed\": { \"unit\": \"KMH\", \"value\": "+d.getVehSpeed()+" },"
				+ "\"rpm\": { \"unit\": \"rpm\", \"value\": "+d.getRpm()+" },"
				+ "\"manifoldAbsPressure\": { \"unit\": \"Kpa\", \"value\": "+d.getManifoldAbsPressure()+" },"
				+ "\"intakeAirTemp\": { \"unit\": \"Celsius\", \"value\": "+d.getIntakeAirTemp()+" },"
				+ "\"massAirFlowRate\": { \"unit\": \"100g/sec\", \"value\": "+d.getMassAirFlowRate()+"  }"
				+ "} "
				+ "}\"\n";
		
		return json;
	}
}
