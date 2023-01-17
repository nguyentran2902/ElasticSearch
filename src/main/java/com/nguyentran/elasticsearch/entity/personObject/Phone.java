package com.nguyentran.elasticsearch.entity.personObject;

import java.io.Serializable;



import lombok.Data;

@Data
public class Phone implements Serializable{
	private  String areaCode;
	private  String number;
	private  String netWork;
	private  Integer status;
}