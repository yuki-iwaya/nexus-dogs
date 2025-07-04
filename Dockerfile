# 📦 ビルドステージ
# ベースイメージとしてGradle（JDK 17）を使用
FROM gradle:8.7-jdk17 AS build

# 作業ディレクトリ
WORKDIR /app

# Gradle設定ファイルとラッパーをコピー (キャッシュを効かせるため先にコピー)
# これらのファイルに変更がなければ、依存関係のダウンロードがスキップされる
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# 依存関係をダウンロード
# これにより、ソースコードの変更があっても依存関係の再ダウンロードを防ぐ
RUN gradle dependencies --no-daemon --stacktrace

# プロジェクトの残りのファイルをコピー
COPY src ./src

# プロジェクトをビルド
RUN gradle bootJar --no-daemon

# JARファイルが作成されたことを確認
RUN ls -l /app/build/libs

# 🏃‍♂️ ランタイムステージ
# ランタイムイメージとして OpenJDK 17 slim を使用
FROM openjdk:17-jdk-slim

# アプリケーション実行用のユーザーとグループを作成 (セキュリティ強化のため)
RUN groupadd --system springboot && useradd --system --gid springboot springboot

# Spring Bootアプリケーションのデフォルトポートを公開
EXPOSE 8080

# コンテナ内の作業ディレクトリを設定
WORKDIR /app

# ビルドステージで作成したJARファイルをランタイムステージにコピー
COPY --from=build /app/build/libs/*.jar /app.jar

# コピーしたJARファイルの所有者を新しく作成したユーザーに変更
RUN chown springboot:springboot /app.jar

# 以降のコマンドを非rootユーザーとして実行
USER springboot

# コピーされたファイルを確認
RUN ls -l /app

# アプリケーションの実行コマンド
# JVMのメモリ設定などをここに追加することも可能 (例: -Xmx512m)
ENTRYPOINT ["java", "-jar", "/app.jar"]
