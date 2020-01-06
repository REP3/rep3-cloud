# 1.Project
<a href="">REP3_CLOUD</a>
# 2.Cloud简单配置
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
配置一下EurekaServer端依赖
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
配置zuul依赖
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
Cloud基本的依赖就配置完成了,接下来就配置配置yaml跑起来试试看

# 3.Springboot
SpringBoot不做过多赘述,主要看一下配置文件加载的优先级
配置加载顺序1->12越小优先级越高
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
微服务的监控和管理,将依赖配置在根模块中,所有的子模块SpringBoot就可以使用了
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
# 5.Eureka服务治理中心

`服务注册`:主要用来开微服务架构中有多少服务都部署在哪里

`服务发现`:服务之间调用关系的处理,比如A服务调用C服务,会直接像注册中心发出咨询请求,然后注册中心将`C服务的位置清单`返回给A服务,A便获得了`多个C服务的位置`,然后就可以调用其一.

SpringCloudEureka使用`Netflix Eureka`来实现服务注册与发现,既包括了注册端也包括了服务端

服务端依赖
```groovy
compile 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'

```
客户端依赖
```groovy
compile 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client',
```

服务端Application开启`@EnableEurekaServer`注解标识为服务中心
同时要配置配置文件,给出hostname地址,客户端策略等
```yaml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
```

客户端需要开启`@EnableEurekaClient`标示为Eureka客户端,同时配置出服务中心的地址
```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8000/eureka/

```
