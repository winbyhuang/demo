#!/bin/sh
cd `pwd`

STR=`ps -C java -f | grep "Application" |awk '{print $2}'`
if [ ! -z "$STR" ]; then
    kill $STR > /dev/null 2>&1
else
        echo "没有Java进程"
fi


JETTY_HOME=`readlink -f $0`
JETTY_HOME=`dirname $JETTY_HOME`
JETTY_HOME=${JETTY_HOME%/bin*}

echo "[INFO] JETTY_HOME=$JETTY_HOME"

#DIR_LOG=${JETTY_HOME}/logs
DIR_LOG=/home/admin/logs/demo
FILE_STDOUT_LOG=$DIR_LOG/stdout.log
FILE_STDERR_LOG=$DIR_LOG/stderr.log
FILE_START_LOG=$DIR_LOG/start.log

# 根据需要创建日志目录
if [ ! -e $DIR_LOG ] ; then
        mkdir -p $DIR_LOG
fi

MainClass="com.example.demo.Application"

JAVA_HOME=/usr/java/jdk1.8.0_121

JAVA_OPTS="-server -Xmx1024M -Xms1024M -Xmn256m -Xss256k  -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/admin/logs -Xloggc:$DIR_LOG/gc.log -XX:ErrorFile=$DIR_LOG/hs_err_pid%p.log"



${JAVA_HOME}/bin/java -cp "${JETTY_HOME}/lib/*"  $JAVA_OPTS $MainClass  > ${FILE_STDOUT_LOG} 2>${FILE_STDERR_LOG} &
echo "end"