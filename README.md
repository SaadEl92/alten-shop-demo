# alten-shop-demo

## Requirements

- [Docker](https://docs.docker.com/get-docker/)

- [Docker Compose Plugin](https://docs.docker.com/compose/install/linux/)

## Getting Started

### 1. Clone the repository

```sh
git clone git@github.com:SaadEl92/alten-shop-demo.git
cd alten-shop-app
```

### 2. Launch the application

```sh
docker compose up -d
```

This will:

Build the app and database images

- Start PostgreSQL database on port 5455

- Start the Spring Boot app on port 8099

### 3. Open API documentation

Once containers are up, visit:

http://localhost:8099/swagger-ui/index.html

You can explore all REST endpoints from the Swagger UI.

### 4. Cleaning Up

When you’re done, remove all containers, volumes, and images created by this compose file:

```sh
# From the 'alten-shop app' folder:
docker-compose down --rmi all --volumes
```

This command will stop and remove the alten-shop-app and database containers, drop the named volumes, and delete the locally built images (alten-shop-app:latest and alten-shop-database:16). Other images on your machine remain untouched.