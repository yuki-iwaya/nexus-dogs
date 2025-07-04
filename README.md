# NexusDogs
- NexusDocs (ネクサスドックス): ドキュメント間のつながりやハブになることを示唆します。


---

# 📄 データベース初期化ファイルの準備

データベースの認証情報は機密情報のため、`init.sql` ファイルを直接 Git 管理しません。
代わりに、`init.sql.template` を使用して `init.sql` を生成します。

---

## 📝 手順

### 1️⃣ `.env` ファイルを確認

`MYSQL_USER` と `MYSQL_PASSWORD` が定義されている必要があります。

```dotenv
# .env の例
MYSQL_ROOT_PASSWORD=root_password
MYSQL_DATABASE=your_app_database
MYSQL_USER=your_username
MYSQL_PASSWORD=your_password
```

---

### 2️⃣ `init.sql.template` をコピー

`init.sql.template` を `init.sql` としてコピーします。

#### Linux / macOS:

```bash
cp init.sql.template init.sql
```

#### Windows (PowerShell):

```powershell
Copy-Item .\init.sql.template .\init.sql
```

---

### 3️⃣ プレースホルダーを置換

コピーした `init.sql` ファイル内のプレースホルダー `${MYSQL_USER}` と `${MYSQL_PASSWORD}` を、`.env` の値に置き換えます。

---

#### 🌟 Linux / macOS (Bash / Zsh)

`.env` の変数を現在のシェルに読み込みます。

```bash
# （必要に応じて）
export $(grep -v '^#' .env | xargs)
```

プレースホルダーを置換するワンライナー：

```bash
# macOS の場合
sed -i '' -e "s|\${MYSQL_USER}|$MYSQL_USER|g" -e "s|\${MYSQL_PASSWORD}|$MYSQL_PASSWORD|g" init.sql

# Linux の場合
sed -i -e "s|\${MYSQL_USER}|$MYSQL_USER|g" -e "s|\${MYSQL_PASSWORD}|$MYSQL_PASSWORD|g" init.sql
```

---

#### 🌟 Windows (PowerShell)

`.env` から変数を読み込み、`init.sql` のプレースホルダーを置き換えます。

```powershell
$envContent = Get-Content .\.env | ConvertFrom-StringData
(Get-Content .\init.sql) `
    -replace '\$\{MYSQL_USER\}', $envContent.MYSQL_USER `
    -replace '\$\{MYSQL_PASSWORD\}', $envContent.MYSQL_PASSWORD |
    Set-Content .\init.sql
```

---

## 📂 `.gitignore` の設定

`init.sql` は Git リポジトリにコミットされないように、`.gitignore` に以下を追加します。

```
# .gitignore
init.sql
```

---

## 💡 補足

* 上記のワンライナーは、`MYSQL_USER` と `MYSQL_PASSWORD` が環境変数に設定されているか、PowerShell の例のように `.env` から読み込む前提です。
* `docker-compose up` を実行する前に、必ずこの `init.sql` 生成手順を行ってください。
* MySQL コンテナの初回起動時に `init.sql` が実行されます。
* 認証情報やスキーマの変更があった場合は、一度 MySQL のボリュームを削除してやり直します。

---

### 📦 ボリューム削除の例

```bash
docker-compose down
docker volume rm <プロジェクト名>_mysql_volume
docker-compose up --build
```

---
