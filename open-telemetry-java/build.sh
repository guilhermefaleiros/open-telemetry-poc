mvn clean install -DskipTests
cp opentelemetry-javaagent-all.jar service-1/
cp opentelemetry-javaagent-all.jar service-2/
docker-compose up --build