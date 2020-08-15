	package net.guides.springboot.crud.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Passlimit")
public class Passlimit {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	@NotBlank
    @Size(max=100)
	private String stateName;
	private String cityName;
	private String limitDate;
	private int dailyLimit;

	
	public Passlimit() {
		
	}
	
	public Passlimit(String stateName, String cityName, int dailyLimit, String limitDate) {
		this.stateName = stateName;
		this.cityName = cityName;
		this.dailyLimit = dailyLimit;
		this.limitDate = limitDate;
		System.out.println(this.limitDate);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public int getDailyLimit() {
		return dailyLimit;
	}
	public void setDailyLimit(int dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	@Override
	public String toString() {
		return "DailyLimit [id=" + id + ", stateName=" + stateName + ", cityName=" + cityName + ", limitDate=" + limitDate
				+ "]";
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}	
}
