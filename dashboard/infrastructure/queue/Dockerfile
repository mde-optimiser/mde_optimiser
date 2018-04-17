FROM java:8

RUN mkdir -p /var/app/current && cd /var/app/current \
	&& wget http://archive.apache.org/dist/qpid/java/6.1.0/binaries/qpid-broker-6.1.0-bin.tar.gz \
	&& tar -xzvf qpid-broker-6.1.0-bin.tar.gz

#Copy the default queue config to the qpid config work directory
COPY initial-config.json /root/default/config/default.json

COPY entrypoint.sh /entrypoint.sh

EXPOSE 5672 8080

ENTRYPOINT ["/var/app/current/qpid-broker/6.1.0/bin/qpid-server"]
#ENTRYPOINT ["/entrypoint.sh"]