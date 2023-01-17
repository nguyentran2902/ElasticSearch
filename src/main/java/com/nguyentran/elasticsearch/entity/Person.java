package com.nguyentran.elasticsearch.entity;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.nguyentran.elasticsearch.entity.personObject.Email;
import com.nguyentran.elasticsearch.entity.personObject.Info;
import com.nguyentran.elasticsearch.entity.personObject.Language;
import com.nguyentran.elasticsearch.entity.personObject.Phone;

import lombok.Data;


@Data
@Document(indexName = "person")
public class Person {

	@Id
	private String _id;
	
	private   String firstName;
	private  String lastName;
	private  Integer sex;
	private  String dayOfBirth;
	
	private List<Phone> phones;
	private List<Email> emails;
	private List<Info> infos;
	private List<Language> languages;
}
