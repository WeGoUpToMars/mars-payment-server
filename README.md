# mars-payment-server

최초 생성 : 2022.11.16
최종 업데이트 : 2022.11.26

## Sequence Diagram

![img.png](img/img.png)

1. 클라이언트에서 카드 정보를 PG 결제 창에 등록한다 (생략)
2. 카드 유효성 체크 결과를 Backend 서버로 redirect

3. (성공 시) PG 서버에 인증용 토큰 발급 요청
   <br/>(실패 시) 클라이언트로 실패 화면 응답 return

4. 결재 성공 화면 응답 return

## ERD

![erd](img/ERD_221123.png)

## 로컬 환경 구축

- `./docker/mysql/conf/db.cnf`, `/docker/mysql/conf/sql/init-master.sql` 파일 생성

> db.conf

```
[server]

[mysqld]
server-id=95
log-bin
character-set-server=utf8mb4
collation-server=utf8mb4_bin
lower_case_table_names=1
default-time-zone='+00:00'
max_connections=1000
```

> init-master.sql

```sql
CREATE DATABASE mars;
create user 'test'@'%' identified by 'test';
grant all privileges on mars.* to 'test'@'%';
flush privileges;

```

- `startup.sh` 실행
