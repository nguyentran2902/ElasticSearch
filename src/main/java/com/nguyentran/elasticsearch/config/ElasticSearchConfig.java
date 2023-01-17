package com.nguyentran.elasticsearch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.nodes.Client;

@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfig {

	 @Value("${elasticsearch.host}")
	    private String esHost;
	    @Value("${elasticsearch.port}")
	    private int esPort;
	    @Value("${elasticsearch.clustername}")
	    private String esClusterName;

		
	
}
