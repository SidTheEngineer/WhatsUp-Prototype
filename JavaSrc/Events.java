
public class Events {
	
	private String Title;
	private String Start;
	private String Location;
	private String Description;
	
	public Events() {
		
	}
	
	public Events(String title, String start, String location, String description) {
		this.Title = title;
		this.Start = start;
		this.Location = location;
		this.Description = description;
	}
	
	public void setTitle(String title) {
		this.Title = title;
	}
	
	public void setStartTime(String start) {
		this.Start = start;
	}
	
	public void setLocation(String location) {
		this.Location = location;
	}
	
	public void setDescription(String description) {
		this.Description = description;
	}
	
	public String getTitle() {
		return this.Title;
	}
	
	public String getStartTime() {
		return this.Start;
	}
	
	public String getLocation() {
		return this.Location;
	}
	
	public String getDescription() {
		return this.Description;
	}
	
	
}
