# SmartShop
ちょっと機能が足りない（スマートな）ショッププラグインです。
## 概要
- Spigot 1.21.1での動作を想定しています。
- /shopコマンドでGUIを開き、商品の出品・購入ができます。
- SimpleEconomy( https://github.com/AoiDeveloper/SimpleEconomy )に依存しています。
## ビルド方法
~~ルートで./gradlew jarとかするとjarが生成されます。~~
./gradlew shadowJarを使用してください。そうしなければkotlin-std-libが含まれません。
## 動作確認方法
Spigot 1.21.1で作ったサーバーのpluginsフォルダに入れて実行してください。  
もちろんそのときにはSimpleEconomyも導入してください。

また、デバッグのために/economy <amount:double>という自分にamountだけお金を与えるコマンドを追加しています。使ってください。
## その他必要なもの
特になし
