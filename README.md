# 🗓️ Schedule Develop

일정 관리 Develop API 서버입니다. <br>
Java와 Spring Boot를 활용하여 일정 생성, 조회, 수정, 삭제 기능을 구현했습니다.<br>
댓글 작성 및 댓글 포함 조회 기능도 포함되어 있습니다.
유저 관리 기능도 포함되어 있습니다. (세션 로그인)

---

## 🧩 ERD (Entity Relationship Diagram)

<img width="510" height="506" alt="Screenshot 2025-08-13 at 6 48 51 PM" src="https://github.com/user-attachments/assets/cb55ed34-a3f8-4a54-9bb5-cbd6b5b7dcf8" />


---

## 📌 프로젝트 개요
프로젝트 목적:
RESTful API 개발 및 서버-DB 연동 이해를 바탕으로 백엔드 시스템 구축 능력 향상

주요 학습 목표:<br>
- 3 Layer Architecture 설계<br>
- JPA를 활용한 데이터베이스 연동<br>
- HTTP 요청/응답 처리 (ResponseEntity)<br>
- API 명세에 맞춘 CRUD 구현<br>
- Postman을 활용한 API 테스트<br>

---

## 🛠 기술 스택
Language: Java 17<br>
Framework: Spring Boot<br>
ORM: Spring Data JPA<br>
IDE: IntelliJ IDEA<br>
Build Tool: Gradle<br>
Database: MySQL

---

## ✅ 주요 기능
필수 기능
- 일정 생성 CRUD<br>
- 유저 CRUD<br>
- 회원가입<br>
- 로그인(인증) - Session을 활용한 로그인 기능 구현<br>
  @Configuration를 이용한 로그인 Filter<br>

도전 기능
- 다양한 예외 처리 적용<br>
  @RestControllerAdvice를 이용한 글로벌 예외처리 적용<br>
  @Valid를 활용한 예외사항 적용<br>
- 비밀번호 암호화<br>
  BCrypt를 사용한 암호화<br>
- 댓글 CRUD<br>
  단방향 연관관계<br>
- 일정 페이징 조회<br>
  Spring Data Jpa에서 지원하는 페이지네이션<br>
  스케줄 리스트 조회에 댓글 개수 추가 (JPQL 사용)<br>


---

## 📁 패키지 구조(요약)

<pre> <code>
# org.example.scheduleapi
├── comments
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── common
│   ├── config
│   ├── entity
│   ├── exception
│   ├── filter
│   └── utils
├── schedule
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── users
│   ├── auth
│   ├── config
│   ├── controller
│   ├── dto
│   ├── repository
│   └── service
└── ScheduleApiApplication
</code> </pre>

---
## 🧪 API 실행 및 테스트
- Postman을 활용해 API 테스트를 진행합니다.<br>
- 각 요청에 대한 응답은 @ResponseStatus 통해 처리됩니다.<br>
- API는 명세에 따라 설계되었으며, 요구사항에 맞춰 동작합니다.<br>

---

## 🛠️ API 명세서

### 🏷️ 공통 정보
Base URL: http://localhost:8080<br>
Content-Type: application/json<br>
인증: 일부 API는 로그인 필요 (세션 기반)<br>

### 👤 회원(User) 관련
| 기능                | Method | URL                        | 요청 Body                                                                                                      | 응답 예시                                                                                                                                      | 응답 코드 |
| ----------------- | ------ | -------------------------- | ------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------ | ----- |
| 회원가입              | POST   | `/users/signup`            | `{ "username": "sero", "email": "test@example.com", "password": "Password1" }`<br>비밀번호: 8자 이상, 대문자·숫자 포함 | `{ "id": 1, "username": "sero", "email": "test@example.com", "createdAt": "2025-08-13T09:00:00", "updatedAt": "2025-08-13T09:00:00" }` | 201   |
| 전체 유저 조회 / 유저명 검색 | GET    | `/users?username=sero` (username optional) | -                                                                                                            | `[ { "id": 1, "username": "sero", "email": "test@example.com", "createdAt": "...", "updatedAt": "..." } ]`                             | 200   |
| 유저 단건 조회          | GET    | `/users/{id}`              | -                                                                                                            | `{ "id": 1, "username": "sero", "email": "test@example.com", "createdAt": "...", "updatedAt": "..." }`                                 | 200   |
| 비밀번호 변경           | PATCH  | `/users/{id}`              | `{ "oldPassword": "Password1", "newPassword": "NewPass2" }`<br>새 비밀번호 규칙 동일                                  | -                                                                                                                                          | 204   |
| 유저 삭제             | DELETE | `/users/{id}`              | -                                                                                                            | -                                                                                                                                          | 204   |

### 🔑 인증(Auth) 관련
|기능                | Method | URL            | 요청 Body                                               |응답 예시  |응답 코드 |
| ----------------- | ------ | -------------- | --------------------------------------------------------- |------|-----|
|로그인 | POST   | `/auth/login`  |  `{ "email": "user@example.com", "password": "Passw0rd" }` | -|200|
|로그아웃 | POST   | `/auth/logout` |  -                                                         |-|200|


### 📅 일정(Schedule) 관련
| 기능                 | Method | URL                                          | 요청 Body                                             | 응답 예시                                                                                                                                                                                                                                                     | 응답 코드 |
| ------------------ | ------ | -------------------------------------------- | --------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----- |
| 일정 생성              | POST   | `/schedules`                                 | `{ "title": "회의", "contents": "오전 10시 팀 회의 진행" }`   | `{ "id": 1, "user": { "id": 10, "username": "sero" }, "title": "회의", "contents": "오전 10시 팀 회의 진행", "createdAt": "...", "updatedAt": "...", "commentCount": 0 }`                                                                                       | 201   |
| 일정 전체 조회 / 작성자명 검색 | GET    | `/schedules?username=sero` (username optional) | -                                                   | `{ "content": [ { "id": 1, "user": { "id": 10, "username": "sero" }, "title": "회의", "contents": "오전 10시 팀 회의 진행", "createdAt": "...", "updatedAt": "...", "commentCount": 2 } ], "totalElements": 1, "totalPages": 1, "last": true }`                 | 200   |
| 일정 개별 조회           | GET    | `/schedules/{id}`                            | -                                                   | `{ "id": 1, "userId": 10, "username": "sero", "title": "회의", "contents": "오전 10시 팀 회의 진행", "createdAt": "...", "updatedAt": "...", "comments": [ { "id": 101, "user": { "id": 11, "username": "zero" }, "contents": "참석합니다", "createdAt": "..." } ] }` | 200   |
| 일정 수정              | PATCH  | `/schedules/{id}`                            | `{ "title": "수정된 회의", "contents": "시간 변경: 오후 2시" }` 둘 중 하나만 적어도 변경 가능 | `{ "id": 1, "user": { "id": 10, "username": "sero" }, "title": "수정된 회의", "contents": "시간 변경: 오후 2시", "createdAt": "...", "updatedAt": "...", "commentCount": 2 }`                                                                                     | 200   |
| 일정 삭제              | DELETE | `/schedules/{id}`                            | -                                                   | -                                                                                                                                                                                                                                                         | 204   |

### 💬 댓글(Comment) 관련
| 기능       | Method | URL                               | 요청 Body                    | 응답 예시                                                                                                                                   | 응답 코드 |
| -------- | ------ | --------------------------------- | -------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- | ----- |
| 댓글 생성    | POST   | `/schedule/{scheduleId}/comments` | `{ "contents": "좋아요!" }`   | `{ "id": 1, "user": { "id": 11, "username": "zero" }, "scheduleId": 5, "contents": "좋아요!", "createdAt": "...", "updatedAt": "..." }`     | 201   |
| 댓글 목록 조회 | GET    | `/schedule/{scheduleId}/comments` | -                          | `[ { "id": 1, "user": { "id": 11, "username": "zero" }, "scheduleId": 5, "contents": "좋아요!", "createdAt": "...", "updatedAt": "..." } ]` | 200   |
| 댓글 수정    | PUT    | `/comments/{commentId}`           | `{ "contents": "수정된 댓글" }` | `{ "id": 1, "user": { "id": 11, "username": "zero" }, "scheduleId": 5, "contents": "수정된 댓글", "createdAt": "...", "updatedAt": "..." }`   | 200   |
| 댓글 삭제    | DELETE | `/comments/{commentId}`           | -                          | -                                                                                                                                       | 204   |


---

## 🔄 트러블 슈팅
https://www.notion.so/Develop-24955d5f00a080fc9a9ff1a28b1e9c7a?source=copy_link


