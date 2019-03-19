#!/bin/bash
SERVER_NAME=sharding-jdbc-performance-test

cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`

LOGS_DIR=${DEPLOY_DIR}/logs
if [ ! -d ${LOGS_DIR} ]; then
    mkdir ${LOGS_DIR}
fi

STDOUT_FILE=${LOGS_DIR}/stdout.log

PIDS=`ps -ef | grep java | grep "$DEPLOY_DIR" | grep -v grep | awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

CLASS_PATH=.:${DEPLOY_DIR}/conf:${DEPLOY_DIR}/lib/*
JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "

JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn1g -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods
-XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "

MAIN_CLASS=io.shardingsphere.example.jdbc.performance.test.SpringBootStarterExample

echo "Starting the $SERVER_NAME ..."

if [ $# == 1 ]; then
    JAVA_OPTS=${JAVA_OPTS}" "$1
fi

if [ $# == 2 ]; then
    JAVA_OPTS=${JAVA_OPTS}" "$1" "$2
fi

nohup java ${JAVA_OPTS} ${JAVA_MEM_OPTS} -classpath ${CLASS_PATH} ${MAIN_CLASS} > ${STDOUT_FILE} 2>&1 &
sleep 1
echo "Please check the STDOUT file: $STDOUT_FILE"
