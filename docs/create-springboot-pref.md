# SpringBoot最小構成プロジェクト
Linux上で Spring Boot + Thymeleaf で最小構成プロジェクトを作成する. 

### プロジェクト作成
リポジトリの作成  
Git Hub上で行う.

プロジェクトのクローン
```bash
git clone "git@github.com:ryuma-nakajyun/springboot-pref.git"
```

docsディレクトリ作成
```bash
mkdir -p  springboot-pref/docs
```

ドキュメント作成
```bash
touch  springboot-pref/docs/create-springboot-pref-memo.md
```

java メインクラス作成  
```bash
mkdir -p springboot-pref/src/main/java/com/example/pref 
```
```bash
touch springboot-pref/src/main/java/com/example/pref/PrefApplication.java
```

java controllerディレクトリ作成
```bash
mkdir -p springboot-pref/src/main/java/com/example/pref/controller  
```
```bash
touch springboot-pref/src/main/java/com/example/pref/controller/PerfController.java  
```

templatesディレクトリ作成
```bash
mkdir -p springboot-pref/src/main/resources/templates
```
index.htmlファイル作成
```bash
touch springboot-pref/src/main/resources/templates/index.html
```

staticディレクトリ作成
```bash
mkdir -p springboot-pref/src/main/resources/static
```
style.cssファイル作成
```bash
touch springboot-pref/src/main/resources/static/style.css
```

### pom.xml作成
```bash 
touch springboot-pref/pom.xml
```
springboot-pref/  
pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
            <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

メインクラス作成  
springboot-pref/src/main/java/com/example/demo/  
PrefApplication.java
```java
package com.example.pref;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrefApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrefApplication.class, args);
    }
}
```

Controller作成  
springboot-pref/src/main/java/com/example/pref/controller/  
PerfController.java 
```java
package com.example.pref.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfController {

    @GetMapping("/index")
    public String hello(Model model) {

        model.addAttribute("message", "Hello Pref!");
        
        return "index";
    }
}
```

### 起動
```bash
mvn spring-boot:run
```
### 動作確認  
別ターミナル
```bash
curl http://localhost:8080/index
```

### 結果  
```text
TBA
```

### ディレクトリ構成
現状ディレクトリ階層を確認
```bash
$ tree springboot-pref/
``` 
```text
springboot-pref/
├── README.md
├── docs
│   └── create-springboot-pref-memo.md
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── pref
│       │               ├── PrefApplication.java
│       │               └── controller
│       │                   └── PerfController.java
│       └── resources
│           ├── static
│           │   └── style.css
│           └── templates
│               └── index.html
└── target
    ├── classes
    │   ├── com
    │   │   └── example
    │   │       └── pref
    │   │           ├── PrefApplication.class
    │   │           └── controller
    │   │               └── PerfController.class
    │   ├── static
    │   │   └── style.css
    │   └── templates
    │       └── index.html
    ├── generated-sources
    │   └── annotations
    └── maven-status
        └── maven-compiler-plugin
            └── compile
                └── default-compile
                    ├── createdFiles.lst
                    └── inputFiles.lst

26 directories, 13 files    
```

### 学び・気づき
---
- 
- 
- 

### 疑問
---
- 
- 
- 

### 参考
---
- AI先生
- 参考書籍・サイト
  -  Spring Boot 3で始めるWebアプリケーション開発入門(基礎編): プログラミング初心者・Webプログラマになりたい人向け 超入門テキスト
  - [Java の double を素直な String にしたい、E 要らない](https://qiita.com/k_ui/items/840227f29848b3ad0fbf)

### memo
---
- 
- 
- 
