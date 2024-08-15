# BK TRAVEL MICROSERVICES APPLICATION

A website designed for travel enthusiasts.

Using microservices architecture for backend.

## Table of Contents

- Project Structure
- Project Functions
- Getting Started
  - Prerequisites
  - Installation
  - Database Setup
  - Running backend
  - Running frontend
- Usage
- Contributing
- License

## Project Structure

```structure
bk-travel-microservices/
│
├── README.md
│
├── docker-compose.yml
│
├── kafka-deployment.yaml
│
├── database/
│
├── account-service/
│   ├── src/
│   └── pom.xml
│
├── auth-service/
│   ├── src/
│   └── pom.xml
│
├── booking-service/
│   ├── src/
│   └── pom.xml
│
├── chat-service/
│   ├── src/
│   └── pom.xml
│
├── discovery-server/
│   ├── src/
│   └── pom.xml
│
├── gateway-service/
│   ├── src/
│   └── pom.xml
│
├── mail-service/
│   ├── src/
│   └── pom.xml
│
├── marking-service/
│   ├── src/
│   └── pom.xml
│
├── notification-service/
│   ├── src/
│   └── pom.xml
│
├── payment-service/
│   ├── src/
│   └── pom.xml
│
├── review-service/
│   ├── src/
│   └── pom.xml
│
├── staff-service/
│   ├── src/
│   └── pom.xml
│
├── tour-service/
│   ├── src/
│   └── pom.xml
│
├── tourist-attraction-service/
│   ├── src/
│   └── pom.xml
│
├── client/
│   ├── src/
│   └── pom.xml
│
└── cs_client/
    ├── src/
    └── package.json

```

## Project Functions

- User authentication (Sign in/Sign up)
- Tour search, booking, marking and rating.
- Cancel booked tour after successful booking
- Pay online with VNPAY
- Destination search for travel locations.
- Blog for destination
- Real-time chat with customer service representatives.
- Inquiry submission via email.
- Administrator management.

## Getting Started

### Prerequisites

- **_`Java 17`_** or higher
- **_`Node.js`_** (version 16 or higher)
- **_`MySQL`_** (recommend new versions)
- **_`Docker`_** (recommend new versions)

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/Aresky-T/bk-travel-microservices
   ```

2. Building Kafka broker container:

   ###### 1. _*`Change directory to project`*_

   ```bash
   cd bk-travel-microservices
   ```

   ###### 2. _*`Building Kafka zookeeper server container named "zookeeper-01"`*_

   ```bash
   docker-compose -f kafka-deployment.yaml up --no-start zookeeper-01
   ```

   ###### 3. _*`Building Kafka broker server container named "kafka-broker-01"`*_

   ```bash
   docker-compose -f kafka-deployment.yaml up --no-start kafka-broker-01
   ```

3. Buiding Mysql server container

   ```bash
   docker-compose -f docker-compose.yml up mysql-server
   ```

4. Buiding Discovery server and Gateway service containers

   ###### 1. _*`Discovery server`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start discovery-server
   ```

   ###### 2. _*`Gateway service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start gateway-service
   ```

5. Buiding main service containers

   ###### 1. _*`account-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start account-service
   ```

   ###### 2. _*`auth-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start auth-service
   ```

   ###### 3. _*`tour-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start tour-service
   ```

   ###### 4. _*`tourist-attraction-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start tourist-attraction-service
   ```

   ###### 5. _*`booking-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start booking-service
   ```

   ###### 6. _*`payment-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start payment-service
   ```

   ###### 7. _*`review-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start review-service
   ```

   ###### 8. _*`marking-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start marking-service
   ```

   ###### 9. _*`staff-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start staff-service
   ```

   ###### 10. _*`chat-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start chat-service
   ```

   ###### 11. _*`mail-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start mail-service
   ```

   ###### 12. _*`notification-service`*_

   ```bash
   docker-compose -f docker-compose.yml up --build --no-recreate --no-start notification-service
   ```

6. Install client dependencies and building:

   ```bash
   cd client
   npm install
   npm run build
   ```

7. Install cs_client dependencies:

   ```bash
   cd cs_client
   npm install
   npm run build
   ```

### Running the Backend

#### 1. `Starting mysql server and discovery server containers`

```bash
docker start mysql-server discovery-server
```

#### 2. `Starting Kafka zookeeper server and broker server containers`

```bash
docker start zookeeper-01
```

```bash
docker start kafka-broker-01
```

#### 3. `Starting Api gateway service container`

```bash
docker start gateway-service
```

#### 4. `Starting main service container`

```bash
docker start auth-service account-service
```

```bash
docker start tour-service booking-service payment-service
```

```bash
docker start review-service marking-service
```

```bash
docker start tourist-attraction-service
```

```bash
docker start staff-service
```

```bash
docker start chat-service mail-service
```

```bash
docker start notification-service
```

### Running the Frontend

```bash
cd client
serve -s build -l 3000
```

```bash
cd cs_client
serve -s build -l 3001
```

## Usage

- Access the client at `http://localhost:3000`
- Access the customer support client at `http://localhost:3001`
- Backend API endpoints are available at `http://localhost:8080`

## Deployment

- Access the domain: [https://bk-travel.vercel.app](https://bk-travel.vercel.app)

<!-- ## Related Repository

- Access the github link: [https://github.com/Aresky-T/bk_travel_customer_support](https://github.com/Aresky-T/bk_travel_customer_support)
- The deployed domain: [https://bk-travel-customer-support.vercel.app/](https://bk-travel-customer-support.vercel.app/) -->

## Contributing

Contributing are welcome! Please create a pull request.

## License

This project is licensed under the GITHUB Lience - @2024 | all rights reserved.
