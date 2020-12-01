/**
 * 
 */
package com.fss.openbanking.runner;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fss.openbanking.dao.DaoApiRepository;

/**
 * @author Selvakumar
 *
 */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@ComponentScan("com.fss")
public class FSSOpenBankingRunner {

	@Autowired
	private DaoApiRepository daoApiRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(FSSOpenBankingRunner.class);

	public static String transctionId = null;


    @PostConstruct
    private void init() {
    	try
    	{
	        transctionId = daoApiRepository.fetchTransactionId();
	        
	        if(transctionId == null)
	        	transctionId = "mid00000";
    	}
    	catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
    }
    
	public static void main(String[] args) {
		 System.setProperty("spring.profiles.active", "dev");
		 System.setProperty("spring.main.allow-bean-definition-overriding", "true");
		 System.setProperty("spring.devtools.restart.enabled", "false");
		 SpringApplication.run(FSSOpenBankingRunner.class, args);
	}
}
