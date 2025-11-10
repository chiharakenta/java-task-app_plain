#!/bin/bash

# クリーン
rm -rf target/*
mkdir -p target/WEB-INF/classes

# javaのコンパイル
find src/java -name "*.java" > sources.txt
javac -d target/WEB-INF/classes -cp "lib/*:src/webapp/WEB-INF/lib/*" @sources.txt
rm sources.txt

# webappフォルダ内のコピー
cp -r src/webapp/* target

# デプロイ
APP_PATH="$CATALINA_HOME/webapps/ROOT"
rm -rf -- $APP_PATH
mkdir $APP_PATH
cp -r target/* $APP_PATH

# tomcatの再起動
$CATALINA_HOME/bin/shutdown.sh
$CATALINA_HOME/bin/startup.sh
