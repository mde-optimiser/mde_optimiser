#!/usr/bin/env bash

if [ "$TRAVIS_BRANCH" != "master" ] && [ "$TRAVIS_BRANCH" != "develop" ]; then
    echo "TRAVIS_BRANCH is not defined. This is not a Travis build. Defaulting to 'develop'"
    TRAVIS_BRANCH="develop"
fi

cd ../

echo "Navigating to repository root"

pwd

echo "Remove old website build"

cd website/src/

rm -rf _site/*

bundle exec jekyll build

echo "Done building the Jekyll website"

echo "Ready to publish"



