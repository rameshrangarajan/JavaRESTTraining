package pojoPackage;

import com.google.gson.annotations.Expose;

public class CreateUsersPojo {
	private String name;
	@Expose(serialize = true)
	private String job;
	
	public CreateUsersPojo() {
	}
	
	public CreateUsersPojo(String name, String job) {
		this.name = name;
		this.job = job;
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
