FROM centos:7

MAINTAINER alex+git@onboot.org

RUN yum -y --enablerepo=extras install epel-release

RUN yum install -y java-1.8.0-openjdk \
    python-pip \
    cronie

RUN pip install awscli --upgrade --user

ENV PATH "/root/.local/bin:$PATH"

RUN mkdir -p /var/app/current/
RUN touch /etc/crontab /etc/cron.*/*
# Configure the update results cron job
ADD files/crontab /etc/cron.d/update-results-cron
ADD files/update-results.sh /opt/update-results.sh
RUN chmod 0644 /etc/cron.d/update-results-cron
RUN touch /var/log/update-results-cron.log
RUN chmod +x /opt/update-results.sh

# Copy solvers to shell path
COPY ./tools/argmatsat/argmat-sat.out /usr/bin/argmatsat
COPY ./tools/eqargsolver/eqargsolver-2.76 /usr/bin/eqargsolver

