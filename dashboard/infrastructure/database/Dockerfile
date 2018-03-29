FROM java:8

ENV DOWNLOAD http://www.h2database.com/h2-2015-10-11.zip
ENV DATA_DIR /opt/h2-data

RUN curl ${DOWNLOAD} -o h2.zip \
    && unzip h2.zip -d /opt/ \
    && rm h2.zip \
    && mkdir -p ${DATA_DIR}

EXPOSE 8082 9092

CMD java -cp /opt/h2/bin/h2*.jar org.h2.tools.Server \
 	-web -webAllowOthers -webPort 8082 \
 	-tcp -tcpAllowOthers -tcpPort 9092
   
VOLUME ${DATA_DIR}

