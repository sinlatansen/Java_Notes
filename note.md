# Java 学习

## 安装环境

```bash
brew install cask java11
sudo ln -sfn /usr/local/opt/openjdk@11/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-11.jdk
echo 'export PATH="/usr/local/opt/openjdk@11/bin:$PATH"' >> ~/.zshrc
export CPPFLAGS="-I/usr/local/opt/openjdk@11/include"
```

```java
public class Main {
    public static void main(String[] args) {
        System.out.print("Hello and welcome!") ;
    }
}
```

IDEA 下载插件慢：修改 host 文件，添加 plugins.jetbrains.com 的 IP。

## 基础语法

### 基础规则

1. Java 源文件由类组成。
2. 入口函数`public static void main(String[] args)`为固定约定。
3. 一个源文件最多一个 public 类，其他类个数不限。
4. 源文件的文件名与 public 类名一致。
5. 可以讲 main 方法写在非 public 类，然后指定运行非 public 类。

### 转义字符

- `\t`：制表
- `\n`：换行
- `\\`：一个\
- `\"`：一个”
- `\'`：一个’
- `\r`：一个回车

回车与换行区别

```java
System.out.println("123\n45");
// out:
/*
123
45
*/

System.out.println("123\r45");
// out:
/*
453
*/
```

### 文档注释

注释内容可以被`javadoc`解析。

```java
/**
 * @author lzy
 * @version v0.1.0
 */
class shady{

}
```

```bash
% javadoc -d ../doc -author -version Main.java                                             24-06-26 - 0:10:22
正在加载源文件Main.java...
正在构造 Javadoc 信息...
正在创建目标目录: "../doc/"
标准 Doclet 版本 11.0.23
正在构建所有程序包和类的树...
正在生成../doc/Main.html...
正在生成../doc/package-summary.html...
正在生成../doc/package-tree.html...
正在生成../doc/constant-values.html...
正在构建所有程序包和类的索引...
正在生成../doc/overview-tree.html...
正在生成../doc/index-all.html...
正在构建所有类的索引...
正在生成../doc/allclasses-index.html...
正在生成../doc/allpackages-index.html...
正在生成../doc/deprecated-list.html...
正在构建所有类的索引...
正在生成../doc/allclasses.html...
正在生成../doc/allclasses.html...
正在生成../doc/index.html...
正在生成../doc/help-doc.html...
```

### Java 代码规范

1. 注释用 javadoc 方式来写。
2. 非 javadoc 的注释是给代码维护者看的。
3. 行宽度 80 字符。
4. UTF-8 编码。
5. **次行风格**和**行尾风格**。
