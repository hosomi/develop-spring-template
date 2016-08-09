Spring Boot + Gradle template
=============

Spring Boot , Gradle で開発する際のテンプレートです。


# 依存関係  

## Core(Spring Boot 1.4.0.RELEASE):

| 対象        | 内容  |
| ------------- | :----- |
| org.springframework.boot:spring-boot-starter-data-jpa | JPA |
| org.springframework.boot:spring-boot-starter-security | Spring Security |
| org.springframework.boot:spring-boot-starter-web | Spring MVC |
| org.springframework.boot:spring-boot-starter-thymeleaf | Template engine |
| org.springframework.boot:spring-boot-starter-log4j2 | log4j2 |
| org.thymeleaf.extras:thymeleaf-extras-springsecurity4 | Template engine Spring Security 拡張 |
| org.thymeleaf.extras:thymeleaf-extras-conditionalcomments:2.1.2.RELEASE | Template engine IE Conditional comments 拡張 |
| org.mybatis.spring.boot:mybatis-spring-boot-starter:1.1.1 | ORM、MyBatis |

## Webjars:

| 対象        | 内容  |
| ------------- | :----- |
| org.webjars:webjars-locator:0.32 | WebJar バージョン番号管理 |
| org.webjars:jquery:2.2.4 | jQuery 2.2.4 (*1) |
| org.webjars:bootstrap:3.3.7 | Bootstrap 3.3.7 |
| org.webjars:html5shiv:3.7.3 |  | Bootstrap の IE8 対応用 (HTML5) |
| org.webjars.npm:respond.js:1.4.2 | Bootstrap の IE8 対応用(レスポンシブ) |
| org.webjars:font-awesome:4.6.3 | Web アイコンフォン(Font Awesome) |
| org.webjars:Eonasdan-bootstrap-datetimepicker:4.17.37-1 | bootstrap-DateTimePicker(Bootstrap,jQuery, moment.js(*2) 必須) |


*1 jQuery を必要とするモジュールが 3 系 に対応していないものが多く、2 系にした。  
*2 org.webjars:Eonasdan-bootstrap-datetimepicker の依存関係として moment.js の webjars がダウンロードされます。（バージョン指定が必要な場合は、定義が必要です。）

## etc:

| 対象        | 内容  |
| ------------- | :----- |
| org.springframework.boot:spring-boot-starter-tomcat | Tomcat ランタイム |
| com.h2database:h2| H2 Database ランタイム |


# 動作環境

2016.08.08 現在  

* JDK 8 (8u101)


# 動作方法

1. [ダウンロード](https://github.com/hosomi/develop-spring-template/archive/master.zip "ダウンロード")後、解凍し develop-spring-template-master ディレクトリ直下へコンソールで移動。
2. 「 gradlew bootRun 」 を実行。
3. ブラウザを立ち上げて 「 http://localhost:8080/template/ 」 にアクセス。


## ログインID

| ログインID       | パスワード  |
| ------------- | ----- |
| test | testpass |

**※ロールはありません。**



