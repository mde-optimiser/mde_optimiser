#!/usr/bin/env bash

#Fail at the first error
set -e

if [ $TRAVIS_BRANCH != "master" ] && [ $TRAVIS_BRANCH != "develop" ]; then
    echo "$TRAVIS_BRANCH is not a deployment branch so no deployment necessary."
    exit 0
fi

echo "Deployment branch detected as $TRAVIS_BRANCH. Deploying code to repositories"

echo "Setting up ssh keys in directory: "
pwd

ENCRYPTED_KEY_VAR="encrypted_${ENCRYPTION_LABEL}_key"
ENCRYPTED_IV_VAR="encrypted_${ENCRYPTION_LABEL}_iv"
ENCRYPTED_KEY=${!ENCRYPTED_KEY_VAR}
ENCRYPTED_IV=${!ENCRYPTED_IV_VAR}

openssl aes-256-cbc -K $ENCRYPTED_KEY -iv $ENCRYPTED_IV -in $DEPLOY_KEY -out deploy_key -d

eval `ssh-agent -s`

chmod 600 deploy_key
ssh-add deploy_key


cd $TRAVIS_BUILD_DIR/..

echo "Preparing to publish to repository: "
pwd

rm -rf gh-pages

git clone -b gh-pages git@github.com:$TRAVIS_REPO_SLUG gh-pages

cd gh-pages

echo "Preparing current branch release folder for branch $TRAVIS_BRANCH"

REPO_OUTPUT="downloads/$TRAVIS_BRANCH"

if [ ! -d "$REPO_OUTPUT" ]; then
  mkdir -p $REPO_OUTPUT
fi

rm -rf $REPO_OUTPUT/*

echo "Preparing Maven repository"

REPO_OUTPUT_MAVEN="$REPO_OUTPUT/maven"

mkdir -p $REPO_OUTPUT_MAVEN
mv ../mde_optimiser/repositories/output/m2/m2.mdeoptimiser.repository/* $REPO_OUTPUT_MAVEN/

echo "Preparing P2 repository for MDEO Libs"

REPO_OUTPUT_P2_LIBS="$REPO_OUTPUT/p2/artifacts/libraries"

mkdir -p $REPO_OUTPUT_P2_LIBS
mv ../mde_optimiser/repositories/output/p2/p2.mdeoptimiser.repository/* $REPO_OUTPUT_P2_LIBS

echo "Preparing P2 repository for Eclipse UI"

REPO_OUTPUT_P2_ECLIPSE="$REPO_OUTPUT/p2/artifacts/eclipse"

mkdir -p $REPO_OUTPUT_P2_ECLIPSE
mv ../mde_optimiser/interfaces/eclipse/src/uk.ac.kcl.inf.mdeoptimiser.interfaces.eclipse.repository/target/* $REPO_OUTPUT_P2_ECLIPSE

echo "Preparing Eclipse P2 composite update site"

REPO_OUTPUT_P2_COMPOSITE="downloads/p2/"

mkdir -p $REPO_OUTPUT_P2_COMPOSITE
cp -r ../mde_optimiser/repositories/composite/* $REPO_OUTPUT_P2_COMPOSITE

echo "Preparation completed."

git add --all

git config user.name "Travis CI"
git config user.email "travis-ci@mde-optimiser.github.io"

git commit -m "Published build id: $TRAVIS_BUILD_NUMBER"

git push origin gh-pages

echo "Code deployed to $TRAVIS_REPO_SLUG repository in github.com."
