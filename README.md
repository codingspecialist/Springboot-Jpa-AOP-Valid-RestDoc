# 스프링 부트 블로그 만들기

## 1. 개발 환경
* Springboot
* JPA
* MySQL
* AOP
* Rest-Docs
* adoc
* Lombok

## 2. 요구사항(기능)
* 상품등록
* 상품수정
* 상품전체조회
* 상품상세조회

## 3. 테이블
```sql
create user 'binding'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO 'binding'@'%';
create database binding;
use binding;
```