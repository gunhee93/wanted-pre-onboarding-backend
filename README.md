# wanted-pre-onboarding-backend

## 참가자
|이름|github|
|---|---|
|조건희|[조건희 github](https://github.com/gunhee93)|

## 사용 기술 
Environment
<div>
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

Development
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
</div>

Infra
<div>
  <img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
  <img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
  <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
</div>

## 애플리케이션 실행 방법
- 개발도구 : intelij Redis Mysql
- redis server 실행
- repository clon 후 build.gradle open
- application.yml
- spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/pre-back?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: Whrjsgml199#


  redis:
    host: 127.0.0.1
    port: 6379

  jpa:
    database: mysql
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect

    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        # show_sql: true
        format_sql: true
        default_batch_fetch_size: 100

jwt:
  secret: 7xc6zv7867sdva7fywefe78sdyf78asyf78aseyf78ysdf8xy8v7yv78ayvd87dfyvadfbdybgWEJF32HJRK3H3JK2KJ;

## 데이터 베이스 테이블 구조(ERD)

![image](https://github.com/gunhee93/wanted-pre-onboarding-backend/assets/123151812/babbbbca-e71c-4463-9495-e632cef12c2c)


## 구현 방법 및 이유
- jpa 의 fetch join 을 사용해 게시글 목록을 가져올 때 쿼리를 최소한으로 나가게 설계 
- Exception Manager 를 사용해 직관적인 error code, success code 생성
- Redis 를 사용해 jwt token 요청 시 DB 에 접근하지 않고 메모리 안에서 빠르고 가볍게 주고 받음

## API 명세
- 애플리케이션 실행 후 http://localhost:8080/swagger-ui.html# 로 접속
