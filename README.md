Spring Boot + Gradle template
=============

[![Build Status](https://travis-ci.org/hosomi/develop-spring-template.svg?branch=master)](https://travis-ci.org/hosomi/develop-spring-template)
[![build status](https://circleci.com/gh/hosomi/develop-spring-template.svg?style=shield&circle-token=9a9efeb27aabd4d1173951957f233d05f246d6c0)](https://circleci.com/gh/hosomi/develop-spring-template)
[![wercker status](https://app.wercker.com/status/46670404016ddfb8110dd3023bed1473/s/master "wercker status")](https://app.wercker.com/project/byKey/46670404016ddfb8110dd3023bed1473)
![Heroku](http://heroku-badge.herokuapp.com/?app=angularjs-crypto&style=flat)

Spring Boot , Gradle で開発する際のテンプレートです。  

現在提供している機能：
* SpringSecurity による独自テーブルを利用したログイン処理(パスワードはハッシュ化)。
* トランザクション管理（システムエラー時ロールバック）
* 共通のログ出力処理（操作ログ、システムエラーログ、SQL（trace,debug））
* テストコードサンプル
  * SpringSecurity
* コントロールサンプル
  * DateTimePicker : 日付選択（日付のみ、時刻のみ、両方）
  * typeahead : サジェスト機能のようなもの
  * Modal(Bootstrap) ※サブミット非対応
  * bootstrap-dialog : アラート表示
  * bootstrap-switch : スイッチ(トグルスイッチ)

* マスタサンプル(ORM:Mybatis)
  * User : バリデーション、カスタムバリデーションサンプル
  * Company : Mybatis XML サンプル

* etc
  * 入力値検証サンプル : 独自アノテーション（単独、相関チェック）


# 依存関係  

## Core(Spring Boot 1.4.0.RELEASE):

| 対象        | 内容  |
| ------------- | :----- |
| org.springframework.boot:spring-boot-starter-data-jpa | JPA |
| org.springframework.boot:spring-boot-starter-security | Spring Security |
| org.springframework.boot:spring-boot-starter-web | Spring MVC |
| org.springframework.boot:spring-boot-starter-thymeleaf | Template engine Thymeleaf3|
| org.springframework.boot:spring-boot-starter-log4j2 | log4j2 |
| org.thymeleaf.extras:thymeleaf-extras-springsecurity4 | Template engine Spring Security 拡張 |
| org.thymeleaf.extras:thymeleaf-extras-conditionalcomments:2.1.2.RELEASE | Template engine IE Conditional comments 拡張 |
| org.mybatis.spring.boot:mybatis-spring-boot-starter:1.1.1 | ORM、MyBatis |

## ext:

| 対象        | 内容  |
| ------------- | :----- |
| ext['thymeleaf.version'] = '3.0.1.RELEASE' | Thymeleaf version 3 を利用する設定、Spring Boot 1.4.0.RELEASE のデフォルトは version2 がデフォルト |
| ext['thymeleaf-layout-dialect.version'] = '2.0.0' | Thymeleaf version 3 からは dialect の指定が必要。 |

## Webjars:

| 対象        | 内容  |
| ------------- | :----- |
| org.webjars:webjars-locator:0.32 | WebJar バージョン番号管理 |
| org.webjars:jquery:2.2.4 | jQuery 2.2.4 (*1) |
| org.webjars:bootstrap:3.3.6 | Bootstrap 3.3.6 |
| org.webjars:html5shiv:3.7.3 | Bootstrap の IE8 対応用 (HTML5) |
| org.webjars.npm:respond.js:1.4.2 | Bootstrap の IE8 対応用(レスポンシブ) |
| org.webjars:font-awesome:4.6.3 | Web アイコンフォン(Font Awesome) |
| org.webjars:Eonasdan-bootstrap-datetimepicker:4.17.37-1 | bootstrap-DateTimePicker(Bootstrap,jQuery, moment.js(*2) 必須) |
| org.webjars.bower:sticky-footer:0.1.4 | フッター表示 |
| org.webjars.npm:bootswatch:3.3.6 | Bootstrap のテーマ拡張(Lumen を適用) org.webjars.bower:bootswatch:3.3.7 は依存性に問題が発生 |
| org.webjars:typeaheadjs:0.11.1 | https://twitter.github.io/typeahead.js/|
| org.webjars:hoganjs:3.0.2 | http://twitter.github.io/hogan.js/ typeahead 内部で html テンプレートエンジンとして利用。|
| org.webjars.npm:bootstrap-dialog:1.34.6 | http://nakupanda.github.io/bootstrap3-dialog/ JavaScript のみで生成可能なダイアログ|


*1 jQuery を必要とするモジュールが 3 系 に対応していないものが多く、2 系にした。  
*2 org.webjars:Eonasdan-bootstrap-datetimepicker の依存関係として moment.js の webjars がダウンロードされます。（バージョン指定が必要な場合は、定義が必要です。）

## etc:

| 対象        | 内容  |
| ------------- | :----- |
| org.springframework.boot:spring-boot-starter-tomcat | Tomcat ランタイム |
| webapp-runner | ランチャーアプリケーション Heroku で war を起動させるために利用 |
| com.h2database:h2| H2 Database ランタイム |
| org.springframework.boot:spring-boot-starter-test | SpringBoot test |
| org.springframework.security:spring-security-test | SpringBootSecurity |
| org.dbunit:dbunit:2.5.3 | DB Test |

# 動作環境

2016.08.08 現在  

* JDK 8 (8u101)

# 動作方法

1. [ダウンロード](https://github.com/hosomi/develop-spring-template/archive/master.zip "ダウンロード")後、解凍し develop-spring-template-master ディレクトリ直下へコンソールで移動。
2. 「 gradlew 」 を実行（ Windows の方は gradlew.bat をダブルクリックでも可）。
3. ブラウザを立ち上げて 「 http://localhost:8080 」 にアクセス。  
**※終了時は強制終了させてください。**

# 認証情報

| ログインID       | パスワード  |
| ------------- | ----- |
| test | testpass |

**※SpringSecurity  のロールはありません。**

# etc:
## Heroku
こちらは実験的です、予告なく停止する場合があります。  
ローカルで実行しなくてもサンプルが確認できます。  

https://springboottemplate.herokuapp.com/  
※ push 時に自動ビルドされますので、タイミング次第では利用できません。  
※ めったにないかもしれませんが、heroku 自体がダウンしている可能性もあります。(https://status.heroku.com/)

## CI サービス
無料でどこまで利用できるか検証用に利用しています。  
（ビルドが目的で Gradle が利用できる前提となります。）  
現在検証中は下記サービスです。  

* Travis CI (https://travis-ci.org/)
* CircleCI (https://circleci.com/)
* Wercker (http://wercker.com/)
