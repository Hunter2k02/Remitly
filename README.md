# Swift Code API

This API provides functionalities for managing bank information, including retrieving bank details by SWIFT code, retrieving banks by country, adding new banks, and deleting existing banks. The data is initially loaded from a CSV file on application startup.

## Technologies Used

*   Java 17
*   Spring Boot 3.4.1
*   PostgreSQL Database
*   JUnit testing framework


## Installation

#### 1.  Make sure you have JDK 17 and Maven installed.
#### 2.  Clone the repository:

    ```bash
    git clone https://github.com/Hunter2k02/Remitly
    ```

#### 3.  Navigate to the project directory:

    ```bash
    cd Remitly
    ```

#### 4.  Build the project:

    ```bash
    mvn clean install
    ```

#### 5.  Configure the application:

    * Create a file named `application.yml` in your project's root directory (src/main/resources).
    * Note: These configurations target the PostgreSQL database connection.
    * Replace the placeholders in the following configuration with your actual values:
    
```yaml
server:
  port: 8080
spring:
  sql:
    init:
      mode: always
  application:
    name: SWIFT
  datasource:
    url: jdbc:postgresql://localhost:5432/your_database_name  # Replace with your database name
    driver-class-name: org.postgresql.Driver
    username: your_username  # Replace with your database username
    password: your_password  # Replace with your database password
  jpa:
    properties:
      show_sql: true
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        ddl-auto: none
```

#### 6.  Run the application:
```bash
java -jar target/SWIFT-0.0.1-SNAPSHOT.jar
```
## Containerization (Docker)

This project can be easily containerized using Docker.

#### 1.  Make sure you have Docker and Docker Compose installed.

#### 2.  Navigate to the project directory containing the `Dockerfile` and `docker-compose.yml` files.

#### 3.  Build and run the containers:

    ```bash
    docker-compose up -d --build
    ```

This command:
- Builds the Docker images for both the Spring Boot app and PostgreSQL database.
- Starts the containers in detached mode (`-d`).
- Runs the application, which will be available at `http://localhost:8080`.

#### 4.  Access the application at `http://localhost:8080`.

#### 5.  Stop the containers:

    ```bash
    docker-compose down
    ```

## Usage

**Retrieving Bank Details by SWIFT Code**

```bash
curl http://localhost:8080/v1/swift-codes/{swiftCode}
```

Example:
```bash
curl http://localhost:8080/v1/swift-codes/AAISALTRXXX
```

**Retrieving Bank Details by ISO2 Code**

```bash
curl http://localhost:8080/v1/swift-codes/country/{countryISO2}
```
Example:
```bash
curl http://localhost:8080/v1/swift-codes/country/PL
```

**Adding a New Bank**
```bash
curl -X POST \
  http://localhost:8080/v1/swift-codes/ \
  -H 'Content-Type: application/json' \
  -d '{
    "swiftCode": "NEWSWIFT123",
    "bankName": "New Bank",
    "address": "New Address",
    "countryISO2": "US",
    "countryName": "United States",
    "headquarter": true
  }'
```

**Deleting a Bank**
```bash
curl -X DELETE \
  http://localhost:8080/v1/swift-codes/ \
  -H 'Content-Type: application/json' \
  -d '{
      "swiftCode": "NEWSWIFT123",
      "bankName": "New Bank",
      "countryISO2": "US"
  }'
```
### **Note to Remitly (Regarding Endpoint 4 - Delete Bank):**

The assignment description for Endpoint 4 was initially ambiguous regarding the deletion criteria. It described the endpoint as DELETE /v1/swift-codes/{swift-code} (implying deletion by Swift code alone) but also stated that bankName and countryISO2 should match.

To resolve this ambiguity and prioritize data integrity, I have implemented the endpoint to require all three parameters (swiftCode, bankName, and countryISO2) to be provided in the request body (using @RequestBody) and to match a database record for deletion to proceed. This approach prevents accidental deletions and ensures that only precisely matching records are removed.
