**修正中**


Spring Boot + Gradle template
=============

Spring Boot , Gradle で開発する際のテンプレートです。


**依存関係**  

Core(Spring Boot 1.4.0.RELEASE):

| 対象        | 内容  |
| ------------- | :----- |
| org.springframework.boot:spring-boot-starter-data-jpa | JPA |
| org.springframework.boot:spring-boot-starter-security | Spring Security |
| org.springframework.boot:spring-boot-starter-web | Spring MVC |
| org.springframework.boot:spring-boot-starter-thymeleaf | Template engine |
| org.springframework.boot:spring-boot-starter-log4j2 | log4j2 |
| org.thymeleaf.extras:thymeleaf-extras-springsecurity4 | Template engine Spring Security 拡張 |
| org.mybatis.spring.boot:mybatis-spring-boot-starter:1.1.1 | ORM、MyBatis |

Webjars:

| 対象        | 内容  |
| ------------- | :----- |
| org.webjars:webjars-locator:0.32 | WebJar バージョン番号管理 |
| org.webjars:jquery:3.1.0 | jQuery 3.1.0 |
| org.webjars:bootstrap:3.3.6 | Bootstrap 3.3.6 |
| org.webjars:html5shiv:3.7.3 |  | BootstrapのIE8 対応用(HTML5)
| org.webjars.npm:respond.js:1.4.2 | BootstrapのIE8 対応用(レスポンシブ) |
| org.webjars:font-awesome:4.6.3 | Webアイコンフォン(Font Awesome) |


etc:

| 対象        | 内容  |
| ------------- | :----- |
| org.springframework.boot:spring-boot-starter-tomcat | Tomcat ランタイム |
| com.h2database:h2| H2 Database ランタイム |
