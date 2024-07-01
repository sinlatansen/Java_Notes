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

## Java OOP

### static 与 public

- 静态方法：可以被类名直接调用，无需实例化对象。
- 静态变量：可以被类名直接访问，无需实例化对象。
- 实例方法：只能被实例对象调用，需要实例化对象。
- 实例变量：只能被实例对象访问，需要实例化对象。

### 构造函数

- 名称与类名相同。
- 无返回值，但可以抛出异常。
- 可以重载。

### 修饰符

#### 访问修饰符

1. 类

   - `public`：公共访问权限，可以在任何地方访问。
   - 默认：包访问权限，只能在同一个包内访问。

2. 属性，方法，构造函数

   - `public`：公共访问权限，可以在任何地方访问。
   - `protected`：受保护访问权限，只能在同一个包内和子类中访问。
   - `default`：默认访问权限，只能在同一个包内访问。
   - `private`：私有访问权限，只能在当前类中访问。

#### 其他修饰符

1. 类

   - `final`：不可被继承。
   - `abstract`：抽象类，不能实例化。

2. 属性，方法
   - `final`：不可被重写。
   - `static`：静态方法，可以被类名直接调用。
   - `abstract`：抽象方法，不能实例化。
   - `transient`：非持久化属性。
   - `synchronized`：同步方法，同一时间只能有一个线程执行。
   - `volatile`：线程安全的属性。

### 封装

将类的变量、属性声明为 private，不允许外部代码直接访问，只能通过公共方法来访问。

```java
public class Hello {
   private int x;

   public Hello() {
      x = 9;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getX() {
      return x;
   }
}
```

### 包和 API

使用库中的类或包。

```java
import package.name.ClassName;
import package.name.*;
```

创建包。

```java
package package.name;
```

编译包。

```bash
javac -d . package/name/*.java
```

### 继承

```java
class World extends Hello {
   public World() {
      setX(10);
   }
}
```

`final`修饰的类不能被继承。
`final`修饰符可以防止子类重写父类方法。

### 多态

子类和父类都有同名的方法，调用子类的方法时，实际调用的是子类的方法。

### 内部类

```java
class Outer {
   private int x = 10;

   class Inner {
      public void print() {
         System.out.println(x);
      }
   }
}

public class Main {
   public static void main(String[] args) {
      Outer outer = new Outer();
      Outer.Inner inner = outer.new Inner();
      inner.print();
   }
}

```

`private`或者`protected`修饰的内部类无法被外部类访问。
`static`修饰的内部类，可以直接访问而无需实例化外部类。

内部类可以访问外部类的所有成员，包括私有成员。
`static`内部类则不能访问外部类的非静态成员。

```java
class OuterClass {
  int x = 10;

  class InnerClass {
    public int myInnerMethod() {
      return x;
    }
  }
}

public class Main {
  public static void main(String[] args) {
    OuterClass myOuter = new OuterClass();
    OuterClass.InnerClass myInner = myOuter.new InnerClass();
    System.out.println(myInner.myInnerMethod());
  }
}

// Outputs 10
```

### 接口

接口`interface`是一个完全的抽象类。

```java
interface Animal {
   public void eat();
   public void sleep();
}

class Pig implements Animal {
   public void eat() {
      System.out.println("Pig is eating");
   }
   public void sleep() {
      System.out.println("Pig is sleeping");
   }
}
```

实现接口时必须重写所有方法。

一个类只能继承一个父类，但是可以实现多个接口。

```java
interface A {
  public void method1();
}

interface B {
  public void method2();
}

class C implements A, B {
  public void method1() {
    System.out.println("Method 1");
  }

  public void method2() {
    System.out.println("Method 2");
  }
}
```

### 枚举

枚举`enum`是一个特殊的类，它的所有实例都是唯一的。

用到`values()`方法来循环遍历枚举。

```java
for(Color c : Color.values()) {
   System.out.println(c);
}
```

### 用户输入（Scanner）

```java
import java.util.Scanner;

public class Hello {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter your name: ");

      String name = input.nextLine();
      System.out.println("Hello, " + name + "!");
   }
}
```

| 方法          | 作用                   |
| ------------- | ---------------------- |
| nextBoolean() | 读取下一个布尔值       |
| nextByte()    | 读取下一个字节值       |
| nextDouble()  | 读取下一个双精度浮点值 |
| nextFloat()   | 读取下一个单精度浮点值 |
| nextInt()     | 读取下一个整数值       |
| nextLong()    | 读取下一个长整数值     |
| nextLine()    | 读取下一行文本         |
| nextShort()   | 读取下一个短整数值     |

### 日期与时间

导入`java.time`包。

| 类名              | 作用           |
| ----------------- | -------------- |
| LocalDate         | 日期           |
| LocalTime         | 时间           |
| LocalDateTime     | 日期时间       |
| ZonedDateTime     | 带时区的时间   |
| DateTimeFormatter | 格式化日期时间 |

| Value               | Example             |
| ------------------- | ------------------- |
| yyyy-MM-dd          | 2021-06-26          |
| HH:mm:ss            | 10:15:30            |
| yyyy-MM-dd HH:mm:ss | 2021-06-26 10:15:30 |
| E, MMM d, yyyy      | Wed, Jun 26, 2021   |
