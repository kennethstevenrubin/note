#!/bin/bash
echo "build note system..."

echo "1. discovery-service:"
cd discovery-service
mvn clean package dockerfile:build -Dmaven.test.skip
cd ..

echo "2. gateway-service:"
cd gateway-service
mvn clean package dockerfile:build -Dmaven.test.skip
cd ..

echo "3. note-service:"
cd note-service
mvn clean package dockerfile:build -Dmaven.test.skip
cd ..

echo "4. note-web:"
cd note-web
docker build . -t note/note-web:0.0.1
cd ..

echo "done!"