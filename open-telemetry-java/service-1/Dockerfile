FROM openjdk:11-jre
RUN mkdir -p /app/bin
COPY ./target/service-1-0.0.1-SNAPSHOT.jar /app/bin
COPY opentelemetry-javaagent-all.jar /app/bin
CMD java -Dotel.exporter=jaeger \
         -Dotel.exporter.jaeger.endpoint=jaeger:14250 \
         -Dotel.exporter.jaeger.service.name=Service1 \
		 -Dapplication.home=/app/bin/ \
		 -Dapplication.name=Service1 \
		 -javaagent:/app/bin/opentelemetry-javaagent-all.jar \
		 -jar \
		 /app/bin/service-1-0.0.1-SNAPSHOT.jar