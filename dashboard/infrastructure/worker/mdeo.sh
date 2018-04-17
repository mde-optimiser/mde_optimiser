#!/bin/bash

cd /var/app/mdeo && mvn clean compile

export MDEO_CLASSPATH=`find ~/.m2 -name *.jar | python -c "import sys; print ':'.join(sys.stdin.read().splitlines())"`

java -classpath "$MDEO_CLASSPATH:/var/app/mdeo/plugins/uk.ac.kcl.mdeoptimise/target/classes:/var/app/mdeo/plugins/uk.ac.kcl.mdeoptimise.ui/target/classes:/var/app/mdeo/plugins/uk.ac.kcl.mdeoptimise.tests/target/classes:/var/app/mdeo/dashboard/uk.ac.kcl.mdeoptimise.dashboard.api/target/classes" uk.ac.kcl.ui.launch.RunOptimisation /var/app/case_studies/cra/ /var/app/case_studies/cra/mutation-solution.mopt
