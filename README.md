# 🛡️ MiniIAM Docs – Spring Security 기반 CSRF 실습 프로젝트

MiniIAM Docs는 Spring Security 기반으로 구현된  
문서 관리 웹 애플리케이션입니다.  
이 프로젝트는 **CSRF (Cross Site Request Forgery)** 공격이  
어떻게 일어나고, 어떻게 방어할 수 있는지를 직접 실습하고 학습하기 위해 제작되었습니다.

---

## 📌 실습 개요

- 사용자는 로그인 후 문서를 생성하고 관리할 수 있습니다.
- 공격자는 이메일을 통해 **이벤트로 위장한 링크**를 전달합니다.
- 사용자가 해당 링크를 클릭하면, **자동으로 문서 삭제 요청이 발생**합니다.
- CSRF 방어 설정이 없을 경우 공격이 성공하고,  
  **Spring Security의 기본 설정으로 이를 차단할 수 있음을 검증합니다.**

---

## ▶️ 실행 방법

### 1. PostgreSQL 실행 (Docker 사용)

```bash
docker-compose -f docker/docker-compose.yml up -d
```

### 2. 애플리케이션 실행

```bash
./gradlew bootRun
```

앱은 기본적으로 `http://localhost:8080` 에서 실행됩니다.

---

## 🧪 테스트 시나리오

### ✅ 정상 흐름 확인

- `/register`에서 회원가입
- `/login` 후 `내 문서` 페이지 접속
- 문서를 작성하고 저장 여부 확인

### ❌ CSRF 공격 시나리오 (방어 미적용 상태)

- 브라우저에서 `script/csrf-invite.html` 열기
- "이벤트 참여 중입니다..." 메시지 출력 후
- 사용자의 문서가 백그라운드에서 자동으로 삭제됨

### ✅ CSRF 방어 적용 후

- `SecurityConfig.kt`에서 `csrf().enable()` 혹은 기본 설정 유지
- 같은 공격 페이지에 접속해도
- 요청은 `403 Forbidden`으로 차단되어 데이터가 보호됨

---

## ✉️ 이메일 시뮬레이션

```bash
python script/send_email.py
python script/send_ransom_email.py
```

> 이메일 전송을 위해 Gmail 등의 SMTP 정보를 스크립트 내부에 설정해야 합니다.

---

## 📄 블로그 포스트

- [kerryKim - 나는 왜 내 문서를 잃었는가: Spring Security와 함께한 CSRF 공격 & 방어 실습기](https://velog.io/@carrykim/%EB%82%98%EB%8A%94-%EC%99%9C-%EB%82%B4-%EB%AC%B8%EC%84%9C%EB%A5%BC-%EC%9E%83%EC%97%88%EB%8A%94%EA%B0%80-Spring-Security%EC%99%80-%ED%95%A8%EA%BB%98%ED%95%9C-CSRF-%EA%B3%B5%EA%B2%A9-%EB%B0%A9%EC%96%B4-%EC%8B%A4%EC%8A%B5%EA%B8%B0)
