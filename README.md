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

## 데이터 베이스 테이블 구조(ERD)

![image](https://github.com/gunhee93/wanted-pre-onboarding-backend/assets/123151812/babbbbca-e71c-4463-9495-e632cef12c2c)

## 데모 영상

## 구현 방법 및 이유
- 
- jpa 의 fetch join 을 사용해 게시글 목록을 가져올 때 쿼리를 최소한으로 나가게 설계 
- Exception Manager 를 사용해 직관적인 error code, success code 생성
- Redis 를 사용해 jwt token 요청 시 DB 에 접근하지 않고 메모리 안에서 빠르고 가볍게 주고 받음

## API 명세
- 애플리케이션 실행 후 http://localhost:8080/swagger-ui.html# 로 접속
