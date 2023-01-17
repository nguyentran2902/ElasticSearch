package com.nguyentran.elasticsearch.respository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.nguyentran.elasticsearch.entity.Person;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String>{

}
