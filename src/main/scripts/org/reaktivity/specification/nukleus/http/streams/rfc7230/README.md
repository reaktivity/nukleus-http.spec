Tests in this directory match those in the [k3po http specification](https://github.com/k3po/k3po/tree/develop/specification/http/src/main/scripts/org/kaazing/specification/http/rfc7230/) 
as follows, grouped by subfolder:

### architecture

nukleus-http.spec              | k3po
------------------------------ | ----
request.and.response           | client.must.send.host.identifier
request.header.host.missing    | inbound.must.reject.requests.missing.host.identifier 
request.uri.with.user.info     | inbound.must.reject.requests.with.user.info.on.uri 
request.version.http.1.2+      | inbound.must.reply.with.version.one.dot.one.when.received.higher.minor.version 
TODO: request.version.missing  | inbound.must.send.version
request.uri.with.percent.chars | inbound.should.allow.requests.with.percent.chars.in.uri
request.version.not.http.1.x   | origin.server.should.send.505.on.major.version.not.equal.to.one
response.version.missing       | outbound.must.send.version
request.version.invalid        | response.must.be.400.on.invalid.version

TODO: add cases for invalid responses:
response.version.invalid
response.version.missing
response.version.not.http.1.x

### connection.management

nukleus-http.spec                | k3po
-------------------------------- | ----
response.status.101.with.upgrade | server.that.is.upgrading.must.send.a.101.response (TO BE CONFIRMED)
                                 | client.must.close.connection.after.request.with.connection.close
                                 | client.must.not.reuse.tcp.connection.when.receives.connection.close
                                 | client.with.pipelining.must.not.retry.pipelining.immediately.after.failure
                                 | connections.should.persist.by.default
                                 | intermediary.must.remove.connection.header.on.forward.request
                                 | proxy.must.not.retry.non.idempotent.requests
                                 | reverse.proxy.connection.established
                                 | server.getting.upgrade.request.must.respond.with.upgrade.header
                                 | server.must.close.connection.after.response.with.connection.close
                                 | server.must.close.its.half.of.connection.after.sending.response.if.it.receives.a.close
                                 | server.should.accept.http.pipelining
                                 | server.that.sends.upgrade.required.must.include.upgrade.header
