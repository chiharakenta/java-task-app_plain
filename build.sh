#!/bin/bash

# --------------------------
# ターゲットフォルダの中身をクリア
rm -rf target/*
# --------------------------

# --------------------------
# コンパイル
## javaのコンパイル
find src/java -name "*.java" > sources.txt
javac -d target/WEB-INF/classes -cp "lib/*:src/webapp/WEB-INF/lib/*" @sources.txt
rm sources.txt

## java以外のファイルのコピー
cp -r src/webapp/* target/
# --------------------------


# --------------------------
# warファイルの作成
cd target
jar cf ROOT.war .
cd ..
# --------------------------



# --------------------------
# Tomcatにデプロイ
APP_PATH="$CATALINA_HOME/webapps"
rm -f $APP_PATH/ROOT.war
rm -rf $APP_PATH/ROOT
cp target/ROOT.war $APP_PATH
# --------------------------


# --------------------------
# Tomcatの再起動
$CATALINA_HOME/bin/shutdown.sh
$CATALINA_HOME/bin/startup.sh
# --------------------------