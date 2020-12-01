/**
 * 
 */
package com.fss.openbanking.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Selvakumar
 *
 */
@Configuration
@ImportResource(locations = {"classpath:openbanking-context.xml"})  
public class DataSourceConfiguration {

}
