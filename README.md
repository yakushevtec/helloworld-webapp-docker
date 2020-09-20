The Hello World Web Service Application running on Spring Boot
==============================================================


The hello world web application API that listens on port 8080 and greets a user with Hello! and exposes a health status endpoint.
The application utilizes Spring Boot as standalone HTTP Web Server running inside Docker container.

Git:
https://github.com/alex-yakushev/helloworld-webapp-docker.git

1 To build the jar-file with Apache Maven
-------------------------------------
```
mvn clean install
```

2 To build Docker Image with tag "wsapp"
----------------------------------------
```
docker build --tag=wsapp .
```

3 To run the container with on host port 8080
---------------------------------------------
```
docker run -it -p 8080:8080 wsapp
```

4 The helper command-line to do all
-----------------------------------
```
mvn clean install && docker build --tag=wsapp . && docker run -it -p 8080:8080 wsapp
```

5 To test default context
-------------------------------------------------
```
curl -v http://localhost:8080
```
Output
```
$ curl http://localhost:8080
Hello!
```

6 To check health 
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



