# Fisa's Tower

## 목적
> 매일 점심 메뉴를 고민하는 우리 FISA 수강생들을 위해 식사 모임 및 랜덤 점심 추천 서비스를 제공하기 위함.


## 👨‍💻팀원
|<img src="https://avatars.githubusercontent.com/u/127727927?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/78792358?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/74589010?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/175282913?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|
|부준혁 / 팀장⚜ <br/>[@BooJunhyuk](https://github.com/BooJunhyuk)|HyeonWoo Park<br/>[@smartcow99](https://github.com/smartcow99)|강유완<br/>[@yuwankang](https://github.com/yuwankang)|이정욱<br/>[@jeonguk0201](https://github.com/jeonguk0201)|

## Develop Env
![image](https://github.com/user-attachments/assets/523b39d4-2e85-4791-b9aa-7ea4f119f46f)

## 🛒DB ER View
![fisa's tower](https://github.com/user-attachments/assets/0b93ceef-3a67-4274-b306-e84d050ef34d)

## 공유 DB 사용
![image (3)](https://github.com/user-attachments/assets/57dbddc2-0fe7-47c1-ae75-c0b437243202)
> 명령프롬포트->ipconfig->ip주소를 VirtualBox 포워딩 -> DBeaver Host name 입력 후 -> 연결확인
![image](https://github.com/user-attachments/assets/0676123b-372d-4322-983a-e130ec178431)

## 목표 기능

### user
```
- Sign In
- Sign Up
- Withdraw
- 내 정보 보기
```
### plan
```
- 계획 생성
- 계획 수정
- 계획 삭제
- 계획 조회
```
### market
```
- 마켓 정보 조회
- 마켓 리뷰 조회
```
## Table Diagram
![image](https://github.com/user-attachments/assets/e8f6bc5e-f377-4f61-9260-aa8a7f16f97a)

## 용어 사전

| 도메인 | 물리명 | 약어 | 데이터 타입 | 설명 |
| :---: | :---: | :---: | :---: | :---: |
| User | user identification | uid | Int | 유저 아이디 |
| User | password | pw | varchar(20) | 유저 비밀번호 |
| User | user name | uname | varchar(20) | 유저 이름 |
| User | email | email | varchar(50) | 유저 이메일 |
| User | telephone number | tel | varchar(10) | 유저 전화번호 |
| User | nickname | nickname | varchar(20) | 유저 별명 |
|  |  |  |  |  |
| Notification | notification | nid | Int | 알림 아이디 |
| Notification | notification Info | ninfo | varchar(20) | 알림 정보 |
| Notification | notification date | ndate | date | 알림 날짜 |
|  |  |  |  |  |
| Plan | plan identification | pid | Int | 계획 아이디 |
| Plan | plan date | date | date | 계획 날짜 |
|  |  |  |  |  |
| Market | market identification | mid | Int | 마켓 아이디 |
| Market | market name | mname | varchar(20) | 마켓 이름 |
| Market | location | loc | varchar(20) | 마켓 위치 |
| Market | market info | minfo | varchar(100) | 마켓 정보 |
|  |  |  |  |  |
| Market History | rate | rate | Int | 마켓 순위 |
| Market History | review | review | varchar(300) | 마켓 리뷰 |
| Market History | review date | rdate | date | 마켓 리뷰 날짜 |

# Troubleshooting🤯
## 공유 DB 사용할때 WiFi를 사용할 경우
![image (4)](https://github.com/user-attachments/assets/69fc434e-0fce-4c97-8474-693f70fae935)
### 해결 방법
#### 컨테이너의 IP 주소 확인
```
sudo docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' oracle-db
```
#### 방화벽 설정 확인
```
sudo ufw allow 1521/tcp
```
>Wifi를 사용할 경우 ipconfig를 다시 확인 해야하며 랜선이 연결되어있는 경우 랜선으로 하는 것이 편하다.


