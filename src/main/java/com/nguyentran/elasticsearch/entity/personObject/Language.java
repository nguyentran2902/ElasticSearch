package com.nguyentran.elasticsearch.entity.personObject;

import java.io.Serializable;


import lombok.Data;

@Data

public class Language implements Serializable{
	private  Integer type;
	private  String language;
	
	
}

