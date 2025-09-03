# Bajaj Finserv Health Application

A Spring Boot application that automatically generates webhooks and submits SQL queries to the Bajaj Finserv Health API.

##  Features

- **Automatic Startup Process**: Runs webhook generation and query submission on application startup
- **Dynamic SQL Question Selection**: Determines SQL question based on registration number (odd/even logic)
- **RESTful API Integration**: Uses RestTemplate for HTTP calls
- **Comprehensive Logging**: Detailed logs for debugging and monitoring
- **JWT Token Handling**: Manages access tokens for API authentication

##  Project Structure

```
src/main/java/com/bajajfinservhealth/
├── BajajApp.java                    # Main Spring Boot application
├── WebhookService.java              # Service for API calls
└── model/
    ├── GenerateWebhookRequest.java  # Request model for webhook generation
    ├── GenerateWebhookResponse.java # Response model for webhook generation
    └── SubmitQueryRequest.java      # Request model for query submission
```

##  How to Run

### Option 1: Using Maven Spring Boot Plugin
```bash
mvn spring-boot:run
```

### Option 2: Build and Run JAR
```bash
# Build the project
mvn clean package

# Run the JAR file
java -jar target/bajaj-health-1.0-SNAPSHOT.jar
```

##  Configuration

The application uses the following configuration in `application.properties`:

- **API Endpoints**: Configured in WebhookService
- **User Details**: Name, Registration Number, Email
- **Logging**: Detailed logging configuration
- **HTTP Timeouts**: 30-second timeouts for API calls


## Error Handling

- Network timeouts and connection errors
- Invalid API responses
- JSON parsing errors
- Authentication failures

## API Integration

### Generate Webhook
- **URL**: `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- **Method**: POST
- **Body**: JSON with name, regNo, email
- **Response**: webhook URL and access token

### Submit Query
- **URL**: `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`
- **Method**: POST
- **Headers**: Authorization (JWT token)
- **Body**: JSON with finalQuery
