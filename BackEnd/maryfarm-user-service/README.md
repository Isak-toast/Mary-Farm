# 웹 디자인 Backend

<!-- 필수 항목 -->

## 소개

웹 디자인 프로젝트의 Backend 스켈레톤 코드

<!-- 필수 항목 -->

## 기술스택 및 라이브러리

| Project | Version | Description |
| --- | --- | --- |
| Java | 1.8 |  |
| Spring Boot | 1.8 |  |
| Tomcat | 1.8 |  |
| Gradle | x.x.x | Build Tool |
| MySQL | x.x.x |  |
|  |  |  |

<!-- 필수 항목 -->

## 개발 환경 구성

### EC2 설치

```docker
# yum으로 zulu8 openjdk
sudo yum install java-1.8.0-openjdk

java -version
# maven과 node&npm 설치
sudo yum install maven 
curl -sL [https://rpm.nodesource.com/setup_10.x](https://rpm.nodesource.com/setup_10.x) | sudo -E bash
sudo yum install nodejs -y
node -v
npm -v

# git clone project
git clone [https://lab.ssafy.com/s08-webmobile2-sub1/skeleton-project.git](https://lab.ssafy.com/s08-webmobile2-sub1/skeleton-project.git)
username : isakggong
password : key
```

[[Node.js] npm cache 해결](https://icerabbit.tistory.com/78)

```docker
npm cache clean —force

yum clean all # cache를 삭제
```

### Port 설정

EC2 포트는 3022로 열려있다. ssh를 통해서 MobaXterm으로 EC2에 접근가능하다. 

안에서 front server와 back server를 열어야 한다. 

[](https://velog.io/@svstar94/Frontend-Backend-%EC%84%9C%EB%B2%84-AWS-EC2%EB%A1%9C-%EB%B0%B0%ED%8F%AC%ED%95%98%EA%B8%B0-https-%EC%84%A4%EC%A0%95%EA%B9%8C%EC%A7%80)

[AWS - Frontend 배포](https://velog.io/@e_soojeong/AWS-Frontend-%EB%B0%B0%ED%8F%AC)

→ Frontend 코드만을 가지고 간이 서버를 만들어서 실행시켜보자.

포트는 TCP : 8000으로 열어두었다.

보안그룹 → EC2에서 보안을 위해서 열어둔 포트로만 접근할 수 있도록 한다.

현재 IPv4는 [http://3.34.197.134](http://3.34.197.134:8000/) 이므로 http://3.34.187.134:8000번으로 진입할 수 있다면 된다.

### BackEnd 개발 환경 구성

Windows 기준 개발 환경 구성 설명

1. OpenJDK 설치
    1. JDK 다운로드 사이트에서 1.8.x 설치 파일 다운로드 및 실행
        - Zulu OpenJDK: [https://www.azul.com/downloads/?version=java-8-lts&package=jdk](https://www.azul.com/downloads/?version=java-8-lts&package=jdk)
        - OJDK Build: [https://github.com/ojdkbuild/ojdkbuild](https://github.com/ojdkbuild/ojdkbuild)
    2. 설치 후 명령 프롬프트(cmd) 확인
    출력 예)
        
        ```
        > java -version
        openjdk version "1.8.0_192"
        OpenJDK Runtime Environment (Zulu 8.33.0.1-win64) (build 1.8.0_192-b01)
        OpenJDK 64-Bit Server VM (Zulu 8.33.0.1-win64) (build 25.192-b01, mixed mode)
        
        ```
        
2. Docker 및 데이터베이스 구성 *(이미 설치되어 있거나 원격 DB를 사용하는 경우 설치 부분 생략)*
    1. Docker 사이트를 참조하여 Docker For Windows 설치
        - [https://docs.docker.com/docker-for-windows/install/](https://docs.docker.com/docker-for-windows/install/)
    
     1. Docker for Linux(centos- yum) - root 권한으로 진행
    
    - yum update
    
    1) docker 설치
    
    - `yum install docker -y`
    
    2) 설치한 Docker 버전 확인
    
    - `docker -v`
    
    3) Docker 실행
    
    - `sudo service docker start`
    
    4) Docker 그룹에 sudo 추가 (인스턴스 접속 후 도커 바로 제어할 수 있도록)
    
    - `sudo usermod -aG docker ec2-user`
    
    [[AWS] EC2 Linux에 Docker 설치하기](https://jinjinyang.tistory.com/46)
    
    1. MySQL 설치
        - cmd에서 docker run 커맨드 실행
            
            ```
            > docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name farm-mysql mysql
            
            > docker run --name maria-db -p 3306:3306 -e MYSQl_ROOT_PASSWORD=<패스워드> -d mariadb
            ```
            
    2. DB 및 계정 생성
        - cmd에서 docker exec 커맨드 실행
            
            ```
            > docker exec -it farm-mysql mysql -u root -p
            
            > docker exec -it maria-db mysql -u root -p
            # 컨테이너 빠져나오기
            exit
            ```
            
        - root 비밀번호 입력
            
            ```
            > Enter passowrd : root
            
            ```
            
        - DB 생성
            
            ```
            mysql> create database IF NOT EXISTS `ssafy_sns`;
            
            ```
            
3. 프로젝트 다운로드 및 설정
    1. 프로젝트 다운로드
        
        ```
        git clone <repo URL>
        ```
        
    2. src/main/resources/application.properties 수정
        
        ```
        spring.datasource.username=<사용자 계정> -> root
        spring.datasource.password=<비밀번호> -> 1234
        
        ```
        

## 디렉토리 구조

```
.
└─src
    ├─main

```

[[Docker] 도커 컨테이너 접속 및 빠져나오기](https://itholic.github.io/docker-enter-container/)

### Nginx

[NginX로 Reverse-Proxy 서버 만들기](https://www.joinc.co.kr/w/man/12/proxy)

nginx version: nginx/1.22.0

node v14.21.2

npm 6.14.17

*nohup 프로세스 죽이기 [https://duckgugong.tistory.com/m/257](https://duckgugong.tistory.com/m/257)

ps -ef | grep ‘index.ts’

[[EC2] 프리티어(t2.micro)에서 Jenkins 용량 초과 문제](https://gksdudrb922.tistory.com/196)

[[Jenkins] Docker를 이용한 Jenkins - Spring Boot 프로젝트 배포](https://dev-overload.tistory.com/40)

Jenkins 이미지 → Jenkins를 실행하려면 swapfile로 RAM을 늘려줄 필요가 있음.

`$ docker pull jenkins/jenkins:lts`

`$ docker run --name jenkins-docker -d -p 8080:8080 -p 50000:50000 -v /home/jenkins:/var/jenkins_home -u root jenkins/jenkins:lts`

*docker 옵션

여기서 -d 명령어는 백그라운드 실행, -p 명령어는 컨테이너와 호스트 PC 간 연결을 위해 내부 포트와 외부 포트를 묶은 것입니다.

- v 명령어는 이미지의 /var/jenkins_home 디렉터리를 호스트 PC 내에 마운트 하는 명령어입니다.

이것을 하는 이유는, Jenkins 설치 시 ssh 키값 생성, 저장소 참조 등을 용이하게 하기 위함입니다.

[[SpringBoot] 게시판 (5) - AWS EC2에 배포하기 (feat. AWS RDS)](https://victorydntmd.tistory.com/338)

gradle web project 배포

---
CI/CD jenkin 인프런 강의 진행 중
