package com.appsdeveloperblog.app.webservices.ui.model.request;

import javax.validation.constraints.NotNull ;
import javax.validation.constraints.Size ;

public class UpdateUserDetailslRequestModel {

	@NotNull(message = "First Name cannot be empty or null")
	@Size(min=2 , message = "Password must be or equal to greater than 8 and less than 16 characters" , max = 10)
	private String firstName;

	@NotNull(message = "Last Name cannot be empty or null")
	@Size(min=2,message = "Password must be or equal to greater than 8 and less than 16 characters" , max = 10)
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
