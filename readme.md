# Installation with maven

Add jitpack repository :

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add in your `pom.xml` dependencies :

```xml
<dependency>
    <groupId>com.github.oualidbouh</groupId>
    <artifactId>rabbitmq-queue-listener-management</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Prerequisites
In order to use this lib, you need to use at least spring-boot-starter-web to have an embedded tomcat and to be able to perform some HTTP requests to control your RabbitMQ message listeners.

# How to ?
This library aims to give you control over your spring rabbitmq listeners via REST APIs. You can perform the calls via Postman or whatever http client you use.

The available manipulation are listed bellow.

#### Stop a message consumer:

You just need to perform a POST request that way :
```
POST ${your_server_url}/rabbitmq/listeners/{your_listener_id}/stop
```

#### Start a message consumer:

You just need to perform a POST request that way :
```
POST ${your_server_url}/rabbitmq/listeners/{your_listener_id}/start
```

#### Stop all the message consumers:

You just need to perform a POST request that way :
```
POST ${your_server_url}/rabbitmq/listeners/stop
```

#### Start all the message consumers:

You just need to perform a POST request that way :
```
POST ${your_server_url}/rabbitmq/listeners/start
```

#### Fetch all the message consumers:

You just need to perform a GET request that way :
```
GET ${your_server_url}/rabbitmq/listeners/stats
```