package com.nguyentran.elasticsearch.respository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.nguyentran.elasticsearch.entity.Laptop;

@Repository
public interface LaptopReponsitory extends ElasticsearchRepository<Laptop, String>{

}
