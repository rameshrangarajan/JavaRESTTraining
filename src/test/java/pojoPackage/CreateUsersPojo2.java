package pojoPackage;

import com.google.gson.annotations.Expose;

public class CreateUsersPojo2 {
	private String name;
	@Expose(serialize = true)
	private String job;
	private String place;
	
	public CreateUsersPojo2() {
	}
	
	public CreateUsersPojo2(String name, String job, String place) {
		this.name = name;
		this.job = job;
		this.place=place;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	

}
