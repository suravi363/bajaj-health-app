# Bajaj Finserv Health Application

A Spring Boot application that automatically generates webhooks and submits SQL queries to the Bajaj Finserv Health API.

## 🚀 Features

- **Automatic Startup Process**: Runs webhook generation and query submission on application startup
- **Dynamic SQL Question Selection**: Determines SQL question based on registration number (odd/even logic)
- **RESTful API Integration**: Uses RestTemplate for HTTP calls
- **Comprehensive Logging**: Detailed logs for debugging and monitoring
- **JWT Token Handling**: Manages access tokens for API authentication

## 📋 Requirements

- Java 17 or higher
- Maven 3.6 or higher
- Internet connection for API calls

## 🛠️ Project Structure

```
src/main/java/com/bajajfinservhealth/
├── BajajApp.java                    # Main Spring Boot application
├── WebhookService.java              # Service for API calls
└── model/
    ├── GenerateWebhookRequest.java  # Request model for webhook generation
    ├── GenerateWebhookResponse.java # Response model for webhook generation
    └── SubmitQueryRequest.java      # Request model for query submission
```

## 🚀 How to Run

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

## 📊 Application Flow

1. **Startup**: Application starts and automatically begins the process
2. **Webhook Generation**: Sends POST request to generate webhook and get access token
3. **SQL Question Determination**: Analyzes registration number to determine which SQL question to use
4. **Query Submission**: Submits the final SQL query with proper authorization
5. **Logging**: All steps are logged for monitoring and debugging

## 🔧 Configuration

The application uses the following configuration in `application.properties`:

- **API Endpoints**: Configured in WebhookService
- **User Details**: Name, Registration Number, Email
- **Logging**: Detailed logging configuration
- **HTTP Timeouts**: 30-second timeouts for API calls

## 📝 Current SQL Queries

The application currently uses placeholder SQL queries:

- **Question 1** (Odd regNo): `SELECT * FROM employees WHERE department = 'IT';`
- **Question 2** (Even regNo): `SELECT * FROM employees WHERE salary > 50000;`

**Note**: Replace these with actual SQL queries from the Google Drive link as specified in the requirements.

## 🔍 Logging

The application provides comprehensive logging:

- Request/Response details for all API calls
- SQL question determination logic
- Error handling and exception details
- Process flow tracking

## 🚨 Error Handling

- Network timeouts and connection errors
- Invalid API responses
- JSON parsing errors
- Authentication failures

## 📦 Dependencies

- Spring Boot Web Starter
- Jackson for JSON processing
- JJWT for JWT token handling
- Spring Boot Test for testing

## 🔄 API Integration

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

## 🎯 Next Steps

1. Replace placeholder SQL queries with actual questions from Google Drive
2. Test with real API endpoints
3. Add unit tests for better code coverage
4. Implement retry logic for failed API calls
5. Add configuration for different environments

## 📞 Support

For any issues or questions, please check the application logs for detailed error information.
