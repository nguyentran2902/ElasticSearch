package com.nguyentran.elasticsearch.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


@Configuration
public class ElasticSearchConfig extends 
         AbstractElasticsearchConfiguration {


	@Value("${elasticsearch.host}")
	private String esHost;
	@Value("${elasticsearch.port}")
	private int esPort;
	@Value("${elasticsearch.clustername}")
	private String esClusterName;
	
	@Bean
	@Override
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration clientConfiguration = 
			    ClientConfiguration
			      .builder()
			      .connectedTo("localhost:9200")
			      .build();

			  return RestClients.create(clientConfiguration).rest();
	}
	

}
