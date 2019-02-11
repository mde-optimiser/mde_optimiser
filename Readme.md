## MDE Optimiser

This is the main repository for the MDEOptimiser tool.

### Build status

DEV Build

![DEVELOP branch build status](https://travis-ci.org/mde-optimiser/mde_optimiser.svg?branch=develop)

MASTER Build

![MASTER branch build status](https://travis-ci.org/mde-optimiser/mde_optimiser.svg?branch=master)




## Notes for new build structure

All Maven projects are organised in a single pom file. ANT is used to build these projects first, then the Eclipse interface is built by using the published artifacts from this build.

We separate local and pipeline builds by using a different build number for local builds.

Local builds: YYYY.mm.dd.hhmmss

CI builds: YYYY.mm.dd.build-no


Build order:

1. Eclipse bundles are converted to a maven repository stored in the repositories/output directory
2. Maven projects are compiled and deployed to a maven repository. Eclipse bundles are loaded as a maven repository from the repositories/output directory
3. The maven repository is converted to a P2 repository and the output is stored in repositories/output
4. The Eclipse projects are built. Maven artifacts are loaded as Eclipse bundles from the P2 repository.

## To create a new project

### Using Java Only

mvn archetype:generate
	-DgroupId={project-packaging} \
	-DartifactId={project-name} \
	-DarchetypeArtifactId=maven-archetype-quickstart \
	-DinteractiveMode=false

More Maven project configuration settings example site:
https://www.mkyong.com/maven/how-to-create-a-java-project-with-maven/

### Using Java and XTend

mvn archetype:generate
-DgroupId={project-packaging} \
-DartifactId={project-name} \
-DarchetypeGroupId=org.eclipse.xtend \
-DarchetypeArtifactId=xtend-archetype

More XTend Maven configuration settings: https://www.eclipse.org/xtend/download.html
