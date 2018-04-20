FROM java:8

COPY entrypoint.sh /
COPY web.sh /

# Install Jetty
RUN wget -O /opt/jetty.tar.gz "http://eclipse.org/downloads/download.php?file=/jetty/9.0.7.v20131107/dist/jetty-distribution-9.0.7.v20131107.tar.gz&r=1"
RUN tar -xvf /opt/jetty.tar.gz -C /opt/
RUN rm /opt/jetty.tar.gz
RUN mv /opt/jetty-distribution-9.0.7.v20131107 /opt/jetty
RUN rm -rf /opt/jetty/webapps.demo
RUN useradd jetty -U -s /bin/false
RUN chown -R jetty:jetty /opt/jetty

# Ideally at this point we should install Maven and package the web app to create a new .war file (see web.sh)

#ENTRYPOINT ["/entrypoint.sh"]

# When running docker-compose build with the following line it returns an ERROR (no such file or directory)
#ADD ../../var/web/uk.ac.kcl.mdeoptimise.statusdashboard/target/statusdashboard-1.0-SNAPSHOT.war /opt/jetty/webapps/statusdashboard-1.0-SNAPSHOT.war

# When running docker-compose build with the following line it returns an ERROR (Forbidden path outside the build context:)
ADD statusdashboard-1.0-SNAPSHOT.war /opt/jetty/webapps/app.war

# Run Jetty
EXPOSE 8080
CMD ["java", "-Djetty.home=/opt/jetty", "-jar", "/opt/jetty/start.jar"]