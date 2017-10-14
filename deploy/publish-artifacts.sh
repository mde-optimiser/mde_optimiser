#!/usr/bin/env bash

cd $TRAVIS_BUILD_DIR/..

rm -rf gh-pages

git clone -b gh-pages git@github.com:alxbrd/mde_optimiser.git gh-pages

cd gh-pages


mkdir -p downloads/eclipse/$TRAVIS_BRANCH
rm -rf downloads/eclipse/$TRAVIS_BRANCH/*

mv ../mde_optimiser/src/releng/uk.ac.kcl.mdeoptimise.repository/target/* downloads/eclipse/$TRAVIS_BRANCH/

git add --all

git config user.name "Travis CI"
git config user.email "alex+git-CIBUILD@onboot.org"

git commit -m "Published build id: $TRAVIS_BUILD_NUMBER"

git push origin gh-pages


