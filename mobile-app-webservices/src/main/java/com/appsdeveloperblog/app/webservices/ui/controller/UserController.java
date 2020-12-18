package com.appsdeveloperblog.app.webservices.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.webservices.exceptions.UserServiceException;
import com.appsdeveloperblog.app.webservices.ui.model.request.UpdateUserDetailslRequestModel;
import com.appsdeveloperblog.app.webservices.ui.model.request.UserDetailslRequestModel;
import com.appsdeveloperblog.app.webservices.ui.model.response.UserRest;

@RestController

@RequestMapping("/users")
public class UserController 
{
	  Map<String , UserRest> users ;
	// (required keyword) Can be used with (String) only NOT with (int)
	  
	  
	///////////////////////////////////////////// GET MAPPING//////////////////////////////////
	@GetMapping
	public String getUser(@RequestParam(value = "page", defaultValue = "2") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit,
			              @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort)
	{
		return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}

	@SuppressWarnings("unused")
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
	{
		/*METHOD - 1 =>  ADDING Value here 
		  UserRest returnValue = new UserRest();
		returnValue.setFirstName("");                      
		 */
		
		/*METHOD - 2 
		 * String firstName = null ; //for exception---CODE begin
		@SuppressWarnings({ "null", "unused" })
		int  firstNameLength = firstName.length();*/ //CODE End ---for exception 
		
		
		/*
		if(true) //to catch thz exception we have to create UserServiceException in AppExceptionHandler.java
			throw new UserServiceException("A User Service Exception is thrown");
		*/
		
		
		//To GET data directly by user(Post Mapping with.the.help of POSTMAN)
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId) , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	

	/////////////////////////////////////////// POST MAPPING////////////////////////////////////////////////
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<UserRest> createUser(@Validated @RequestBody UserDetailslRequestModel userDetails) 
	{
		UserRest returnValue = new UserRest();
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setEmail(userDetails.getEmail());

		String userId = UUID.randomUUID().toString() ;
		returnValue.setUserId(userId);
		
		if(users == null)
		users = new HashMap<>();
		users.put(userId, returnValue);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	
	/////////////////////////////////////////////PUT MAPPING///////////////////////////////////////////////
	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
/*public String updateUser(@PathVariable String userId , @RequestBody UserDetailslRequestModel userDetails)
	{
		return "update user was called" ;
	}*/
	//HERE we can either use @VALID with same class(i.e. UserDetailslRequestModel) or We can ReMOVE @VALID and make new Class
	public UserRest updateUser(@PathVariable String userId ,@Validated @RequestBody UpdateUserDetailslRequestModel userDetails) 
	{
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId , storedUserDetails);
		
		return storedUserDetails;
    }


	/////////////////////////////////////////	DELETE MAPPING///////////////////////////////////////////////////
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) 
	{
		users.remove(id);
		return ResponseEntity.noContent().build() ;
	}
}
