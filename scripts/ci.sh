#!/bin/bash -ex

export UID
docker-compose up --build -d jobmanager taskmanager
#sleep 3
docker-compose run job

JAR="out/foo/assembly/dest/out.jar"
JOBMANAGER_CONTAINER=$(docker ps --filter name=jobs --format={{.ID}})

#docker cp $JAR "$JOBMANAGER_CONTAINER":/job.jar
docker exec -t -i "${JOBMANAGER_CONTAINER}" flink run /job.jar
 
