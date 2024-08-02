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
