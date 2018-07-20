#!/bin/bash -ex

export UID
docker-compose version

docker-compose up -d jobmanager taskmanager
docker-compose up --build -d box
docker-compose up --build job

JAR="out/foo/assembly/dest/out.jar"
JOBMANAGER_CONTAINER=$(docker ps --filter "label=com.pccwg.flink-node-type-jobs" --format={{.ID}})
JOB_CONTAINER=$(docker ps -a --filter name=thejob --format={{.ID}})

docker cp "$JOB_CONTAINER":"/workspace/$JAR" job.jar
docker cp job.jar "$JOBMANAGER_CONTAINER":/job.jar
rm job.jar
docker exec -i "${JOBMANAGER_CONTAINER}" flink run /job.jar
 
