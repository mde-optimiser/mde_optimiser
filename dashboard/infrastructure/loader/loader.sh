#!/bin/bash

cd /var/app/loader && mvn clean compile

export LOADER_CLASSPATH=`find ~/.m2 -name *.jar | python -c "import sys; print ':'.join(sys.stdin.read().splitlines())"`

java -classpath "$LOADER_CLASSPATH:/var/app/loader/target/classes:/var/app/loader/lib/*" uk.ac.kcl.mdeoptimise.dataloader.DataLoader