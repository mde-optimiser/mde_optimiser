#!/bin/bash

# Install maven 3.3.9
wget -O /tmp/apache-maven-3.3.9-bin.tar.gz http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz && \
    tar xzf /tmp/apache-maven-3.3.9-bin.tar.gz -C /opt/ && \
    ln -s /opt/apache-maven-3.3.9 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/local/bin  && \
    rm -f /tmp/apache-maven-3.3.9-bin.tar.gz

cd /var/app/web && mvn clean dependency:copy-dependencies package
cp /var/app/web/target/statusdashboard-1.0-SNAPSHOT.war /opt/jetty/webapps/

java -Djetty.home=/opt/jetty -jar /opt/jetty/start.jar