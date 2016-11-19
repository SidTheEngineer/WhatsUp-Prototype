import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Crawler {
	
	public static String EventsURL = "http://events.ucf.edu";
	private static ArrayList<Events> EventsList;
	
	public static void main(String[] argv) throws IOException {
		
		EventsList = new ArrayList<Events>();
		
		URL url = new URL(EventsURL);
		URLConnection con = url.openConnection();
		BufferedReader inStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String line;
		
		readFull(inStream);
		
		sendEvents(constructJSON());
	}
	
	public static void readFull(BufferedReader inputStream) throws IOException {
		String full = "";
		String line = "";
		while((line = inputStream.readLine()) != null) {
			full += line;
		}
		readTodaysEvents(full);
	}
	
	@SuppressWarnings("unchecked")
	public static String constructJSON() {
		String json = "";
		JSONArray arr = new JSONArray();
		
		for(Events e : EventsList) {
			JSONObject jobj = new JSONObject();
			jobj.put("Title", e.getTitle());
			jobj.put("Time", e.getStartTime());
			jobj.put("Location", e.getLocation());
			jobj.put("Description", e.getDescription());
			arr.add(jobj);
		}
		
		return arr.toJSONString();
		
	}
	
	public static void sendEvents(String jsonString) throws MalformedURLException, IOException {
		
		URL url = new URL("http://localhost:3000/api/events");
		URLConnection conn = url.openConnection();
		
		
		//HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:3000/api/events").openConnection();
		conn.setDoOutput(true);
		conn.setRequestProperty("charset", "utf-8");
		
		OutputStream wr = conn.getOutputStream();
		wr.write(jsonString.getBytes());
		
	}
	
	public static void readTodaysEvents(String full) {
		int index0 = full.indexOf("<ul class=\"event-list");
		full = full.substring(index0, full.lastIndexOf("</ul>"));
		int index1 = full.indexOf("</ul>");
		
		while(full.contains("<li class=\"event") && full.contains("</li>")) {
			int index = full.indexOf("<li ");
			String eventString = full.substring(index);
			full = full.substring(index);
			eventString = full.substring(0, eventString.indexOf("</li>"));
			full = full.substring(full.indexOf("</li>"));
			readSingleEvent(eventString);
			
		}
		
		for(Events e : EventsList) {
			System.out.println(e.getTitle() + " - " + e.getLocation() + " - " + 
					e.getStartTime() + " - " + e.getDescription() + "\n");
		}
		
	}
	
	public static void readSingleEvent(String eventString) {
		Events event = new Events();
		
		// Gets Title
		if(eventString.contains("<a class=\"summary\"")) {
			int loc = eventString.indexOf("<a class=\"summary\"");
			String temp = eventString.substring(loc);
			loc = temp.indexOf(">");
			temp = temp.substring(loc + 1);
			loc = temp.indexOf("</a>");
			temp = temp.substring(0, loc);
			event.setTitle(temp);
			//System.out.print(temp + " - ");
		}
		
		// Get's Location
		if(eventString.contains("<span class=\"location")) {
			int loc = eventString.indexOf("<span class=\"location");
			String temp = eventString.substring(loc);
			loc = temp.indexOf(">");
			temp = temp.substring(loc + 1);
			loc = temp.indexOf("</span>");
			temp = temp.substring(0, loc);
			event.setLocation(temp);
			//System.out.print(temp + " - ");				
		}
		
		// Get's Start Time
		if(eventString.contains("<span class=\"start-time\"")) {
			int loc = eventString.indexOf("<span class=\"start-time\"");
			String temp = eventString.substring(loc);
			loc = temp.indexOf(">");
			temp = temp.substring(loc + 1);
			loc = temp.indexOf("</span>");
			temp = temp.substring(0, loc);
			event.setStartTime(temp);
			//System.out.print(temp + " - ");				
		}
		
		// Get's Description
		if(eventString.contains("<p class=\"description")) {
			int loc = eventString.indexOf("<p class=\"description");
			String temp = eventString.substring(loc);
			loc = temp.indexOf(">");
			temp = temp.substring(loc + 1);
			loc = temp.indexOf("</p>");
			temp = temp.substring(0, loc);
			event.setDescription(temp);
			//System.out.print(temp);				
		}
		EventsList.add(event);
	}
}
