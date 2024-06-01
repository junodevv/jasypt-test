#!/usr/bin/env bash

# 프로젝트 경로
PROJECT_ROOT="/home/ec2-user/jasypt-test"
JAR_FILE="$PROJECT_ROOT/spring-webapp.jar"

# 각종 로그 경로, optional 근데 해주면 도움이 많이됨
APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +'%Y-%m-%d %H:%M:%S')
# build 파일을 정해진 이름으로 복사
echo "[$TIME_NOW] > $JAR_FILE 파일 복사" >> $DEPLOY_LOG   # $DEPLOY_LOG 에 앞 "" 내용을 append 한다. == 로그남기기
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE           # 루트경로에 생긴 *.jar 파일을 우리가 정해놓은 이름인 JAR_FILE 로 복사하기

# jar 파일 실행
echo "[$TIME_NOW] > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &
#↳ nohup: no hang up, 터미널의 세션이 끊겨도 실행된 프로그램은 종료시키지 않는것
#↳ 2> : 표준 에러 출력을 해당 파일에 기록한다.
#↳ & : 명령어를 백그라운드에서 실행한다.

# 결과 출력
CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "[$TIME_NOW] > 실행 완료. PID = $CURRENT_PID" >> $DEPLOY_LOG # 어플리케이션이 새로 실행 될 때마다 PID 가 바뀌는데 이를 확인하기위한 로그