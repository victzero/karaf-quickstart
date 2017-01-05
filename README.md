# karaf-quickstart
## 基本环境搭建
* apache-karaf-4.0.8
* apache-maven-3.3.9
* java version "1.8.0_91"

国内推荐使用aliyun的maven镜像:
```
	<mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
```

## karaf常用命令
* `bundle:watch *`开发时监听SNAPSHOT版本的bundle,并及时自动更新


## 1. 初识karaf,需手动安装依赖包
目标:

* 基于cxf提供rest接口,返回json数据
* 基于blueprint跨bundle调用bean

**安装cxf,支持rest**

	feature:repo-add cxf 3.1.5
	feature:install cxf http
	#feature:install cxf-jaxrs http-whiteboard

** 安装jackson依赖,使cxf支持json返回**

	install mvn:org.codehaus.jackson/jackson-core-asl/1.9.5
	install mvn:org.codehaus.jackson/jackson-mapper-asl/1.9.5
	install mvn:org.codehaus.jackson/jackson-jaxrs/1.9.5

	install mvn:org.slf4j/slf4j-api/1.6.1

	install mvn:org.apache.aries.blueprint/org.apache.aries.blueprint.annotation.api/0.3

** 安装bundle,进行测试,测试地址:**

	install mvn:me.ezjs.quickstart.karaf/department-model/1.0-SNAPSHOT
	install mvn:me.ezjs.quickstart.karaf/department-server/1.0-SNAPSHOT

## 2. 创建feature,"自动"安装依赖包
通过feature方式安装bundle

	feature:repo-add mvn:me.ezjs.quickstart.karaf/department-feature/1.0-SNAPSHOT/xml/features

## 3. 连接Mysql数据库
参考[StackOverflow问答](http://stackoverflow.com/questions/31004170/com-mysql-jdbc-driver-not-found-by-bundle-in-karaf#answers),内容引用如下.

> Database drivers in OSGi are always a bit difficult as the most common approach outside OSGi using DriverManager is not really suitable for OSGi.

> The best approach in OSGi is to use a DataSourceFactory which is standardized by the OSGi alliance. Some database drivers already offer this. For others pax-jdbc provides an adapter. For mysql the second case applies.

即:在OSGI中应该使用符合OSGI联盟标准的DataSourceFactory.

#### 在karaf控制台操作
使用pax-jdbc连接数据库,以OSGI服务的形式提供,并在控制台测试

主要参考资料如下:

* [配置文件 Create DataSource from config](https://ops4j1.jira.com/wiki/display/PAXJDBC/Create+DataSource+from+config)
* [连接池 Pooling and XA support for DataSourceFactory](https://ops4j1.jira.com/wiki/display/PAXJDBC/Pooling+and+XA+support+for+DataSourceFactory)
* [Apache Karaf Tutorial Part 6 - Database Access](http://www.liquid-reality.de/display/liquid/2012/01/13/Apache+Karaf+Tutorial+Part+6+-+Database+Access)

在%KARAF_HOME%/etc目录下创建配置文件`org.ops4j.datasource-mysql-test.cfg`,内容如下
```
	osgi.jdbc.driver.class=com.mysql.jdbc.Driver
	osgi.jdbc.driver.name=mysql
	databaseName=test
	user=root
	password=root
	dataSourceName=test-mysql
```

在karaf控制台安装相关feature,并查看DataSourceFactory

	feature:repo-add pax-jdbc 0.8.0
	feature:install pax-jdbc-mysql pax-jdbc-config
	service:list DataSourceFactory

安装jdbc后,即可直接在karaf控制台执行sql

	feature:install jdbc
	jdbc:ds-list
	jdbc:tables test-mysql
	jdbc:execute test-mysql create table person (name varchar(100), twittername varchar(100))
	jdbc:execute test-mysql insert into person (name, twittername) values ('Christian Schneider', '@schneider_chris')
	jdbc:query test-mysql select * from person

#### 在bundle中使用camel-mybatis连接MySQL数据库,同样基于pax-jdbc创建的DataSourceFactory

参考Apache camel的[camel-examples-mybatis](https://github.com/apache/camel/tree/master/examples/camel-example-mybatis)`apache-camel-2.8.1/examples/camel-example-mybatis`, 其使用camel-mybatis连接Derby数据库,官方示例执行命令如下:

	feature:repo-add camel 2.18.1
	feature:install camel camel-mybatis
	bundle:install mvn:org.apache.derby/derby/10.11.1.1

安装测试bundle

	install mvn:me.ezjs.quickstart.karaf/department-model/1.0-SNAPSHOT
	install mvn:me.ezjs.quickstart.karaf/department-server/1.0-SNAPSHOT

	log:tail

**修改配置文件支持MySQL**

修改配置文件`department_model/src/main/resources/SqlMapConfig.xml`,即可连接MySQL数据库
> 配置参数参考网址[BasicDataSource Configuration Parameters](http://commons.apache.org/proper/commons-dbcp/configuration.html)
```
	<!--<property name="driver" value="org.apache.derby.jdbc.EmbeddedDriver"/>-->
	<!--<property name="url" value="jdbc:derby:memory:mybatis;create=true"/>-->
	<property name="driver" value="com.mysql.jdbc.Driver"/>
	<property name="url" value="jdbc:mysql://127.0.0.1:3306/test"/>
	<property name="username" value="root"/>
	<property name="password" value="root"/>
```

安装依赖,并测试

	feature:repo-add camel 2.18.1
	feature:install camel camel-mybatis

	feature:repo-add pax-jdbc 0.8.0
	feature:install pax-jdbc-mysql

	install mvn:me.ezjs.quickstart.karaf/department-model/1.0-SNAPSHOT
	install mvn:me.ezjs.quickstart.karaf/department-server/1.0-SNAPSHOT

	log:tail






