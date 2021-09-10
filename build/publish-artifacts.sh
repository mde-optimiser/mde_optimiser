#!/usr/bin/env bash

#Fail at the first error
set -e

if [ $GITHUB_BASE_REF != "master" ] && [ $GITHUB_BASE_REF != "develop" ]; then
    echo "$GITHUB_BASE_REF is not a deployment branch so no deployment necessary."
    exit 0
fi

echo "Deployment branch detected as $GITHUB_BASE_REF. Deploying code to repositories"


cd $RUNNER_WORKSPACE

echo "Preparing to publish to repository: "
pwd

rm -rf gh-pages

git clone -b gh-pages https://github.com/mde-optimiser/mde_optimiser.git gh-pages

cd gh-pages

echo "Preparing current branch release folder for branch $GITHUB_BASE_REF"

REPO_OUTPUT="downloads/$GITHUB_BASE_REF"

if [ ! -d "$REPO_OUTPUT" ]; then
  mkdir -p $REPO_OUTPUT
fi

rm -rf $REPO_OUTPUT/*

echo "Preparing Maven repository"

REPO_OUTPUT_MAVEN="$REPO_OUTPUT/maven"

mkdir -p $REPO_OUTPUT_MAVEN
mv ./mde_optimiser/repositories/output/m2/m2.mdeoptimiser.repository/* $REPO_OUTPUT_MAVEN/

echo "Preparing P2 repository for MDEO Libs"

REPO_OUTPUT_P2_LIBS="$REPO_OUTPUT/p2/artifacts/libraries"

mkdir -p $REPO_OUTPUT_P2_LIBS
mv ./mde_optimiser/repositories/output/p2/p2.mdeoptimiser.repository/* $REPO_OUTPUT_P2_LIBS

echo "Preparing P2 repository for Eclipse UI"

REPO_OUTPUT_P2_ECLIPSE="$REPO_OUTPUT/p2/artifacts/eclipse"

mkdir -p $REPO_OUTPUT_P2_ECLIPSE
mv ./mde_optimiser/interfaces/eclipse/src/uk.ac.kcl.inf.mdeoptimiser.interfaces.eclipse.repository/target/* $REPO_OUTPUT_P2_ECLIPSE

echo "Preparing Eclipse P2 composite update site"

REPO_OUTPUT_P2_COMPOSITE="downloads/p2/"

mkdir -p $REPO_OUTPUT_P2_COMPOSITE
cp -r ./mde_optimiser/repositories/composite/* $REPO_OUTPUT_P2_COMPOSITE

echo "Preparation completed."

git remote set-url origin https://x-access-token:${{ secrets.GITHUB_TOKEN }}@github.com/$GITHUB_REPOSITORY
          
git add --all

git config user.name "Github CI"
git config user.email "ci@mde-optimiser.github.io"

git commit -m "Published build id: $GITHUB_RUN_NUMBER"

#git push origin gh-pages

echo "Artifacts deployed to $GITHUB_REPOSITORY repository in github.com."
