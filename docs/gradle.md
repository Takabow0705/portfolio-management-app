# Gradleの準備

## 手順

1. 下記サイトより `Gradle 5.6.2`　の`zip`をダウンロードしてくる。ダウンロードするのは `complete` 版が望ましい。

[https://gradle.org/next-steps/?version=5.6.2&format=all](https://gradle.org/next-steps/?version=5.6.2&format=all)

2. Gradleのzipを解凍し、フォルダ内の `bin` フォルダの中までのパスを環境変数に登録。

3. コマンドプロンプトで下記コマンドを実行。

```
gradle --version
```

以下のような画面が出れば成功

```
------------------------------------------------------------
Gradle 5.6.2
------------------------------------------------------------

Build time:   2019-09-05 16:13:54 UTC
Revision:     55a5e53d855db8fc7b0e494412fc624051a8e781

Kotlin:       1.3.41
Groovy:       2.5.4
Ant:          Apache Ant(TM) version 1.9.14 compiled on March 12 2019
JVM:          11.0.4 (Amazon.com Inc. 11.0.4+11-LTS)
OS:           Linux 5.0.0-27-generic amd64

```

失敗する場合は環境変数を見直すか、再起動。