Spring Boot + Gradle template
=============

[![Build Status](https://travis-ci.org/hosomi/develop-spring-template.svg?branch=master)](https://travis-ci.org/hosomi/develop-spring-template)
[![build status](https://circleci.com/gh/hosomi/develop-spring-template.svg?style=shield&circle-token=9a9efeb27aabd4d1173951957f233d05f246d6c0)](https://circleci.com/gh/hosomi/develop-spring-template)
[![wercker status](https://app.wercker.com/status/46670404016ddfb8110dd3023bed1473/s/master "wercker status")](https://app.wercker.com/project/byKey/46670404016ddfb8110dd3023bed1473)


Spring Boot , Gradle で開発する際のテンプレートです。  

現在提供している機能：
* SpringSecurity による独自テーブルを利用したログイン処理(パスワードはハッシュ化)。
* トランザクション管理（システムエラー時ロールバック）
* 共通のログ出力処理（操作ログ、システムエラーログ、SQL（trace,debug））
* テストコードサンプル
  * SpringSecurity
  * コントローラに対するテスト
* コントロールサンプル
  * DateTimePicker : 日付選択（日付のみ、時刻のみ、両方）
  * typeahead : サジェスト機能のようなもの
  * Modal(Bootstrap) ※サブミット非対応
  * bootstrap-dialog : アラート表示
  * bootstrap-switch : スイッチ(トグルスイッチ)
  * bootstrap-tagsinput : タグ入力（typeahead との組み合わせのサンプル）

* マスタサンプル(ORM:Mybatis)
  * User : バリデーション、カスタムバリデーションサンプル
  * Company : Mybatis XML サンプル

* etc
  * 入力値検証サンプル : 独自アノテーション（単独、相関チェック）


# 依存関係  

## Core(Spring Boot 1.4.1.RELEASE):

| 対象                                                      | バージョン          | 内容  |
| --------------------------------------------------------- | ----------------- |:------------------------------------------- |
| org.springframework.boot:spring-boot-starter-data-jpa     | ※1               | JPA                                          |  
| org.springframework.boot:spring-boot-starter-security     | ※1               | Spring Security                              |  
| org.springframework.boot:spring-boot-starter-web          | ※1               | Spring MVC                                   |  
| org.springframework.boot:spring-boot-starter-thymeleaf    | 3.0.1.RELEASE    | Template engine                              |  
| org.springframework.boot:spring-boot-starter-log4j2       | ※1               | Logging                                      |  
| org.thymeleaf.extras:thymeleaf-extras-springsecurity4     | ※1               | Template engine Spring Security 拡張         |  
| org.thymeleaf.extras:thymeleaf-extras-conditionalcomments | 2.1.2.RELEASE    | Template engine IE Conditional comments 拡張 |  
| org.mybatis.spring.boot:mybatis-spring-boot-starter       | 1.1.1            | Spring Boot <-> ORM                          |  

※1 Core のバージョンに依存。

## Webjars:

http://www.webjars.org/  
（※非力な PC だと表示に時間がかかるので注意）  

| 対象                              | バージョン  | 内容または URL                                |
| --------------------------------- | --------- |:------------------------------------------- |
| webjars-locator                   | 0.32      | WebJar バージョン番号管理（隠蔽で利用）  |
| jquery                            | 2.2.4     | jQuery 2.2.4 (※2) |
| bootstrap                         | 3.3.7-1   | Bootstrap 3.3.7 |
| html5shiv                         | 3.7.3     | Bootstrap の IE8 対応用 (HTML5) ※後々削除予定 |
| respond.js                        | 1.4.2     | Bootstrap の IE8 対応用(レスポンシブ) ※後々削除予定 |
| font-awesome                      | 4.6.3     | Font Awesome |
| Eonasdan-bootstrap-datetimepicker | 4.17.37-1 | bootstrap-DateTimePicker(Bootstrap,jQuery, moment.js(※3) 必須) |
| sticky-footer                     | 0.1.4     | フッター表示 |
| bootswatch                        | 3.3.2     | Bootstrap のテーマ拡張(Lumen を適用) bootswatch 3.3.7 は依存性に問題が発生 |
| typeaheadjs                       | 0.11.1    | https://twitter.github.io/typeahead.js/  |
| hoganjs                           | 3.0.2     | http://twitter.github.io/hogan.js/ typeahead 内部で html テンプレートエンジンとして利用。 |
| bootstrap-dialog                  | 1.34.6    | http://nakupanda.github.io/bootstrap3-dialog/ JavaScript のみで生成可能なダイアログ |
| Bootstrap-3-Typeahead             | 3.1.1     | https://github.com/bassjobsen/Bootstrap-3-Typeahead bootstrap-tagsinput で利用、 typeaheadjs と共存可能 |

※2 jQuery を必要とするモジュールが 3 系 に対応していないものが多くフロント全般に影響する為、 2 系にした。  
※3 Eonasdan-bootstrap-datetimepicker の依存関係として moment.js の webjars がダウンロードされます。（バージョン指定が必要な場合は、個別に定義が必要です。）  

## ext:

| 対象        | 内容  |
| ------------- | :----- |
| ext['thymeleaf.version'] = '3.0.1.RELEASE' | Thymeleaf version 3 を利用する設定、Spring Boot 1.4.0.RELEASE のデフォルトは version2 がデフォルト |
| ext['thymeleaf-layout-dialect.version'] = '2.0.3' | Thymeleaf version 3 からは dialect の指定が必要。 |

## etc:

| 対象                              | バージョン  | 内容または URL                                |
| --------------------------------- | --------- |:------------------------------------------- |
| org.springframework.boot:spring-boot-starter-tomcat | ※1  | Tomcat ランタイム |
| webapp-runner | 8.5.5.0 | ランチャーアプリケーション Heroku で war を起動させるために利用 |
| com.h2database:h2 | ※1 | H2 Database ランタイム |
| org.springframework.boot:spring-boot-starter-test | ※1  | SpringBoot test |
| org.springframework.security:spring-security-test | ※1  | SpringBoot Security |
| org.dbunit:dbunit | 2.5.3 | DB Test |

## SOAP:
| 対象                              | バージョン  | 内容または URL                                |
| --------------------------------- | --------- |:------------------------------------------- |
| org.springframework.boot:spring-boot-starter-web-services | ※1  | SpringBoot SOAP |
| wsdl4j | 1.6.3  | WSDL |

### SOAP client （cURL example）:

```shell
curl --header "content-type: text/xml" -d @src/test/resources/ws/requestDpt.xml http://localhost:8080/ws/dpt
```

or (Heroku)

```shell
curl --header "content-type: text/xml" -d @src/test/resources/ws/requestDpt.xml http://springboottemplate.herokuapp.com/ws/dpt
```

## REST:
| 対象                              | バージョン  | 内容または URL                                |
| --------------------------------- | --------- |:------------------------------------------- |
| jackson-dataformat-xml | 2.8.3  | XML |

### JSON:

http://localhost:8080/rest/dpt/json/1000  
or (Heroku)  
http://springboottemplate.herokuapp.com/rest/dpt/json/1000

### XML:

http://localhost:8080/rest/dpt/xml/1000  
or (Heroku)  
http://springboottemplate.herokuapp.com/rest/dpt/xml/1000

## CDN

http://www.jsdelivr.com  
Thymeleaf の ローカル（AP 経由しない）での表示の際で WebJars と同じバージョンを調査する場合に利用。


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
![Heroku](http://heroku-badge.herokuapp.com/?app=angularjs-crypto&style=flat)  

こちらは実験的です、予告なく停止する場合があります。  
ローカルで実行しなくてもサンプルが確認できます。  



https://springboottemplate.herokuapp.com/  
※ Application Error が表示されている場合、　Free dyno の制限になっている可能性があります。　　　
Heroku 無料プラン(Free dyno) を利用しています。　　　
（24 時間の内、6 時間は Sleep 状態にしなければならないという制限があります。）　　　
https://blog.heroku.com/new-dyno-types-public-beta#hobby-and-free-dynos

## CI サービス
無料でどこまで利用できるか検証用に利用しています。  
（ビルドが目的で Gradle が利用できる前提となります。）  
現在検証中は下記サービスです。  

* Travis CI (https://travis-ci.org/)
* CircleCI (https://circleci.com/)
* Wercker (http://wercker.com/)

## Code Coverage
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/01cdd8027340415da850355603fea894)](https://www.codacy.com/app/kiyotake-hosomi/develop-spring-template?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hosomi/develop-spring-template&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/01cdd8027340415da850355603fea894)](https://www.codacy.com/app/hosomi/develop-spring-template?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hosomi/develop-spring-template&amp;utm_campaign=Badge_Coverage)

Codacy の静的解析は Codacy のみで結果を確認できます。  
Coverage は Codacy から確認する場合、JaCoCo と JaCoCo のCoverage 結果を送信する仕組みが必要になります（Circle CI からアップロードする仕組みにしています）。  
