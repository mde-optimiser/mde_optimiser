#!/usr/bin/env bash

/root/.local/bin/aws s3 sync /var/app/current/ s3://mde-optimiser-results/
