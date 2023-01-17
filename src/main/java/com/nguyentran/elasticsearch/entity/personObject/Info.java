package com.nguyentran.elasticsearch.entity.personObject;

import java.io.Serializable;


import lombok.Data;

@Data

public class Info implements Serializable{

	private  Integer type;
	private  String numberId;
	private  Integer status;
	
	
}
