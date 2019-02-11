#!/usr/bin/env bash

if [ "$TRAVIS_BRANCH" != "master" ] && [ "$TRAVIS_BRANCH" != "develop" ]; then
    echo "TRAVIS_BRANCH is not defined. This is not a Travis build. Defaulting to 'develop'"
    TRAVIS_BRANCH="develop"
fi

cd ../

pwd

cd website/src/

rm -rf _site/*

bundle exec jekyll build

echo "Done building the Jekyll website"

echo "Preparing to create repositories directory for $TRAVIS_BRANCH website"

mkdir -p ./_site/repositories/$TRAVIS_BRANCH/

REPO_DEPLOY_ROOT="./_site/repositories/$TRAVIS_BRANCH"

cp ../../repositories/output/* -R "$REPO_DEPLOY_ROOT/"

MDEOPTIMISER_ECLIPSE_REPO="$REPO_DEPLOY_ROOT/p2/p2.mdeoptimiser.eclipse.repository/"

mkdir -p $MDEOPTIMISER_ECLIPSE_REPO

cp ../../interfaces/eclipse/src/uk.ac.kcl.inf.mdeoptimiser.interfaces.eclipse.repository/target/* $MDEOPTIMISER_ECLIPSE_REPO -R

echo "Deployed repositories to ./_site/repositories/$TRAVIS_BRANCH successfully/"

echo "Current repositories directory layout"

find ./_site/repositories/$TRAVIS_BRANCH/ -mindepth 2 -maxdepth 2 -type d
