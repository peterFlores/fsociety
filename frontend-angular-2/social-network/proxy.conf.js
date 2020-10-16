var HttpsProxyAgent = require('https-proxy-agent');
var proxyConfig = [{
  context: '/oauth/token',
  target: 'http://3.22.230.92:9100',
  secure: false
}];

function setupForCorporateProxy(proxyConfig) {
  var proxyServer = process.env.http_proxy || process.env.HTTP_PROXY;
  if (proxyServer) {
    var agent = new HttpsProxyAgent(proxyServer);
    console.log('Using corporate proxy server: ' + proxyServer);
    proxyConfig.forEach(function(entry) {
      entry.agent = agent;
    });
  }
  return proxyConfig;
}

module.exports = setupForCorporateProxy(proxyConfig);

const PROXY_CONFIG = [
    {
        context: [
            "/login,",
            
        ],
        target: "http://3.22.230.92:9100/oauth/token",
        secure: false
    }
]

module.exports = PROXY_CONFIG;