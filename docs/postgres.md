# postgersSQLの準備

## ダウンロード

以下のリンクのサイトからダウンロード。バージョンは10.10にする。  
[ダウンロードリンク](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)


**UNIX系OSの場合**  
postgresSQLはデフォルトではOSのユーザ名とDBのユーザ名が一致していなければ、ログインができない。

### 参考  
[peer認証の関係でpsqlログインできない時の対処法](https://qiita.com/tomlla/items/9fa2feab1b9bd8749584)

**Windowsの場合**  
[【未経験者向け】PostgreSQL セットアップ（Windows向け）](https://qiita.com/ynkgw/items/bf4a4a773e7a7cb0172f)

Windowsのときは特に障害はない？？

## 初期設定

以下の作業はすべて `postgres`ユーザで行う。

```
psql user=postgres
```

初期設定時のパスワードを要求されるので、それを入力。

1. Roleの設定

```
postgres=# create role <Role名> LOGIN CREATEDB PASSWORD '<Password>';
```

2. peer認証の停止

設定ファイルのパスを表示

```
postgres=# SHOW hba_file;
```

`peer`を`trust`に変更。
```
# "local" is for Unix domain socket connections only
# local   all             all                                     peer
local   all             all                                     trust
```
3. Roleにスキーマを紐つける

```
postgres=# create database <DB名> owner <Role名>;
```

## クライアントの設定

a5m2でOracleと要領は同じ