[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/bFlAvWr6)

# Boutique Hotel Technikum

## Setup

### Frontend

#### Install Node.js LTS

https://nodejs.org/en (current version: 20.13.0)


#### Install NPM packages
go to /app folder

```
cd app/boutique-hotel-technikum
npm install
```

#### Set environment variables
Copy ```.env.development.example``` to ```.env.development``` and add your Google API Key as the VITE_API_KEY=[YOUR_KEY]!

#### Run Ionic app
```
npm run dev
```

### Backend (Dev)

#### Install

##### Install Java 22

##### Install Maven

https://maven.apache.org/install.html or just use it only through IntelliJ IDEA

#### Generate Open API code

```
mvn clean install
```

Or run install on IntelliJ IDEA Maven tab

#### Run DB

```
docker-compose -f backend/docker-compose-db.yaml up -d
```

#### Override DB Config with ENV variables

See -> [baeldung.com/intellij-idea-environment-variables](https://www.baeldung.com/intellij-idea-environment-variables)

````
SPRING_DATASOURCE_USERNAME=<username>;SPRING_DATASOURCE_PASSWORD=<password>
```` 

#### Run api-service

### Backend (Just run)

#### Generate backend Docker image

 - Docker should be installed and running

From the root directory of the project

```
docker build backend/api-service -f backend/api-service/Dockerfile -t boutique-hotel-api-service:latest
```

#### Run backend and database

```
docker-compose -f backend/docker-compose-be.yaml up -d
```