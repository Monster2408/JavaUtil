# Java用

[![](https://jitpack.io/v/xyz.mlserver/JavaUtil.svg)](https://jitpack.io/#xyz.mlserver/JavaUtil)
[![Twitter](https://badgen.net/twitter/follow/monster_2408?icon=twitter)](https://twitter.com/monster_2408)

[Java Docs](https://docs.mlserver.jp/JavaUtil/)

```xml
<project>
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    
    <dependencies>
        <dependency>
            <groupId>xyz.mlserver</groupId>
            <artifactId>JavaUtil</artifactId>
            <version>VERSION</version>
        </dependency>
    </dependencies>
</project>
```
# Java用

# JavaDocの生成
JavaバージョンとJavadocバージョンの違いでエラーが出たため標準の機能でなく`mvn`を使用することになったため以下コマンドを使用すること
```shell
mvn javadoc:javadoc
```