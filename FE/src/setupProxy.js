const { createProxyMiddleware } = require("http-proxy-middleware");
const cors = require("cors");

// src/setupProxy.js
module.exports = function (app) {
  app.use(
    cors(
      "/api",
      createProxyMiddleware({
        target: "http://13.124.221.114:8080",
        changeOrigin: true,
      })
    )
  );
};
