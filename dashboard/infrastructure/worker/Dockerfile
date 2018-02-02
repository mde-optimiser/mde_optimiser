FROM openjdk:8-jdk

COPY entrypoint.sh /

COPY mdeo.sh /

COPY ./case_studies/ /var/app/case_studies

ENTRYPOINT ["/mdeo.sh"]
#ENTRYPOINT ["/entrypoint.sh"]
