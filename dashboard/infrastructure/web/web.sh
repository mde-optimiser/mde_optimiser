#!/bin/bash

cd /var/app/web && mvn clean compile && mvn package

#export LOADER_CLASSPATH=`find ~/.m2 -name *.jar | python -c "import sys; print ':'.join(sys.stdin.read().splitlines())"`

#java -classpath "/var/app/loader/target/classes" uk.ac.kcl.mdeoptimise.dataloader.DataLoader