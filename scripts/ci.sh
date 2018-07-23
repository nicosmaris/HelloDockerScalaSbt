#!/bin/bash -ex

export UID
docker-compose version

docker-compose build job
docker-compose run job bash -c "cp -R /input/* /workspace/ && cd /workspace && mill -i foo.test"

