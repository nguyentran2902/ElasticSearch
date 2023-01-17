package com.nguyentran.elasticsearch.entity.personObject;

import java.io.Serializable;


import lombok.Data;

@Data

public class Email implements Serializable{
 
	private  String email;
	private  Integer type;
	
	
}
