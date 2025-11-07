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
TOMCAT_PATH="/Users/chiharakenta/Desktop/java-samples/apache-tomcat-11.0.10"
APP_PATH="$TOMCAT_PATH/webapps/ROOT"
rm -rf -- $APP_PATH
mkdir $APP_PATH
cp -r target/* $APP_PATH

# tomcatの再起動
$TOMCAT_PATH/bin/shutdown.sh
$TOMCAT_PATH/bin/startup.sh