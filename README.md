# 1.Project

<a href="">REP3_CLOUD</a>

# 2.Cloud 简单配置

## 2.1.父模块配置

主要就是配置一下通用依赖和基本插件

```groovy
buildscript {
    ext {
        springBootVersion = '2.2.2.RELEASE'
        springBootManagementVersion = '1.0.8.RELEASE'
        springCloudVersion = 'Hoxton.SR1'
    }
    repositories {
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        mavenCentral()
        maven { url 'https://repo.spring.io/snapshot' }
        maven { url 'https://repo.spring.io/milestone' }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath("io.spring.gradle:dependency-management-plugin:${springBootManagementVersion}")
    }
}

allprojects {
    group "com.github.rep3.cloud"
    version "1.0.0"
}

subprojects {
    apply plugin: 'java'
    apply plugin: 'application'
    apply plugin: 'idea'
    apply plugin: 'eclipse'
    apply plugin: 'war'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    jar {
        enabled = true
    }
    bootJar {
        classifier = 'boot'
    }
    repositories {
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        mavenCentral()
        maven { url 'https://repo.spring.io/snapshot' }
        maven { url 'https://repo.spring.io/milestone' }
    }
    dependencies {
        compile(
                'org.springframework.boot:spring-boot-starter-web',
                'org.springframework.boot:spring-boot-starter-tomcat',
                'org.springframework.boot:spring-boot-starter-actuator',
                'org.springframework.cloud:spring-cloud-starter',
                'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client',
                'com.google.guava:guava:23.0'

        )
        testCompile(
                "org.springframework:spring-test",
                "junit:junit:4.12"
        )
    }
    dependencyManagement {
        imports { mavenBom("org.springframework.boot:spring-boot-dependencies:${springBootVersion}") }
        imports { mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}" }
    }
}
```

## 2.2.EurekaServer 服务注册中心

配置一下 EurekaServer 端依赖

```groovy
mainClassName = 'com.github.rep3.cloud.eureka.EurekaApplication'
springBoot {
    mainClassName = 'com.github.rep3.cloud.eureka.EurekaApplication'
    buildInfo {
        properties {
            artifact = 'eureka'
            version = '1.0.0'
            group = 'com.github.rep3.cloud'
            name = 'eureka'
        }
    }
}
bootJar {
    classifier = 'boot'
}
dependencies {
    compile 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
}
```

## 2.3.Zuul 网关

配置 zuul 依赖

```groovy
mainClassName = 'com.github.rep3.cloud.zuul.Rep3ZuulApplication'
springBoot {
    mainClassName = 'com.github.rep3.cloud.zuul.Rep3ZuulApplication'
    buildInfo {
        properties {
            artifact = 'zuul'
            version = '1.0.0'
            group = 'com.github.rep3.cloud'
            name = 'zuul'
        }
    }
}
bootJar {
    classifier = 'boot'
}
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-netflix-zuul')
}
```

Cloud 基本的依赖就配置完成了，接下来就配置配置 YAML 跑起来试试看

# 3.Springboot

SpringBoot 不做过多赘述，主要看一下配置文件加载的优先级
配置加载顺序 1->12 越小优先级越高

```
1.命令行传入参数
2.SPRING_APPLICATION_JSON中的属性
3.java:comp/env中的JNDI属性
4.Java的系统属性:System.getProperties()
5.操作系统环境变量
6.random*配置的随机属性
7.位于当前应用jar包之外的application-profile.yaml配置文件
8.位于当前应用jar包之内的application-profile.yaml配置文件
9.位于jar包之外的appication.yml配置
10.位于jar包之内的application.yml配置
11.@Configuration配置类中
12.应用默认属性比如port默认8080
```

# 4.SpringBootActutor

微服务的监控和管理，将依赖配置在根模块中，所有的子模块 SpringBoot 就可以使用了

```groovy
'org.springframework.boot:spring-boot-starter-actuator'
```

启动以后查看 /health 就可以看到监控信息了

放开所有监控点

```yaml
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

查看所有监控点

```
http://192.168.0.101:8002/actuator
```

返回了所有监控点信息

```json
{"_links":{"self":{"href":"http://192.168.0.101:8002/actuator","templated":false},"archaius":{"href":"http://192.168.0.101:8002/actuator/archaius","templated":false},"beans":{"href":"http://192.168.0.101:8002/actuator/beans","templated":false},"caches-cache":{"href":"http://192.168.0.101:8002/actuator/caches/{cache}","templated":true},"caches":{"href":"http://192.168.0.101:8002/actuator/caches","templated":false},"health-path":{"href":"http://192.168.0.101:8002/actuator/health/{*path}","templated":true},"health":{"href":"http://192.168.0.101:8002/actuator/health","templated":false},"info":{"href":"http://192.168.0.101:8002/actuator/info","templated":false},"conditions":{"href":"http://192.168.0.101:8002/actuator/conditions","templated":false},"configprops":{"href":"http://192.168.0.101:8002/actuator/configprops","templated":false},"env":{"href":"http://192.168.0.101:8002/actuator/env","templated":false},"env-toMatch":{"href":"http://192.168.0.101:8002/actuator/env/{toMatch}","templated":true},"loggers-name":{"href":"http://192.168.0.101:8002/actuator/loggers/{name}","templated":true},"loggers":{"href":"http://192.168.0.101:8002/actuator/loggers","templated":false},"heapdump":{"href":"http://192.168.0.101:8002/actuator/heapdump","templated":false},"threaddump":{"href":"http://192.168.0.101:8002/actuator/threaddump","templated":false},"metrics-requiredMetricName":{"href":"http://192.168.0.101:8002/actuator/metrics/{requiredMetricName}","templated":true},"metrics":{"href":"http://192.168.0.101:8002/actuator/metrics","templated":false},"scheduledtasks":{"href":"http://192.168.0.101:8002/actuator/scheduledtasks","templated":false},"mappings":{"href":"http://192.168.0.101:8002/actuator/mappings","templated":false},"refresh":{"href":"http://192.168.0.101:8002/actuator/refresh","templated":false},"features":{"href":"http://192.168.0.101:8002/actuator/features","templated":false},"service-registry":{"href":"http://192.168.0.101:8002/actuator/service-registry","templated":false}}}
```

```
1./beans: 所有bean信息
2./caches:缓存信息
3./health:服务状态
4./info:服务自定义信息
5./configprops:应用配置的属性信息
6./env:应用所有可用环境信息
7./mappings:所有SpringMvc映射关系信息
8./metrics:内存信息,线程信息,垃圾回收信息等
9./dump:运行中的线程信息
10./trace:http跟踪信息
...
```

# 5.Eureka 服务治理中心

 `服务注册` :主要用来开微服务架构中有多少服务都部署在哪里

 `服务发现` :服务之间调用关系的处理，比如 A 服务调用 C 服务，会直接像注册中心发出咨询请求，然后注册中心将 `C服务的位置清单` 返回给 A 服务，A 便获得了 `多个C服务的位置` ,然后就可以调用其一。

SpringCloudEureka 使用 `Netflix Eureka` 来实现服务注册与发现，既包括了注册端也包括了服务端

服务端依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'

```

客户端依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client',
```

服务端 Application 开启 `@EnableEurekaServer` 注解标识为服务中心
同时要配置配置文件，给出 hostname 地址，客户端策略等

```yaml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
```

客户端需要开启 `@EnableEurekaClient` 标示为 Eureka 客户端，同时配置出服务中心的地址

```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8000/eureka/

```

## 5.1高可用
上面搭建的只有一个服务注册中心`1 Eureka : N Springboot`,当这一台Eureka崩掉以后,所有服务将不能正常访问

在Eureka中,所有服务既是服务提供者,也是服务消费者,Eureka本身也不例外,现在放开一下配置,并新建一个Eureka服务,两者互相注册成服务
```yaml
  client:
    register-with-eureka: false
    fetch-registry: false
```
EurekaA
```yaml
server:
  port:8001
spring:
  application:
    name:eureka1
eureka:
  instance:
    hostname: peer1
  client:
    serviceUrl:
      defaultZone: http://peer2:8000/eureka
```
EurekaB
```yaml
server:
  port:8000
spring:
  application:
    name:eureka2
eureka:
  instance:
    hostname: peer2
  client:
    serviceUrl:
      defaultZone: http://peer1:8001/eureka
```
最后将peer的ip地址解析在/etc/hosts中即可完成eureka高可用部署
高可用服务注册
```yaml
spring:
  profiles: dev
  application:
    name: auth
server:
  port: 8002
eureka:
  client:
    serviceUrl:
      defaultZone: http://peer1/eureka/,http://peer2/eureka/
management:
  endpoints:
    web:
      exposure:
        include: '*'

```
## 5.2负载均衡

ribbon已经在zuul里了所以不必添加依赖
ribbon和eureka联合使用时,在eureka的服务发现基础上实现了一套`服务选择`的策略,从而达到每个服务负载均衡

将`@EnableEurekaClient`替换为`@EnableDiscoveryClient`
当使用服务时,ribbon会轮询服务从而走负载较低的服务达到负载均衡的效果

## 5.3 Eureka基础架构

```
服务注册中心: Eureka提供的服务端,提供服务注册与发现功能
服务提供: Eureka提供的客户端,将自己的服务注册到Eureka中
服务消费者: 消费者从应用中心获取服务列表,从而使消费者知道到何处调用服务,ribbon来实现服务消费,另外还有Feign消费方式
```

![image.png](https://img.hacpai.com/file/2020/01/image-95805443.png)

```
1.服务注册
服务提供者在启动的时候`发送REST`请求到Eureka,其中包含了自身服务的信息
Eureka接收到请求后,将元数据信息存储在`双层Map`中,第一层key为服务名称,第二层是具体服务实例名称.

2.服务同步
服务提供者可能注册到了不同的注册中心,他们的信息分别被多个eureka维护
所以访问其中一台eureka是无法调用到其余服务的,那么将eureka构建成集群模式
当服务提供者发送`REST请求`到其中一个注册中心时
eureka将请求转发给集群中其他的eureka中,从而实现同步

3.服务续约
心跳检测来观测哪些服务死掉,死掉的服务会被剔除出注册中心集群中去
其中有两个配置项
服务续约任务调用时间间隔,默认为30秒
eureka.instance.lease-renewal-interval-in-seconds=30
服务失效时间默认为90秒
eureka.instance.lease-expiration-duration-in-seconds=90
```
