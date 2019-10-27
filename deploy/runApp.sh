#!/bin/sh

JAR_NAME="portfolio-management-app-1.0-SNAPSHOT.jar"
JAVA_OPTIONS="-Dspring.profiles.active=prod"
BIN_DIR="../build/libs/${JAR_NAME}"

echo "application started"
java -jar ${JAVA_OPTIONS} ${BIN_DIR}
echo "boot complited!"
