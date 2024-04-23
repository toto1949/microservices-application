# **Microservices Project**  

## **Overview**  
This is a microservices-based application designed for managing orders, inventory, and notifications seamlessly. The project demonstrates the implementation of modern microservices architecture using **Java 8** and **Spring Cloud**, with robust features such as **service discovery**, **circuit breakers**, **asynchronous messaging**, and **database management** with **MongoDB** and **MySQL**.  

## **Key Features**  
- **Order Service**: Handles customer orders and integrates with other services to update inventory and send notifications.  
- **Product Service**: Manages product details and inventory.  
- **Inventory Service**: Tracks stock availability and integrates with the Order Service.  
- **Notification Service**: Sends real-time notifications when an order is placed.  
- **Discovery Service (Eureka)**: Enables service discovery and load balancing.  
- **API Gateway**: Serves as the single entry point for client requests, handling routing and security.  
- **Kafka Integration**: Facilitates asynchronous communication between services for events like order placement.  
- **Spring Cloud Security**: Ensures secure communication between services.  
- **Zipkin**: Enables distributed tracing for performance monitoring.  
- **Circuit Breaker**: Ensures system resilience during service failures.  
- **WebFlux**: Provides reactive programming capabilities for better scalability.  

## **Technologies Used**  
- **Backend**: Java 8, Spring Boot, Spring Cloud, WebFlux  
- **Databases**: MySQL (for relational data), MongoDB (for NoSQL data)  
- **Message Broker**: Apache Kafka  
- **Service Discovery**: Eureka  
- **API Gateway**: Spring Cloud Gateway  
- **Security**: Spring Cloud Security  
- **Monitoring**: Zipkin  
- **Containerization**: Docker  

## **Architecture**  
The project follows a microservices architecture with the following components:  
1. **Order Service**  
2. **Product Service**  
3. **Inventory Service**  
4. **Notification Service**  
5. **Discovery Service (Eureka)**  
6. **API Gateway**  

![Architecture Diagram](https://via.placeholder.com/800x400?text=Architecture+Diagram)  
*(Replace the placeholder with your actual architecture diagram if available)*  

## **Setup Instructions**  
1. Clone the repository:  
   \`\`\`bash  
   git clone https://github.com/your-repo/microservices-project.git  
   cd microservices-project  
   \`\`\`  

2. Build and package the services:  
   \`\`\`bash  
   mvn clean install  
   \`\`\`  

3. Run Docker Compose to start all services:  
   \`\`\`bash  
   docker-compose up --build  
   \`\`\`  

4. Access the services:  
   - API Gateway: \`http://localhost:8080\`  
   - Eureka Dashboard: \`http://localhost:8761\`  
   - Zipkin Dashboard: \`http://localhost:9411\`  

5. Use Kafka for asynchronous communication. Make sure Kafka and Zookeeper are running.  

## **How It Works**  
1. **Place an Order**:  
   - The **Order Service** validates the order, interacts with the **Inventory Service**, and publishes an event to **Kafka**.  

2. **Inventory Check**:  
   - The **Inventory Service** checks stock availability and updates the database.  

3. **Notifications**:  
   - The **Notification Service** listens to **Kafka** events and sends notifications to the customer.  

4. **Tracing and Monitoring**:  
   - Use **Zipkin** for distributed tracing and debugging service interactions.  

5. **Fault Tolerance**:  
   - **Circuit breakers** ensure system stability during service downtimes.  

## **Future Enhancements**  
- Add authentication and authorization with OAuth2 or JWT.  
- Implement a UI for better user interaction.  
- Add performance monitoring with Prometheus and Grafana.  

## **Contributors**  
- **Taoufiq El Moutaouakil**  

## **License**  
This project is licensed under the MIT License.  