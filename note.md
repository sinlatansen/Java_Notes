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

### ArrayList

一个大小可变的数组。

```java
import java.util.ArrayList;
import java.util.Collections;

public class Hello {
   public static void main(String[] args) {
      ArrayList<String> cars = new ArrayList<String>();
      cars.add(0, "Toyota");
      cars.add("Honda");
      cars.add("BMW");

      System.out.println("Cars: " + cars);

      System.out.println("First car: " + cars.get(0));

      cars.set(0, "Tesla");
      System.out.println("Cars: " + cars);

      cars.remove(0);
      System.out.println("Cars: " + cars);

      System.out.println("Size: " + cars.size());

      for (String car : cars) {
         System.out.println(car);
      }

      Collections.sort(cars);
      System.out.println("Sorted Cars: " + cars);

      cars.clear();
      System.out.println("Cars: " + cars);
   }
}
```

### LinkedList

接口和 ArrayList 类似，都具有 List 接口，但是原理与 ArrayList 不同。
其本质是双向链表。

| Method                | Description                   |
| --------------------- | ----------------------------- |
| addFirst(E e)         | 在链表头部添加元素 e          |
| addLast(E e)          | 在链表尾部添加元素 e          |
| getFirst()            | 获取链表头部元素              |
| getLast()             | 获取链表尾部元素              |
| removeFirst()         | 删除链表头部元素              |
| removeLast()          | 删除链表尾部元素              |
| add(int index, E e)   | 在指定位置 index 添加元素 e   |
| remove(int index)     | 删除指定位置 index 的元素     |
| set(int index, E e)   | 设置指定位置 index 的元素为 e |
| indexOf(Object o)     | 返回第一个出现的元素的索引    |
| lastIndexOf(Object o) | 返回最后一个出现的元素的索引  |

### HashMap

键值对映射。

```java
import java.util.HashMap;

public class Hello {
   public static void main(String[] args) {
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("England", "London");
      map.put("France", "Paris");
      map.put("Germany", "Berlin");
      System.out.println(map);

      /* get the value of a key */
      System.out.println(map.get("France"));

      /* remove a key-value pair */
      map.remove("France");

      /* size of the map */
      System.out.println(map.size());

      /* 打印空行 */
      System.out.println();

      /* for loop */
      for (String value : map.values()) {
         System.out.println(value);
      }

      /* 打印空行 */
      System.out.println();

      for (String key : map.keySet()) {
         System.out.println(key + " : " + map.get(key));
      }

      /* remove all key-value pairs */
      map.clear();
   }
}
```

### HashSet

无序集合，元素不可重复。

```java
import java.util.HashSet;

public class Hello {
   public static void main(String[] args) {
      HashSet<String> cars = new HashSet<String>();
      cars.add("BMW");
      cars.add("Audi");
      cars.add("Mercedes");
      System.out.println(cars + "\n");

      /* check if a specific element is present in the set */
      if (cars.contains("BMW")) {
         System.out.println("BMW is present in the set");
      }

      /* remove an element from the set */
      cars.remove("Audi");
      System.out.println(cars + "\n");

      /* size of the set */
      System.out.println("Size of the set: " + cars.size() + "\n");

      /* for loop */
      for (String car : cars) {
         System.out.println(car);
      }

      /* remove all elements from the set */
      cars.clear();
      System.out.println(cars + "\n");

   }
}
```

### Iterator 迭代器

迭代器是一个可以用于循环集合的对象。

```java
import java.util.ArrayList;
import java.util.Iterator;

public class Hello {
   public static void main(String[] args) {
      ArrayList<Integer> numbers = new ArrayList<Integer>();
      numbers.add(12);
      numbers.add(8);
      numbers.add(2);
      numbers.add(23);

      /* get the iterator */
      Iterator<Integer> it = numbers.iterator();

      /* print the first element */
      // System.out.println("First element: " + it.next());

      /* loop by iterating through the elements */
      while (it.hasNext()) {
         Integer num = it.next();
         if (num > 10) {
            it.remove();
         } else {
            System.out.println(num);
         }
      }
   }
}
```

### Wrapper Classes 包装类

包装类可以将基本类型转换为对象。

| Wrapper Class | Primitive Type |
| ------------- | -------------- |
| Boolean       | boolean        |
| Byte          | byte           |
| Character     | char           |
| Double        | double         |
| Float         | float          |
| Integer       | int            |
| Long          | long           |
| Short         | short          |

有时候必须使用包装类，例如使用 ArrayList 存储基本类型。

```java
ArrayList<int> numbers= new ArrayList<int>(); // error
ArrayList<Integer> numbers = new ArrayList<Integer>(); // correct
```

```java
public class Hello {
   public static void main(String[] args) {
      Integer i = 10;
      Double d = 20.0;
      System.out.println(i);
      System.out.println(d);

      System.out.println(i.intValue());
      System.out.println(d.doubleValue());

      System.out.println(i.toString());
   }
}
```

### Exception 异常

try and catch 语句用于处理异常。

```java
try {
    // block of code to try
}
catch(ExceptionType e) {
    // block of code to handle the exception
}
finally {
    // block of code to execute regardless of exception
}
```

```java
public class Hello {
   public static void main(String[] args) {
      try {
         int[] nums = { 1, 2, 3 };
         System.out.println(nums[5]);
      } catch (Exception e) {
         System.out.println("Exception occurred");
      } finally {
         System.out.println("Finally block executed");
      }
   }
}
```

throws 语句用于声明一个方法可能抛出的异常。

```java
public class Hello {
   static void checkAge(int age) {
      if (age < 18) {
         throw new ArithmeticException("Age must be 18 or above");
      } else {
         System.out.println("Age is valid");
      }
   }

   public static void main(String[] args) {
      checkAge(5);
   }
}
```

### 正则表达式

可以导入`java.util.regex`包。

- `Pattern`：用于创建正则表达式对象。
- `Matcher`：用于匹配字符串。
- `PatternSyntaxException`：用于处理正则表达式语法错误。

`Pattern.compile()`的第一个参数：
| Flag | Description |
| ---- | ----------- |
| [abc]|匹配 a、b 或 c |
| [^abc]|匹配除了 a、b、c 之外的任何字符 |
| [a-z]|匹配任何小写字母 |
| [A-Z]|匹配任何大写字母 |
| [0-9]|匹配任何数字 |

`Pattern.compile()`的第二个参数：

- Pattern.CASE_INSENSITIVE：忽略大小写。
- Pattern.LITERAL：不进行转义。
- Pattern.MULTILINE：多行模式。
- Pattern.DOTALL：点匹配所有字符。

元字符

- `.`：匹配任意字符。
- `*`：匹配前面的字符零次或多次。
- `+`：匹配前面的字符一次或多次。
- `?`：匹配前面的字符零次或一次。
- `{n}`：匹配前面的字符 n 次。
- `{n,}`：匹配前面的字符至少 n 次。
- `{n,m}`：匹配前面的字符至少 n 次，至多 m 次。
- `|`：匹配前面的字符或后面的字符。
- `()`：创建子表达式。
- `\`：转义字符。
- `^`：匹配字符串的开始。
- `$`：匹配字符串的结束。
- `\b`：匹配单词边界。
- `\B`：匹配非单词边界。
- `\d`：匹配数字。
- `\D`：匹配非数字。
- `\s`：匹配空白字符。
- `\S`：匹配非空白字符。
- `\w`：匹配字母、数字或下划线。
- `\W`：匹配非字母、数字或下划线。

### 线程

线程可以在后台运行代码，而无需中断主程序。

创建线程的两种方式：

1. 继承`Thread`类。

   ```java
   public class Hello extends Thread {
      public static void main(String[] args) {
         Hello thread = new Hello();
         thread.start();
         System.out.println("Hello, World!");
      }

      public void run() {
         System.out.println("Hello, Thread!");
      }
   }
   ```

2. 实现`Runnable`接口。

   ```java
   public class Hello implements Runnable {
      public static void main(String[] args) {
         Hello h = new Hello();
         Thread thread = new Thread(h);
         thread.start();
         System.out.println("Hello, World!");
      }

      public void run() {
         System.out.println("Hello, Thread!");
      }
   }
   ```

#### 并发问题

多个线程同时访问同一个资源时，可能会出现数据不一致的问题。

```java
public class Hello extends Thread {
   public static int amount = 0;

   public static void main(String[] args) {
      Hello thread = new Hello();
      thread.start();
      System.out.println(amount);
      amount++;
      System.out.println(amount);
   }

   public void run() {
      amount++;
   }
}
```

解决办法：
使用`isAlive()`方法判断线程是否存活。

```java
public class Hello extends Thread {
   public static int amount = 0;

   public static void main(String[] args) {
      Hello thread = new Hello();
      thread.start();
      // Wait for the thread to finish
      while (thread.isAlive()) {
         System.out.println("Waiting...");
      }
      // Update amount and print its value
      System.out.println("Main: " + amount);
      amount++;
      System.out.println("Main: " + amount);
   }

   public void run() {
      amount++;
   }
}

/**
 * Output:
 * Waiting...
 * Main: 1
 * Main: 2
 */

```
