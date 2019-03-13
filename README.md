# API Server

---

* 간단하게 API SERVER를 만들어 보았습니다.
* build 는 maven을 통해서 구현하였습니다.
* maven 프로젝트로 import하시면 됩니다.
* native에서 sessionid를 넘겨주는 방식을 사용하여 device_token 방식이 아닌 session 방식으로 구현 하였습니다.

---

##서버구동방법

1. h2서버 설치

    - [h2다운로드 링크](http://www.h2database.com/html/download.html)
2. 쿼리 작성

    - 파일 경로 확인 api\src\main\resources\file\test.sql를 이용하여 쿼리 수행
3. api 호출 확인

---