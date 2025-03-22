#!/bin/bash

APP_NAME=bsr-streaming-kafka_
APP_VERSION=2024.11.14
APP_JAR=../lib/demo-spark-streaming-kafka-1.0.0.jar
MAIN_CLASS=sunyu.demo.Main

APP_ID=`yarn application -list |grep ${APP_NAME} |awk '{print $1}'`

if [ "${APP_ID}" != "" ] ; then
  echo `yarn application -kill ${APP_ID}`
fi

echo "Start Spark Application ${APP_NAME}${APP_VERSION} ...."

for jar in ../lib/*.jar
do
  if [ ${jar} != ${APP_JAR} ] ; then
    LIBJARS=${jar},${LIBJARS}
  fi
done

for file in ../resources/*; do
  if [ -f ${file} ]; then
    RESOURCES_FILES=${file},${RESOURCES_FILES}
  fi
done

spark-submit \
  --class ${MAIN_CLASS} \
  --master yarn \
  --deploy-mode cluster \
  --jars ${LIBJARS} \
  --files ${RESOURCES_FILES} \
  --conf spark.app.name=${APP_NAME}${APP_VERSION} \
  --conf spark.driver.cores=1 \
  --conf spark.driver.memory=1g \
  --conf spark.driver.maxResultSize=0 \
  --conf spark.executor.cores=1 \
  --conf spark.executor.instances=2 \
  --conf spark.executor.memory=1g \
  --conf spark.serializer=org.apache.spark.serializer.KryoSerializer \
  --conf spark.dynamicAllocation.enabled=false \
  --conf spark.shuffle.service.enabled=true \
  --conf spark.scheduler.mode=FIFO \
  --conf spark.streaming.concurrentJobs=1 \
  --conf spark.streaming.backpressure.enabled=false \
  --conf spark.streaming.kafka.maxRatePerPartition=10000 \
  --conf spark.streaming.stopGracefullyOnShutdown=true \
  $APP_JAR \
  60