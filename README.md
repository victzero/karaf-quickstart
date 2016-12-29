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