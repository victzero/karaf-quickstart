# karaf-quickstart
karaf-quickstart


feature:repo-add cxf 3.1.5
feature:install cxf http
#feature:install cxf-jaxrs http-whiteboard

#安装依赖
install mvn:org.codehaus.jackson/jackson-core-asl/1.9.5
install mvn:org.codehaus.jackson/jackson-mapper-asl/1.9.5
install mvn:org.codehaus.jackson/jackson-jaxrs/1.9.5

install mvn:org.slf4j/slf4j-api/1.6.1

install mvn:org.apache.aries.blueprint/org.apache.aries.blueprint.annotation.api/0.3


install mvn:me.ezjs.quickstart.karaf/department-model/1.0-SNAPSHOT
install mvn:me.ezjs.quickstart.karaf/department-server/1.0-SNAPSHOT


bundle:watch *

feature:repo-add mvn:me.ezjs.quickstart.karaf/department-feature/1.0-SNAPSHOT/xml/features


连接数据库方案１：
使用pax-jdbc链接数据库,将其以OSGI服务的形式提供
Database drivers in OSGi are always a bit difficult as the most common approach outside OSGi using DriverManager is not really suitable for OSGi.

The best approach in OSGi is to use a DataSourceFactory which is standardized by the OSGi alliance. Some database drivers already offer this. For others pax-jdbc provides an adapter. For mysql the second case applies.
配置参考: https://ops4j1.jira.com/wiki/display/PAXJDBC/Create+DataSource+from+config
连接池配置参考:https://ops4j1.jira.com/wiki/display/PAXJDBC/Pooling+and+XA+support+for+DataSourceFactory
参考文档:http://www.liquid-reality.de/display/liquid/2012/01/13/Apache+Karaf+Tutorial+Part+6+-+Database+Access

#pax-jdbc-h2
feature:repo-add mvn:org.ops4j.pax.jdbc/pax-jdbc-features/0.8.0/xml/features
feature:install transaction jndi pax-jdbc-h2 pax-jdbc-pool-dbcp2 pax-jdbc-config
service:list DataSourceFactory

#pax-jdbc-mysql
我使用的配置
'''
osgi.jdbc.driver.class=com.mysql.jdbc.Driver
osgi.jdbc.driver.name=mysql
databaseName=test
user=root
password=root
dataSourceName=test-mysql
'''
feature:repo-add pax-jdbc 0.8.0
feature:install pax-jdbc-mysql pax-jdbc-config
service:list DataSourceFactory

-可直接在karaf控制台执行
feature:install jdbc
jdbc:ds-list
jdbc:tables test-mysql
jdbc:execute test-mysql create table person (name varchar(100), twittername varchar(100))
jdbc:execute test-mysql insert into person (name, twittername) values ('Christian Schneider', '@schneider_chris')
jdbc:query test-mysql select * from person

连接数据库方案２：
使用camel-mybatis
feature:repo-add pax-jdbc 0.8.0
feature:install pax-jdbc-mysql
