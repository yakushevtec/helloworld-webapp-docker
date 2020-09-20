The Test Connections Web Service Application running on Spring Boot
==============================================================


The web application API that listens on port 8080 and greets a user with Hello World! and exposes a health status endpoint.
The application utilizes Spring Boot as standalone HTTP Web Server running inside Docker container.

Git:
https://github.com/alex-yakushev/helloworld-webapp-docker.git

To test connection run curl or Web Browser URL.
-------------------------------------
```
curl -v http://localhost:8080/connected?origin=Boston&destination=Newark
or
curl -v http://localhost:8080/connected/Boston/Newark
Should return yes

curl -v http://localhost:8080/connected?origin=Boston&destination=Philadelphia
or
curl -v http://localhost:8080/connected/Boston/Philadelphia
Should return yes

curl -v http://localhost:8080/connected?origin=Philadelphia&destination=Albany
or
curl -v http://localhost:8080/connected/Philadelphia/Albany
Should return no
```

To build the jar-file with Apache Maven
-------------------------------------
```
mvn clean install
```

To build Docker Image with tag "wsapp"
----------------------------------------
```
docker build --tag=wsapp .
```

To run the container with on host port 8080
---------------------------------------------
```
docker run -it -p 8080:8080 wsapp
```

The helper command-line to do all
-----------------------------------
```
mvn clean install && docker build --tag=wsapp . && docker run -it -p 8080:8080 wsapp
```

To test default context
-------------------------------------------------
```
curl -v http://localhost:8080
```
Output
```
$ curl http://localhost:8080
Hello!
```

To check health 
-------------------------------------------------
```
curl -v http://localhost:8080/health
```
Output
```
$ curl http://localhost:8080
Hello World!
```
{
  "status": "OK",
  "version": "0.0.1",
  "uptime": "up since 2020-09-19 23:39:59"
  "cpu:utilization": "8.246428E-5"
  "memory:utilization": "8.246428E-5"
}



