# Amazon-Corretto-11の準備

Javaが無い場合、もしくはJavaのバージョンが8未満の場合に実施する。

## ダウンロード

以下のサイトから `amazon-corretto-11.0.3.7.1-windows-x64.zip` をダウンロードする。  
[https://docs.aws.amazon.com/ja_jp/corretto/latest/corretto-11-ug/downloads-list.html](https://docs.aws.amazon.com/ja_jp/corretto/latest/corretto-11-ug/downloads-list.html)

## パスを通す

環境変数に解凍したフォルダ内の `bin` までのパスを登録する。
また、`JAVA_HOME` 変数も無ければ定義して登録する。

- 参考  
[PATHの設定及び環境変数JAVA_HOMEの設定](https://www.javadrive.jp/start/install/index4.html)