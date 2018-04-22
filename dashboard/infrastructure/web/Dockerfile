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

EXPOSE 8080

ENTRYPOINT ["/web.sh"]
