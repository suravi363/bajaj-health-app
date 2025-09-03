package com.bajajfinservhealth;

import com.bajajfinservhealth.model.GenerateWebhookRequest;
import com.bajajfinservhealth.model.GenerateWebhookResponse;
import com.bajajfinservhealth.model.SubmitQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WebhookService {
    
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String SUBMIT_QUERY_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
    
    @Autowired
    private RestTemplate restTemplate;
    

    public GenerateWebhookResponse generateWebhook(String name, String regNo, String email) {
        logger.info("Starting webhook generation process...");
        logger.info("Request details - Name: {}, RegNo: {}, Email: {}", name, regNo, email);
        
        try {

            GenerateWebhookRequest request = new GenerateWebhookRequest(name, regNo, email);
            

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            

            HttpEntity<GenerateWebhookRequest> entity = new HttpEntity<>(request, headers);
            
            logger.info("Sending POST request to: {}", GENERATE_WEBHOOK_URL);
            logger.info("Request body: {}", request);
            

            ResponseEntity<GenerateWebhookResponse> response = restTemplate.exchange(
                GENERATE_WEBHOOK_URL,
                HttpMethod.POST,
                entity,
                GenerateWebhookResponse.class
            );
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                GenerateWebhookResponse webhookResponse = response.getBody();
                logger.info("Webhook generation successful!");
                logger.info("Webhook URL: {}", webhookResponse.getWebhook());
                logger.info("Access Token: {}", webhookResponse.getAccessToken());
                return webhookResponse;
            } else {
                logger.error("Failed to generate webhook. Status: {}", response.getStatusCode());
                throw new RuntimeException("Failed to generate webhook");
            }
            
        } catch (Exception e) {
            logger.error("Error generating webhook: {}", e.getMessage(), e);
            throw new RuntimeException("Error generating webhook", e);
        }
    }
    

    public String submitQuery(String finalQuery, String accessToken) {
        logger.info("Starting query submission process...");
        logger.info("Final Query: {}", finalQuery);
        
        try {

        SubmitQueryRequest request = new SubmitQueryRequest(finalQuery);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);
            

            HttpEntity<SubmitQueryRequest> entity = new HttpEntity<>(request, headers);
            
            logger.info("Sending POST request to: {}", SUBMIT_QUERY_URL);
            logger.info("Request body: {}", request);
            logger.info("Authorization header: {}", accessToken);
    
            ResponseEntity<String> response = restTemplate.exchange(
                SUBMIT_QUERY_URL,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            logger.info("Query submission response status: {}", response.getStatusCode());
            logger.info("Query submission response body: {}", response.getBody());
            
            return response.getBody();
            
        } catch (Exception e) {
            logger.error("Error submitting query: {}", e.getMessage(), e);
            throw new RuntimeException("Error submitting query", e);
        }
    }
    

    public String determineSqlQuestion(String regNo) {
        logger.info("Determining SQL question based on regNo: {}", regNo);
        

        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits);
        
        logger.info("Last 2 digits: {} (parsed as: {})", lastTwoDigits, lastTwoDigitsInt);
        

        if (lastTwoDigitsInt % 2 == 1) {

            logger.info("Last 2 digits are ODD - Using Question 1");

            return "SELECT * FROM employees WHERE department = 'IT';";
        } else {

            logger.info("Last 2 digits are EVEN - Using Question 2");

            return "SELECT * FROM employees WHERE salary > 50000;";
        }
    }
}
