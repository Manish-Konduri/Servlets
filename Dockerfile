FROM ubuntu:latest
RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install openjdk-8-jdk wget

RUN wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.34/bin/apache-tomcat-8.5.34.tar.gz
RUN tar xvfz apache-tomcat-8.5.34.tar.gz

EXPOSE 8080
ADD tasks-webapp.war /apache-tomcat-8.5.34/webapps/tasks-webapp.war
CMD /apache-tomcat-8.5.34/bin/catalina.sh run
