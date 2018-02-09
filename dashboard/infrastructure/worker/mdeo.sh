#!/bin/bash

java -classpath "/var/app/plugins/*:/var/app/mdeo/uk.ac.kcl.mdeoptimise/target/classes:/var/app/mdeo/uk.ac.kcl.mdeoptimise.lib/*:/var/app/mdeo/uk.ac.kcl.mdeoptimise.ui/target/classes:/var/app/mdeo/uk.ac.kcl.mdeoptimise.tests/target/classes:/var/app/mdeo/uk.ac.kcl.mdeoptimise.dashboard.api/target/classes" uk.ac.kcl.ui.launch.RunOptimisation /var/app/case_studies/cra/ /var/app/case_studies/cra/mutation-solution.mopt
