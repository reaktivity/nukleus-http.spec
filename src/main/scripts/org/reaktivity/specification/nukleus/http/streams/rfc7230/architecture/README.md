Tests in this directory match those in the [k3po http specification](https://github.com/k3po/k3po/tree/develop/specification/http/src/main/scripts/org/kaazing/specification/http/rfc7230/architecture) as follows:

nukleus-http.spec | k3po
----------------- | ----
request.and.response        | client.must.send.host.identifier
request.header.host.missing | inbound.must.reject.requests.missing.host.identifier 
TBD | inbound.must.reject.requests.with.user.info.on.uri 
TBD | inbound.must.reply.with.version.one.dot.one.when.received.higher.minor.version 
TBD | inbound.must.send.version
TBD | inbound.should.allow.requests.with.percent.chars.in.uri
TBD | origin.server.should.send.505.on.major.version.not.equal.to.one
TBD | outbound.must.send.version
TBD | response.must.be.400.on.invalid.version

