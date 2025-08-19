# 🎮 온라인 퀴즈/게임 플랫폼

> ✅ 본 프로젝트는 역할 기반 권한 제어, RESTful API 설계, 동시성 처리 등 실무 중심 기능을 반영하여 설계되었습니다.
> 

<br>

## 📝 **프로젝트 소개**

- **프로젝트 기간**
    - 2025.06.30 ~ 2025.07.31
- **서비스 이름**
    - 뇌이싱
- **서비스 로고**

  
    <img width="252" height="125" alt="9EF49DD5-A045-4C18-8C29-6592F3190D2C" src="https://github.com/user-attachments/assets/48a279ad-54e2-4885-8275-898d411b0e70" />
    
- **목표**
    - 실시간 퀴즈 진행과 경쟁을 통해 사용자들에게 재미와 학습을 동시에 제공
    - 빠른 배포와 운영을 통해 실제 사용자 확보
- **특징**
    - 동시성 처리, 실시간 데이터 전송, 높은 접근성과 확장성
- **대상 사용자층**
    - **친구들과 퀴즈 대결**을 즐기고 싶은 일반 사용자
    - **동아리/스터디 그룹에서 지식 게임**으로 활동하고 싶은 그룹 사용자
    - **학원/교사가 수업 보조 도구로 활용**하고 싶은 교육 목적 사용자

<br>

## 📝 프로젝트 기획

- 기존 퀴즈 앱들은 주로 **싱글플레이 형식**이거나, **실시간 상호작용이 제한적**입니다.
- 그래서 친구 또는 게임 상 유저들과 **실시간으로 함께 즐길 수 있는 퀴즈 플랫폼을 기획**했습니다.
- **사용자들의 자유로운 문제 생성, 채팅을 통한 퀴즈 참여**를 통해 다양한 주제의 퀴즈를 풀 수 있게 하고, 점수제, 랭킹제를 통해 지속적인 참여를 유도하여 재미와 몰입도를 높이도록 했습니다.
- **채팅을 통해 유저 간의 상호작용**도 활발하게 이뤄질 수 있습니다.
- 추후에는 퀴즈 좋아요 기능, 신고 기능을 도입하여 더 몰입하며 즐길 수 있도록 확장할 예정입니다.

<br>

## 🧑🏻‍🏫 프로젝트 컨셉

- Kakao 소셜 로그인을 통한 높은 접근성
- 다양한 카테고리의 퀴즈 게임을 제공하는 높은 확장성
- 멀티 플레이, 제한 시간, 랭킹 시스템을 통한 경쟁 유도
- 네트워크 끊김, 새로고침 등의 상황에도 제공되는 높은 안정성



### 📌 주요 기능

- **OAuth 2.0** 기반 회원 기능
- **SSE**를 활용한 방 목록 실시간 업데이트
- **웹소켓**을 활용한 데이터 전송
- **Redis**를 활용한 실시간 랭킹 조회

### 🔐 권한 분리 (Spring Security 기반)

- **관리자(Admin)**: 유저 정보 조회 및 퀴즈 수정, 삭제
- **사용자(User)**: 게임 참여 및 퀴즈 CRUD, 실시간 랭킹 조회



### 🕹️ 게임 방 조회 및 생성, 입장

- 로비에서 게임 방 목록과 방 정보 (퀴즈, 방 제목, 정원 등) 실시간 조회
- 로비에서 방 정보 (방 제목, 정원, 비밀 방 여부) 입력 후 방 생성
- 원하는 게임 방 입장



### 🧩 퀴즈 조회/생성 및 관리

- 생성된 퀴즈 검색 및 조회 가능
- 퀴즈 제목, 설명, 썸네일 및 각 퀴즈 당 최대 60문제 생성
- 자신이 만든 퀴즈 수정 및 삭제 가능



### 🎯 게임 대기

- 전원
    - 실시간 채팅 가능
    - 실시간 참가자 목록, 레디 여부, 게임 세팅 변경 확인 가능
    - 게임 방 나가기
- 방장
    - 게임 시작 전 퀴즈, 문제 수, 제한 시간 변경
    - 퀴즈 변경 시, 참가자 레디 자동 해제
    - 모든 참가자 레디 시, 게임 시작 가능
- 참가자
    - 게임 준비 상태에 따라 레디 상태 변경



### 🎮 게임 진행

- 선택된 퀴즈, 제한 시간, 문제 수로 게임 진행
- 채팅으로 가장 먼저 정답을 맞힌 플레이어가 점수 획득
    - 정답일 때, 즉시 실시간 랭킹 갱신
- 제한시간 내 정답자가 없는 경우 정답 공개 및 다음 라운드 진행
- 채팅창에 정답자 알림 문구 표시
- 게임 진행 중, 타이머, 실시간 랭킹 표시



### 🔚 게임 종료

- 맞힌 개수에 따라 차등 점수 지급 후, 최종 결과 알림
    - 1등은 1승을 가져가게 되고, 나머지는 패 처리
    - 뇌이싱 전체 랭킹에 게임 결과 반영
- 게임 도중 연결이 끊긴 유저들은 점수 반영 X

 

### ⛓️‍💥 연결 끊김 및 재접속 처리

- 게임 방 접속 후 새로고침시 (연결 끊김 → 재연결)
    - 대기중 : 연결 유지
    - 게임중 : 다음 라운드부터 참가 가능
- 하트비트로 주기적인 웹소켓 연결 상태 유지 확인
- 연결 끊김 후 5초 이내 재연결 실패시
    - 대기중 : 퇴장 처리
    - 게임중 : 랭킹 유지, 게임 참여 불가

 
<br>


## ⚙ 기술 스택

### 언어

<table>
<tr>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Java.png?raw=true" width="80" alt="Java"/><br/>
<sub><b>Java 21</b></sub>
</td>
</tr>
</table>

### 프레임 워크 및 라이브러리

<table>
<tr>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/SpringBoot.png?raw=true" width="80" alt="Spring Boot"/><br/>
<sub><b></b></sub>
</td>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/SpringSecurity.png?raw=true" width="80" alt="Spring Security"/><br/>
<sub><b></b></sub>
</td>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/SpringDataJPA.png?raw=true" width="80" alt="Spring Data JPA"/><br/>
<sub><b></b></sub>
</td>
</tr>
</table>

### 데이터베이스

<table>
<tr>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Mysql.png?raw=true" width="80" alt="MySQL"/><br/>
<sub><b></b></sub>
</td>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Redis.png?raw=true" width="80" alt="Redis"/><br/>
<sub><b></b></sub>
</td>
</tr>
</table>

### 인프라

<table>
<tr>
<td align="center">
<img src="https://github.com/user-attachments/assets/9d571dcc-75fb-4966-b8cb-e17278e78aeb" width="80" alt="GitHub Action"/><br/>
<sub><b>Github Actions</b></sub>
</td>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Docker.png?raw=true" width="80" alt="Docker"/><br/>
<sub><b></b></sub>
</td>
</tr>
</table>

### 협업 도구

<table>
<tr>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Github.png?raw=true" width="80" alt="GitHub"/><br/>
<sub><b></b></sub>
</td>
<td align="center">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Notion.png?raw=true" width="80" alt="Notion"/><br/>
<sub><b></b></sub>
</td>
</tr>
</table>

<br />

## 💁‍♂️ 프로젝트 팀원

<table>
<thead>
<tr>
<th align="center">PO</th>
<th align="center">BE 팀장</th>
<th align="center">팀원</th>
<th align="center">팀원</th>
<th align="center">팀원</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center">
<a href="https://github.com/silver-eunjoo">
<img src="https://github.com/silver-eunjoo.png" width="120" height="120" alt="이은주"/><br/>
<sub><b>이은주</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/dlsrks1021">
<img src="https://github.com/dlsrks1021.png" width="120" height="120" alt="김경찬"/><br/>
<sub><b>김경찬</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/sehee123">
<img src="https://github.com/sehee123.png" width="120" height="120" alt="황세희"/><br/>
<sub><b>황세희</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/jiwon1217">
<img src="https://github.com/jiwon1217.png" width="120" height="120" alt="곽지원"/><br/>
<sub><b>곽지원</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/LimKangHyun">
<img src="https://github.com/LimKangHyun.png" width="120" height="120" alt="임강현"/><br/>
<sub><b>임강현</b></sub>
</a>
</td>
</tr>
</tbody>
</table>

## 🛠️ 역할 분담

| 이름 | 담당 기능 |
| --- | --- |
| **이은주 (PO)** | - 퀴즈 API 개발 <br>- 웹소켓 기반 게임 진행 및 종료 구현 <br>- 게임 진행 중 채팅 동시성 구현 |
| **김경찬 (BE 팀장)** | - 전체 랭킹 구현 <br>- 시스템 구조 설계<br>- CI/CD   |
| **곽지원 (BE)** | - OAuth 2.0 기반 회원 기능 <br>- 마이페이지 <br>- 관리자 기능 |
| **임강현 (BE)** | - SSE 기반 실시간 방 목록 구현 <br>-  동시성 구현  |
| **황세희 (BE)** | - 웹소켓 기반 방 생성 입,퇴장 / 채팅 구현<br>- 재연결 로직 |

<br>

## 🛠️ 프로젝트 아키텍쳐

## 시스템 구성도

<img width="757" height="437" alt="뇌이싱_시스템구성도" src="https://github.com/user-attachments/assets/ced27dfb-fe9c-49cc-8cff-7abe4e781f5f" />

### Traefik

- HTTPS 인증서를 자동으로 갱신하고, 도메인별 트래픽을 분산하기 위해 사용

### Watchtower

- 컨테이너에 Label을 설정해 최신 버전 이미지를 자동으로 적용
- 백엔드 및 프론트엔드 컨테이너에만 Label을 설정하고, 기타 컨테이너는 추적 대상에서 제외

### Monitoring

- Prometheus로 백엔드 지표를 수집하고, Grafana 대시보드를 통해 시각적으로 확인 가능
- 현재는 Spring Actuator의 기본 지표를 수집 중이며, 추후 게임 환경 모니터링을 위한 지표를 추가할 예정

<br>

## ERD

<img width="393" height="360" alt="뇌이싱_erd" src="https://github.com/user-attachments/assets/38cc306f-8b86-48a7-a48c-d56ab43ca651" />


<br />
<br>


## 협업 방식

### 🛠️ 브랜치 전략

<img src="https://github.com/user-attachments/assets/0188c024-6c57-4fdb-a476-447fb64065c5" width="500">

1. **이슈 생성**
    - GitHub 이슈를 통해 작업 항목 정의
2. **브랜치 생성**
    - `dev` 브랜치에서 이슈별 작업 브랜치 생성
    - 브랜치 명명 규칙 예시: `feat/이슈번호`
3. **PR 및 코드 리뷰**
    - 작업 완료 후 Pull Request(PR) 생성
    - 팀원 간 코드 리뷰 진행
4. **Merge 및 브랜치 정리**
    - 리뷰 완료 후 `dev` 브랜치로 Merge
    - Merge 후 이슈 브랜치 삭제
    - `dev` 브랜치 최신 상태 유지


### 🧑‍💻 코딩 컨벤션

### Git 컨벤션

1. **Commit 메시지 형식**
    - [이모지][타입] 커밋 메시지
    - 예: `✨ feat:사용자 로그인 기능 구현`
2. **PR 제목 및 설명**
    - 제목: `[타입] 작업 내용 요약`
    - 본문: 작업 내용, 고려한 사항, 테스트 방법 등 기재
3. **Branch 명명 규칙**
    - `타입/이슈번호`
    - 예:`feat/15`
4. **Issue 제목 규칙**
    - `[타입] 작업 내용 요약`



### 코드 스타일

1. **스타일 가이드**
    - CI job을 추가하여 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) 자동 적용
2. **DTO 작성 기준**
    - 요청(Request) / 응답(Response) DTO 분리
    - Mapper Class를 두어 Entity → DTO 변환 로직 분리
3. **커스텀 예외 처리**
    - 클라이언트로 전달되는 예외는 커스텀 예외로 처리
4. **이름 규칙**
    - **클래스명**: 대문자로 시작, 명확한 의미 전달
        - 예: `UserService`, `OrderController`
    - **메서드명**: 동사 + 대상, camelCase 사용
        - 예: `createUser()`, `getOrderList()`
    - **변수명**: camelCase 사용, 명확하고 간결하게
        - 예: `userId`, `orderRequest`

<br>

## 🗂️ API

작성한 API는 아래에서 확인할 수 있습니다.

<details>
<summary> 👉🏻 API 바로보기 </summary>
<div markdown="1">

<img width="1016" height="821" alt="뇌이싱_api_명세서" src="https://github.com/user-attachments/assets/2b3eae26-1089-43e3-a6ac-873a12d03f8f" />

</div>
</details>

<br>

## 🤔 트러블 슈팅

### 1️⃣ **OAuth 리다이렉션**

**문제**

- https 가 적용된 후, 카카오 개발자 센터에 새로운 리다이렉션 URL을 추가
- **https가 적용된 새로운 URL을 추가했음에도, http로 리다이렉트 하는 문제가 발생**

**해결 [ [적용 코드](https://github.com/prgrms-web-devcourse-final-project/WEB5_7_F1_BE/blob/6a34772240d4c25f27cb846ceba22721ef2f9855/backend/src/main/resources/application.yml#L64) ]**

- 배포 환경에 리버스 프록시가 설정돼있으면 클라이언트의 요청을 제대로 인식하지 못해서 기본적으로 HTTP 주소로 리다이렉트한다는 것을 확인
- 백엔드 서버가 리버스 프록시 뒤에 있을 때는 프록시 헤더를 신뢰하도록 하는 설정이 필요
- **`forward-headers-strategy : native` 설정을 추가해서 문제 해결**

**배운 점**

- 개발자 도구(F12)를 활용한 디버깅의 중요성 로컬 환경과 배포 환경의 설정 정보를 분리하는 것의 필요성을 깨달음



### 2️⃣ **재연결**

**문제**

- 웹소켓이 연결 된 상태 (방 입장 완료) 에서 새로고침, 브라우저 닫기 등 비정상 끊김에 대한 처리
- 한명의 유저가 다른 게임방에 입장하는 것을 방지

**해결 [ [적용 코드](https://github.com/prgrms-web-devcourse-final-project/WEB5_7_F1_BE/blob/6a34772240d4c25f27cb846ceba22721ef2f9855/backend/src/main/java/io/f1/backend/domain/game/websocket/eventlistener/WebsocketEventListener.java#L32C1-L80C2)** **]** 

- disconnected 이벤트 리스너를 통해 사용자의 상태를 connected → disconnected 로 변경
- 5초간의 유예시간을 줘서 재연결 완료 되었으면 disconnected → connected 변경
- 5초 후에 재연결 실패 시 게임중/대기중을 구분하여 게임중일땐 disconnected 상태유지, 대기중일때는 퇴장처리
- 방 입장 로직시 userId에 현재 입장 시도한 roomId와 다른 roomId가 매핑되어있을 시 이전 방에서 Exit 처리

**배운 점** 

- 단순히 연결 유무만 판단해서는 안 되고, 유예 시간과 상태 전이에 따른 처리가 필요
- 세션 ID가 아닌 userId 기반으로 상태를 관리하고, 동일 유저의 중복 입장 제어 경험
- 정상 종료와 예외 종료를 구분하고, disconnect 상태에서 유예 시간 내 재접속 처리 등 다양한 케이스 고려 경험
- 새로고침, 브라우저 종료 등 클라이언트 단 이벤트가 서버에 어떤 영향을 미치는지 파악하고 대응



### 3️⃣ **서비스 순환참조**

**문제**

- **GameService ↔ RoomService ↔ TimerService 간의 의존성 순환 참조 발생**
- 채팅 시, RoomService가 호출되고, 정답 판별 등을 위해 GameService, TimerService를 참조
- 결과적으로 여러 서비스가 서로를 참조하면서 Spring Bean 생성 시 순환 참조 오류 발생

**해결 [ [적용 코드](https://github.com/prgrms-web-devcourse-final-project/WEB5_7_F1_BE/blob/6a34772240d4c25f27cb846ceba22721ef2f9855/backend/src/main/java/io/f1/backend/domain/game/app/GameService.java#L102C5-L180C6) ]**

- 의존성을 끊기 위한 도메인 이벤트 기반 구조로 전환
- chat 메서드를 ChatService로 분리
- ChatService (정답일 때) → 이벤트 발행 (GameCorrectAnswerEvent)
- TimerService (타임아웃일 때) → 이벤트 발행 (GameTimeoutEvent)
- 정답 인정, 타임 아웃과 같은 게임 주요 흐름은 GameService에서 @EventListener로 핸들링

**배운 점**

- 서비스 간 강한 결합은 구조적으로 위험하며 유지보수성과 테스트성 모두 저하됨
- Spring 이벤트 기반 설계로 순환 참조 해소뿐 아니라 관심사의 분리 측면에서도 도움이 됐음



### 4️⃣ **Race condition**

**문제**

- 게임 Start 버튼을 입력한 순간, 다른 사용자가 레디 버튼을 누르는 경우, 플레이어의 준비상태가 false 인채로 게임이 시작되는 문제 발생
- 게임방 입장 시 인원초과 로직, 퇴장 시 방장 변경 로직 → 초과 인원 발생, 방장 변경/방 삭제 불가
- 사용자가 채팅으로 정답을 입력한 순간, 타임아웃 타이머가 거의 동시에 동작 → 정답을 맞췄는데도 시간 초과로 처리되는 현상 발생

**해결 [ 분산락 [적용 코드](https://github.com/prgrms-web-devcourse-final-project/WEB5_7_F1_BE/blob/6a34772240d4c25f27cb846ceba22721ef2f9855/backend/src/main/java/io/f1/backend/global/lock/DistributedLockAspect.java#L27-L59) / CAS** [적용 코드](https://github.com/prgrms-web-devcourse-final-project/WEB5_7_F1_BE/blob/6a34772240d4c25f27cb846ceba22721ef2f9855/backend/src/main/java/io/f1/backend/domain/game/app/GameService.java#L148C9-L151C10) **]** 

- Redisson 기반 분산 락을 AOP로 추상화 하여 어노테이션만으로 원하는 메서드에 락적용 가능하도록 구현, Lock Key는 충돌 가능성이 있는 roomId를 기준으로 설정해,동일한 roomId에 대해서는 한 번에 하나의 쓰레드만 접근 가능하도록 제어
- AtomicBoolean answered 필드값으로 중복 처리 방지
    - CAS(Compare and Swap) 연산을 통해 하나의 로직만 실행되도록 구현

**배운 점**

- AOP 기반 Redis 분산 락과 CAS를 활용하여 동시성 제어를 구현하며, 상황에 맞는 락 선택과 확장성 있는 설계의 중요성을 터득

<br>

## 📃 프로젝트 관련 문서

- 와이어프레임 : [와이어프레임 링크](https://www.figma.com/design/HEJVJjCZ9USRZvdWO8Ft6L/%EB%87%8C%ED%94%BC%EC%85%9C-%EA%B0%80%EC%A0%9C-?node-id=0-1&t=aGMTZdFnk1dle51L-1)
- 프로젝트 기획서 : [프로젝트 기획서 링크](https://www.notion.so/248a7ca2e7b281538928cc49e3b20605?pvs=21)
- 웹소켓 / SSE 명세서 : [웹 소켓 / SSE 명세서](https://www.notion.so/248a7ca2e7b2819cb463f307f163a742?pvs=21)
