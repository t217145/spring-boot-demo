### create docker
```
docker network create RmqNetwork

docker run -itd --name rabbitmq -p 15672:15672 -p 5672:5672 --network RmqNetwork -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin rabbitmq:3-management

docker run -itd --name rmqdemo -p 8080:8080 --network RmqNetwork -e RABBITMQ_HOST=rabbitmq -e RABBITMQ_USER=admin -e RABBITMQ_PWD=admin rmqdemo
```