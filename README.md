# 概要
あえてmavenやgradle、IDEなどを使わずに、
java・tomcat・シェルスクリプトで、war準拠のタスク管理アプリを作成してみました。
ディレクトリの構成はmavenに近い形にしています。

# バージョン情報
- 言語
    - openjdk v21
- サーバー
    - tomcat 11
- db
    - dbフォルダ内にjsonで保存

# ビルド手順
1. 環境変数$CATALINA_HOMEに、tomcatのパスを指定
    
    例: Macのhomebrewで入れたtomcatの場合
    ```
    echo 'export CATALINA_HOME="/opt/homebrew/Cellar/tomcat/11.0.13/libexec"' >> ~/.zshrc
    source ~/.zshrc
    ```
1. クローン
    ```shell
    git clone https://github.com/chiharakenta/java-task-app_plain.git
    cd java-task-app_plain
    ```
1. shファイルに実行権限を付与
    ```shell
    chmod +x init.sh
    chmod +x build.sh
    ```
1. 必要なディレクトリを生成
    ```shell
    ./init.sh
    ```
1. 開発時に必要なライブラリをlibフォルダにダウンロード
    ```
    cd lib
    curl -OL https://repo1.maven.org/maven2/jakarta/servlet/jakarta.servlet-api/6.1.0/jakarta.servlet-api-6.1.0.jar
    curl -OL https://repo1.maven.org/maven2/com/google/code/gson/gson/2.13.2/gson-2.13.2.jar
    cd ..
    ```
1. 実行時に必要なライブラリをダウンロード
    ```shell
    cp lib/gson-2.13.2.jar src/webapp/WEB-INF/lib
    cd src/webapp/WEB-INF/lib
    curl -OL https://repo1.maven.org/maven2/jakarta/servlet/jsp/jstl/jakarta.servlet.jsp.jstl-api/3.0.2/jakarta.servlet.jsp.jstl-api-3.0.2.jar
    curl -OL https://repo1.maven.org/maven2/org/glassfish/web/jakarta.servlet.jsp.jstl/3.0.1/jakarta.servlet.jsp.jstl-3.0.1.jar
    cd ../../../..
    ```
1. 実行
    ```shell
    ./build.sh
    ```

    [http://localhost:8080](http://localhost:8080) にアクセス
