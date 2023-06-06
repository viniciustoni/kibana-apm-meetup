package com.vagai.salesproducts;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesProductsApplication {

	public static void main(String[] args) {
		ElasticApmAttacher.attach();
		SpringApplication.run(SalesProductsApplication.class, args);
	}

}
