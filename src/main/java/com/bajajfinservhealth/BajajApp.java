package com.bajajfinservhealth;

import com.bajajfinservhealth.model.GenerateWebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class BajajApp implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(BajajApp.class);
    

    private static final String NAME = "John Doe";
    private static final String REG_NO = "REG12347";
    private static final String EMAIL = "john@example.com";
    
    @Autowired
    private WebhookService webhookService;
    
    public static void main(String[] args) {
        logger.info("Starting Bajaj Finserv Health Application...");
        SpringApplication.run(BajajApp.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("=== BAJAJ FINSERV HEALTH APPLICATION STARTUP ===");
        logger.info("Application started successfully!");
        
        try {

            logger.info("Step 1: Generating webhook...");
            GenerateWebhookResponse webhookResponse = webhookService.generateWebhook(NAME, REG_NO, EMAIL);
            
            if (webhookResponse == null || webhookResponse.getWebhook() == null || webhookResponse.getAccessToken() == null) {
                logger.error("Failed to get valid webhook response");
                return;
            }
            

            logger.info("Step 2: Determining SQL question...");
            String finalQuery = webhookService.determineSqlQuestion(REG_NO);
            logger.info("Final SQL Query determined: {}", finalQuery);
            

            logger.info("Step 3: Submitting final query...");
            String submissionResponse = webhookService.submitQuery(finalQuery, webhookResponse.getAccessToken());
            
            logger.info("=== PROCESS COMPLETED SUCCESSFULLY ===");
            logger.info("Final submission response: {}", submissionResponse);
            
        } catch (Exception e) {
            logger.error("Error during application startup process: {}", e.getMessage(), e);
            logger.error("Application will continue running but the webhook process failed");
        }
        
        logger.info("=== BAJAJ FINSERV HEALTH APPLICATION READY ===");
    }
    

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
