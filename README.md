## GitHubの使い方

### 最初のプロジェクトの上げ方

```
echo "# ChatLimit" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/minam0119/ChatLimit.git
git push -u origin master
```

### 変更したファイルをアップロードする

```
// 全部のファイルを上げるコマンド
// どっちかのコマンドを使えるよ
git add -A
git add .
```

```
// コミットをする
git commit -m"メッセージを書く"
```

```
// パソコン上のファイルをネットにアップロードする
git push -u origin master
```