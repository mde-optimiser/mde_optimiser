#!/usr/bin/env bash

cd $TRAVIS_BUILD_DIR/..

git clone -b gh-pages git@github.com:alxbrd/mde_optimiser.git gh-pages

cd gh-pages

mkdir -p downloads/eclipse/repository/

rm -rf downloads/eclipse/repository/

mv ../src/releng/uk.ac.kcl.mdeoptimise.repository/target/* downloads/eclipse/repository

git add --all

git user.name = "Travis CI"
git user.email = "alex+git-CIBUILD@onboot.org"

git commit -m "Updated source"

git push origin gh-pages


