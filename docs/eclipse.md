# eclipseの準備

## 手順

1. 下記サイトからEclipseをダウンロード

[https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/)

2. 解凍してEclipseの `exe` を起動できればOK。


## 補足

### Eclipseのメモリヒープを増やす方法

`eclipse.exe` と同じフォルダにある　`eclipse.ini`　を編集する。

以下の部分を変更後のように編集する。
**変更前**
```
-Xms256m
-Xmx1024m
```
**変更後**
```
-Xms2048m
-Xmx2048m
```

- 参考  
[eclipseのメモリを増やしたい](https://qiita.com/crarrry/items/7601290c11f7a310913b)

### 日本語化のやり方

下記リンクを参照

[https://proengineer.internous.co.jp/content/columnfeature/7853](https://proengineer.internous.co.jp/content/columnfeature/7853)
