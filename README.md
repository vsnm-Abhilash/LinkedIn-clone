# LinkedIn Clone Project

This is a LinkedIn clone built using modern technologies to mimic core functionalities like user authentication, connections, posting, liking/unliking posts, and real-time notifications. The project demonstrates various principles of microservices architecture, JWT authentication, and the use of a graph database for managing user connections.

## Features

- **User Signup & Login**: Users can sign up and log in to the platform. JWT (JSON Web Tokens) is used for authentication and authorization.
- **User Connections**: Displays first-degree and second-degree connections, similar to LinkedIn. This functionality is powered by **Neo4j**, a graph database.
- **Post Management**: Users can:
  - Create posts
  - Get specific posts
  - Retrieve all their posts
  - Like and unlike other users' posts
- **Real-Time Notifications**: Users are notified when:
  - A connection creates a post
  - A connection likes or unlikes a post
  - These notifications are managed by **Kafka**, which serves as the notification service for the platform.
  
## Architecture

This project follows a **microservices architecture**, where different services handle distinct responsibilities:

1. **User Service**: Handles user signup, login, and authentication/authorization.
2. **Post Service**: Manages the creation, retrieval, and liking/unliking of posts.
3. **Notification Service**: Sends real-time notifications to users about posts and likes/unlikes using Kafka.
4. **Load Balancer**: Ensures that traffic is distributed evenly across services to optimize performance.
5. **Service Discovery**: Enables services to discover and communicate with each other in a dynamic environment.

## Technologies Used

- **Backend**: Java/Spring Boot (or your preferred tech stack)
- **Authentication**: JWT (JSON Web Tokens)
- **Database**: 
  - **Neo4j** (Graph Database) for managing user connections.
  - **Relational Database** (Optional) for storing posts.
- **Messaging Queue**: Kafka for real-time notifications.
- **Load Balancing**: Nginx (or any other preferred load balancer).
- **Service Discovery**: Eureka (or any preferred service discovery tool).
