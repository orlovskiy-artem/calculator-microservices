# calculator-microservices
Basic calculator app done in microservices style

Simple calculator microservice
It can add, multiply and subtract integers over url

To start the application you need to ensure the docker compose is installed. After that, run the following command:
```
docker compose up --build
```

The use is simple:
```
a+b:
curl localhost:8080/add/a/b

a-b:
curl localhost:8080/subtract/a/b

a*b:
curl localhost:8080/multiply/a/b
