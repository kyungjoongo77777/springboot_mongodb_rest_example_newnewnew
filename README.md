# CRUD_Login_board_lemonade

## 백엔드 (springboot, mongodb , rest-api)

프로젝트는 루트 폴더에 위치하며, Spring Boot, MongoDB, REST API로 구성되어 있습니다.

### 실행 방법

1. 백엔드 실행은 `src/main/java/kyungjoon.lemon.crud_login12345/SpringbootMainApplication.java` 파일을 찾아 어플리케이션을 실행합니다.
2. 백엔드는 8080 포트로 실행됩니다.

## 클라이언트 (nuxt, mobx , vuejs)

클라이언트는 `/front_new` 폴더 내에 프로젝트가 위치하고 있습니다.

### 구조

- `/front_new/service`: 비지니스 로직을 처리합니다. 뷰와 비지니스 로직이 분리되어 있습니다.
- `/front_new/page`: 화면을 담당합니다.
- `/front_new/middleware`: 인증 처리를 담당하는 미들웨어입니다. 인증이 안되어 있으면 `board` 페이지에 접근할 수 없습니다.
- `/front_new/components`: 모듈화된 컴포넌트를 분리하여 구성되어 있습니다.
-  `/front_new/components`: 상수 ( 백엔드의 엔드포인드 기술, 차후에 .env로 변경 처리 필요 )

### 실행 방법

프로젝트 루트에서 아래 명령어로 클라이언트를 실행합니다.

```bash
cd front_new
npm install
npm start
```

### Docker 이미지 및 컨테이너
```bash
클라이언트, 백엔드 
Docker 이미지로 빌드 및 실행할 수 있습니다. 
각각의 프로젝트 루트 디렉토리에 Dockerfile을 참조하여 이미지 빌드후에 컨테이너 실행

### Docker 이미지 빌드 (프론트엔드 예시 ,백엔드도 동일)
docker build -t crud_login_frontend ./front_new

### Docker 컨테이너 실행 (프론트엔드 예시,백엔드도 동일)
docker run -p 3000:3000 crud_login_frontend
```

### 실행 가능한 데모 url : https://frontnew-sz2h574dlq-du.a.run.app/
 
 
