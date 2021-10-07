# Counter 关于代码统计的 maven 插件

**开发进度**

- [x] 代码行数统计（支持自定义文件类型）
- [ ] 代码行数统计测试代码（根据插件功能，编写自动化测试）
- [ ] 代码作者统计（指定作者姓名，统计该作者编写的文件数量）
- [ ] 代码作者统计测试代码（根据插件功能，编写自动化测试）

## 代码行数统计

### 功能

扫描 src/main 和 src/test 下的所有 `.java` 文件，统计文件数量和代码行数。

### 统计的文件类型配置

支持在 pom 中配置，以及传递环境变量配置

**pom 配置如下**  

```xml

<plugin>
    <groupId>org.example</groupId>
    <artifactId>counter-maven-plugin</artifactId>
    <version>1.0</version>
    <configuration>
        <includes>
            <param>pdf</param>
            <param>txt</param>
        </includes>
    </configuration>
</plugin>
```
该配置表示插件仅统计 pdf 和 txt 类型的文件

**环境变量传递**

在需要统计的项目上执行以下命令

```shell
mvn org.example:counter-maven-plugin:1.0:countCode -Dcount.file.type=type1,type2,..
```
多个文件类型以逗号分隔即可

### 简化命令

由于并非 maven 官方插件，因此 groupId 无法直接省略。需要在 `${user.home}/.m2/settings.xml` 添加以下信息，若电脑不存在该目录，也可以在 maven 安装目录 `conf/setting.xml` 中配置。

```xml
<pluginGroups>
  <pluginGroup>org.example</pluginGroup>
</pluginGroups>
```
由于该插件编写，遵循 maven 规范，因此可以使用插件前缀执行命令，最终简化的插件执行命令如下：

```shell
mvn counter:countCode
```

### 安装配置

直接 clone 该项目，在项目中执行如下命令，即可安装插件到本地

```shell
mvn install
```
命令执行完成之后，即可在需要统计的项目中引用该插件

```xml
<plugin>
    <groupId>org.example</groupId>
    <artifactId>counter-maven-plugin</artifactId>
    <version>1.0</version>
</plugin>
```
需要自定义统计的文件类型，参考上述配置方法，没有配置，默认统计 `.java` 文件 。


