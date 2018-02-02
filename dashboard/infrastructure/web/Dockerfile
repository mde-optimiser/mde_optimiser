FROM java:8 
 
RUN  \
  export DEBIAN_FRONTEND=noninteractive && \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get install -y wget curl maven
 
  RUN mkdir var/app/mdeo/uk.ac.kcl.mdeoptimise.web
 
  WORKDIR /var/app/mdeo/uk.ac.kcl.mdeoptimise.web
 
  CMD ["mvn", "package"]