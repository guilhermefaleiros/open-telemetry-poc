const { registerInstrumentations } = require("@opentelemetry/instrumentation");
const { NodeTracerProvider } = require("@opentelemetry/sdk-trace-node");
const { SimpleSpanProcessor } = require("@opentelemetry/sdk-trace-base");
const { Resource } = require("@opentelemetry/resources");
const {
  SemanticResourceAttributes,
} = require("@opentelemetry/semantic-conventions");
const { JaegerExporter } = require("@opentelemetry/exporter-jaeger");
const { HttpInstrumentation } = require("@opentelemetry/instrumentation-http");
const {
  ExpressInstrumentation,
} = require("@opentelemetry/instrumentation-express");

const provider = new NodeTracerProvider({
  resource: new Resource({
    [SemanticResourceAttributes.SERVICE_NAME]: "service-1",
  }),
});

const exporter = new JaegerExporter({});

provider.addSpanProcessor(new SimpleSpanProcessor(exporter));

provider.register();

registerInstrumentations({
  instrumentations: [new HttpInstrumentation(), new ExpressInstrumentation()],
});

const axios = require("axios");
const express = require("express");

const SERVICE_2_URL = "http://localhost:3001";

const service2 = axios.create({ baseURL: SERVICE_2_URL });

const app = express();

app.get("/test", async (req, res) => {
  try {
    const response = await service2.get("/test");
    return res.status(response.status).json(response.data);
  } catch (e) {
    return res.status(500).json({ error: "internal server error" });
  }
});

app.listen(3000, () => {
  console.log("Service 1 ouvindo na porta 3000");
});
