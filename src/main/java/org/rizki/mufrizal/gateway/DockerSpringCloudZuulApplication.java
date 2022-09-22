package org.rizki.mufrizal.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class DockerSpringCloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerSpringCloudZuulApplication.class, args);
	}

}
