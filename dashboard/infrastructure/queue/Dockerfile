FROM java:8

RUN mkdir -p /var/app/current && cd /var/app/current \
	&& wget http://archive.apache.org/dist/qpid/java/6.1.0/binaries/qpid-broker-6.1.0-bin.tar.gz \
	&& tar -xzvf qpid-broker-6.1.0-bin.tar.gz

EXPOSE 5672 8080

COPY entrypoint.sh /

#ENTRYPOINT ["/var/app/current/qpid-broker/6.1.0/bin/aqpid-server]
ENTRYPOINT ["/entrypoint.sh"]