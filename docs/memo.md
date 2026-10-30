# Linuxで環境変数を設定し、Javaで取得する方法
### Linuxで環境変数を設定
---  
一時的に設定（現在のシェルのみ有効）  
```bash
export DATA_DIR=/home/work/dev/springboot-pref/src/main/resources/data/
```

確認：
```bash
echo $DATA_DIR
```
永続的に設定
```text
~/.bashrc または ~/.zshrc に追加します。
```

```bash
export DATA_DIR=/home/work/dev/springboot-pref/src/main/resources/data/
```

反映：
```bash
source ~/.bashrc
```
または
```bash
source ~/.zshrc
```

- Spring Boot起動時だけ設定  
```bash
DATA_DIR=/home/work/dev/springboot-pref/src/main/resources/data/
java -jar app.jar
```
または  
```bash
export DATA_DIR=/home/work/dev/springboot-pref/src/main/resources/data/
./mvnw spring-boot:run
```

### Javaで環境変数を取得  
---
標準Java
```java
String dataDir = System.getenv("DATA_DIR");

System.out.println(dataDir);
```

- Spring Bootで利用
```java
@Value
@Value("${DATA_DIR}")
private String dataDir;
```

- 環境変数が未設定の場合のデフォルト値：
```java
@Value("${DATA_DIR:/tmp/data}")
private String dataDir;

Environment
@Autowired
private Environment environment;

String dataDir = environment.getProperty("DATA_DIR");

application.ymlで使う
app:
  data-dir: ${DATA_DIR}

@Value("${app.data-dir}")
private String dataDir;
```

これで、  
/home/work/dev/springboot-pref/src/main/resources/data/ を  
DATA_DIR 環境変数として設定し、Spring Bootから取得できます。  


# 純正なJavaで環境変数を取得
純正なJavaなら System.getenv() を使います。

- 特定の環境変数を取得
```java
public class Main {
    public static void main(String[] args) {
        String dataDir = System.getenv("DATA_DIR");

        System.out.println(dataDir);
    }
}
```

- Linuxで事前に設定:
```bash
export DATA_DIR=/home/work/dev/springboot-pref/src/main/resources/data/
```

実行:
```bash
java Main
```

出力:
```text
/home/work/dev/springboot-pref/src/main/resources/data/
```

- 環境変数が存在するか確認
```java
String dataDir = System.getenv("DATA_DIR");

if (dataDir == null) {
    System.out.println("DATA_DIRが設定されていません");
} else {
    System.out.println(dataDir);
}
```

- 全ての環境変数を取得
```java
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();

        env.forEach((key, value) ->
            System.out.println(key + "=" + value)
        );
    }
}
```

パスとして使う例
```java
import java.nio.file.Path;

String dataDir = System.getenv("DATA_DIR");
Path path = Path.of(dataDir, "pref.csv");

System.out.println(path);
```

出力:  
```text
/home/work/dev/springboot-pref/src/main/resources/data/pref.csv
```
なお、System.getProperty() は JVM のシステムプロパティ（-Dkey=value）用で、  
環境変数取得には System.getenv() を使います。  


# MySQLとPostgreSQLの違い
MySQLとPostgreSQLは  
どちらも人気のあるオープンソースのリレーショナルデータベース（RDBMS）ですが、**設計思想**や得意分野に違いがあります。  

比較表(MySQLとPostgreSQL)
| 項目 | MySQL | PostgreSQL | 
| --- | -------- | -------- |
| 特徴 | シンプルで高速 | 高機能で標準準拠 | 
| 学習コスト | 比較的低い | やや高い | 
| 読み取り性能 | 高速 | 十分高速 | 
| 複雑なクエリ | やや苦手 | 得意 | 
| SQL標準準拠 | 一部独自仕様あり | 高い | 
| JSON対応 | 対応 | より強力 | 
| 拡張性 | 中程度 | 非常に高い | 
| トランザクション | 対応（InnoDB） | 強力

- 主な利用例	
  - Webサイト、ECサイト	業務システム、分析基盤

- MySQLのメリット
  - セットアップが簡単
  - 動作が軽く高速
  - WordPressなど多くのWebサービスで採用
  - 初学者にも扱いやすい

- 代表的な採用例
  - WordPress
  - Shopify（一部）
  - 多くのLAMP環境
```sql
SELECT * FROM users WHERE age > 20;
```

- PostgreSQLのメリット
  - SQL標準への準拠度が高い
  - 複雑なデータ処理や分析が得意
  - JSONB（高速なJSON検索）をサポート
  - 地理情報（PostGIS）が強力
  - 独自データ型や関数を追加可能

例えばJSON検索：
```sql
SELECT *
FROM orders
WHERE order_data->>'status' = 'completed';
```

選択基準
- MySQLがおすすめ
  - Webアプリを素早く作りたい
  - WordPressを利用する
  - シンプルなCRUD中心
  - 運用の容易さを重視

- PostgreSQLがおすすめ
  - 今後システムが大きくなる可能性がある
  - 複雑な検索や分析を行う
  - JSONデータを多用する
  - GIS（地図情報）を扱う
  - エンタープライズ向け開発
  - 初心者向けの結論

2026年現在、  
新規開発なら PostgreSQLを選ぶケースが増えています。  
高機能で拡張性が高く、近年は多くのクラウドサービス（AWS、Azure、GCP）でも重要な選択肢になっています。  

一般的な選択基準、  
- 「WordPressを動かしたい」→ MySQL
- 「Webアプリ開発を学びたい」→ PostgreSQL
- 「就職・実務を意識したい」→ PostgreSQL

# 同じSQLで違いを比較して
同じ処理をするSQLでも、MySQLとPostgreSQLでは書き方や機能に違いがあります。  

### 1. オートインクリメント
---
```sql
MySQL
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);
```

PostgreSQL
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);
```

最近のPostgreSQLでは標準SQL準拠のこちらもよく使われます。
```sql
CREATE TABLE users (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100)
);
```

### 2. 現在日時取得
---
MySQL
```sql
SELECT NOW();
```

PostgreSQL
```sql
SELECT NOW();
```
これは同じです。

### 3. レコード件数取得
---
MySQL
```sql
SELECT COUNT(*) FROM users;
```
PostgreSQL
```sql
SELECT COUNT(*) FROM users;
```
これも同じです。

### 4. LIMITによるページング
---
MySQL
```sql
SELECT *
FROM users
LIMIT 10 OFFSET 20;
```

または
```sql
SELECT *
FROM users
LIMIT 20, 10;
```

PostgreSQL
```sql
SELECT *
FROM users
LIMIT 10 OFFSET 20;
```
PostgreSQLは LIMIT 20,10 の書き方は不可です。

### 5. 文字列連結
---
MySQL
```sql
SELECT CONCAT(first_name, ' ', last_name);
```
PostgreSQL
```sql
SELECT first_name || ' ' || last_name;
```
もちろん PostgreSQL でも CONCAT() は使えます。

### 6. UPSERT（存在すれば更新）
---
MySQL
```sql
INSERT INTO users(id, name)
VALUES (1, 'Taro')
ON DUPLICATE KEY UPDATE
name = 'Taro';
```

PostgreSQL
```sql
INSERT INTO users(id, name)
VALUES (1, 'Taro')
ON CONFLICT(id)
DO UPDATE SET
name = EXCLUDED.name;
```
PostgreSQLのほうが柔軟です。

### 7. JSON検索
---
データ例
```json
{
  "name": "Taro",
  "age": 30
}
```

MySQL
```sql
SELECT *
FROM users
WHERE JSON_EXTRACT(profile, '$.age') = 30;
```

PostgreSQL
```sql
SELECT *
FROM users
WHERE (profile->>'age')::int = 30;
```
PostgreSQLはJSONB型が非常に強力で、大量データ検索に向いています。  

### 8. フルテキスト検索
---
MySQL
```sql
SELECT *
FROM articles
WHERE MATCH(title, body)
AGAINST('database');
```

PostgreSQL
```sql
SELECT *
FROM articles
WHERE to_tsvector(title || ' ' || body)
@@ to_tsquery('database');
```
PostgreSQLのほうが高機能でカスタマイズ性が高いです。

### 9. NULLの扱い
---
MySQL
```sql
SELECT IFNULL(price, 0);
```

PostgreSQL
```sql
SELECT COALESCE(price, 0);
```
実は COALESCE() はMySQLでも使えます。

実務でよく感じる違い
同じ「ユーザー一覧取得」ならほぼ同じです。
```sql
SELECT id, name
FROM users
WHERE age >= 20
ORDER BY id
LIMIT 10;
```

それぞれの強み
- PostgreSQL の強み
  - JSONを扱う
  - 分析クエリを書く
  - Window関数を多用する
  - GISを使う
  - 複雑な集計をする  

- MySQL の強み
  - WordPress
  - Laravelの小規模サイト
  - ECサイト
  - シンプルなCRUDアプリ  
なら MySQL で十分です。

一言でいうと、  
MySQL = 「扱いやすい軽自動車」 
PostgreSQL = 「高性能なSUV」

というイメージです。  
SQLの基本文法は8〜9割同じですが、高度な機能になるほどPostgreSQLの方が豊富です。  

# MySQLにGISのものはある？
あります。  
実は MySQLもGIS（Geographic Information System）機能を標準サポート しています。  
空間データ型、空間関数、空間インデックスを利用して位置情報を扱えます。  

例えば地点を保存できます。
```sql
CREATE TABLE stores (
    id INT PRIMARY KEY,
    location POINT NOT NULL,
    SPATIAL INDEX(location)
);
```

MySQLでサポートされる主な空間型は次のとおりです。
- 空間型
  - POINT（点）
  - LINESTRING（線）
  - POLYGON（ポリゴン）
  - MULTIPOINT
  - MULTILINESTRING
  - MULTIPOLYGON
  - GEOMETRYCOLLECTION
  - 近くの店舗検索

例えば東京駅付近の店舗を検索する場合  
```sql
SELECT id,
       ST_Distance_Sphere(
           location,
           POINT(139.7671, 35.6812)
       ) AS distance
FROM stores
ORDER BY distance
LIMIT 10;
```
のような検索が可能です。

PostgreSQL(PostGIS)との違い(ここが重要)  
- MySQL GIS
  - 基本的な位置情報検索は十分
  - 地点検索
  - 範囲検索
  - 距離計算
  - 空間インデックス対応
  - Webサービス向き  

- PostgreSQL + PostGIS
  - GIS業界の事実上の標準
  - 地図アプリ開発で最強クラス
  - 数千以上の地理関数
  - 緯度経度変換
  - 衛星データ
  - ジオフェンシング
  - 高度なルート解析  
などが利用できます。

実務での使い分け
- MySQL GISで十分
  - 店舗検索
  - 配達エリア判定
  - 近くのユーザー検索
  - 不動産サイト  

- PostGISが必要
  - カーナビ
  - Google Mapsのようなサービス
  - ドローン管理
  - 防災システム
  - 地理空間分析  

エンジニア界隈ではよく  
GISを使うなら PostgreSQL + PostGIS、一択に近い  
と言われます。  

ただし「近くの店舗を探す」「半径5km以内を検索する」程度なら、MySQLのGIS機能だけでも十分実用的です。  
