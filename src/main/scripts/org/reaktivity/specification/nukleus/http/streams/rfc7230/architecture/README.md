Tests in this directory match those in the [k3po http specification](https://github.com/k3po/k3po/tree/develop/specification/http/src/main/scripts/org/kaazing/specification/http/rfc7230/architecture) as follows:

nukleus-http.spec | k3po
----------------- | ----
request.and.response        | client.must.send.host.identifier
request.header.host.missing | inbound.must.reject.requests.missing.host.identifier 
request.uri.with.user.info  | inbound.must.reject.requests.with.user.info.on.uri 
request.version.http.1.2+   | inbound.must.reply.with.version.one.dot.one.when.received.higher.minor.version 
TODO: request.version.missing | inbound.must.send.version
request.uri.with.percent.chars | inbound.should.allow.requests.with.percent.chars.in.uri
request.version.not.http.1.x | origin.server.should.send.505.on.major.version.not.equal.to.one
TODO: response.version.missing | outbound.must.send.version
request.version.invalid | response.must.be.400.on.invalid.version

TODO: add cases for invalid responses:
response.version.invalid
response.version.missing
response.version.not.http.1.x
