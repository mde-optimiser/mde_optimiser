#!/usr/bin/env bash

mvn install:install-file -Dfile=../uk.ac.kcl.mdeoptimiser.standalone/dist/MDEOptimiser.jar -DgroupId=uk.ac.kcl.mdeoptimise -DartifactId=standalone -Dversion=1.0 -Dpackaging=jar
