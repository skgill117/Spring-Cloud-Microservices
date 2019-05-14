package com.boomshankar.authserver.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.boomshankar.authserver.util.PropertiesUtil;

@Configuration
public class CustomBeans {

	@Bean
	public PropertiesUtil propertiesUtilBean() {
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		propertiesUtil.setIgnoreUnresolvablePlaceholders(true);
		propertiesUtil.setLocations(new ClassPathResource("application.properties"));
		return propertiesUtil;
	}

}
