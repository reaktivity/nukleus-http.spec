Tests in this directory correspond to those in the [k3po http specification](https://github.com/k3po/k3po/tree/develop/specification/http/src/main/scripts/org/kaazing/specification/http/rfc7230/) 
as follows, grouped by subfolder:

### architecture

nukleus-http.spec              | k3po
------------------------------ | ----
request.and.response           | client.must.send.host.identifier
request.header.host.missing    | inbound.must.reject.requests.missing.host.identifier 
request.uri.with.user.info     | inbound.must.reject.requests.with.user.info.on.uri 
request.version.http.1.2+      | inbound.must.reply.with.version.one.dot.one.when.received.higher.minor.version 
request.version.missing        | inbound.must.send.version
request.uri.with.percent.chars | inbound.should.allow.requests.with.percent.chars.in.uri
request.version.not.http.1.x   | origin.server.should.send.505.on.major.version.not.equal.to.one
request.version.invalid        | response.must.be.400.on.invalid.version
TODO: response.version.invalid |
TODO: response.version.missing | outbound.must.send.version
TODO: response.version.not.http.1.x

### connection.management

nukleus-http.spec                | k3po
-------------------------------- | ----
response.status.101.with.upgrade | server.that.is.upgrading.must.send.a.101.response
TBD                              | server.getting.upgrade.request.must.respond.with.upgrade.header
TBD                              | server.that.sends.upgrade.required.must.include.upgrade.header
TBD                              | client.must.close.connection.after.request.with.connection.close
TBD                              | client.must.not.reuse.tcp.connection.when.receives.connection.close
TBD                              | client.with.pipelining.must.not.retry.pipelining.immediately.after.failure
TBD                              | connections.should.persist.by.default
TBD                              | intermediary.must.remove.connection.header.on.forward.request
TBD                              | proxy.must.not.retry.non.idempotent.requests
TBD                              | reverse.proxy.connection.established
TBD                              | server.must.close.connection.after.response.with.connection.close
TBD                              | server.must.close.its.half.of.connection.after.sending.response.if.it.receives.a.close
TBD                              | server.should.accept.http.pipelining
                                 
### message.format

nukleus-http.spec                       | k3po
--------------------------------------- | ----
request.fragmented                      | 
request.fragmented.with.content.length  | 
request.headers.too.long                | server.should.send.414.to.request.with.too.long.a.request[URI]
request.with.content.length             | inbound.should.process.request.with.content.length
request.with.headers                    | inbound.should.accept.headers
response.fragmented                     | 
response.fragmented.with.content.length |
response.headers.too.long               | 
response.with.content.length            | outbound.should.process.response.with.content.length
response.with.headers                   | outbound.should.accept.headers
architecture/request.and.response       | outbound.should.accept.no.headers
TBD                                     | client.should.send.content.length.header.in.post.even.if.no.content
TBD                                     | gateway.must.reject.request.with.multiple.different.content.length
TBD                                     | head.response.must.not.have.content
TBD                                     | head.response.must.not.have.content.though.may.have.content.length
TBD                                     | inbound.on.receiving.field.with.length.larger.than.wanting.to.process.must.reply.with.4xx
TBD                                     | inbound.should.reject.invalid.request.line
TBD                                     | inbound.should.reject.request.with.whitespace.between.start.line.and.first.header
TBD                                     | non.http.request.to.http.server.should.be.responded.to.with.400
TBD                                     | on.response.proxy.must.remove.space.in.header.with.space.between.header.name.and.colon
TBD                                     | proxy.or.gateway.must.reject.obs.in.header.value
TBD                                     | proxy.should.preserve.unrecongnized.headers
TBD                                     | request.must.start.with.request.line
TBD                                     | robust.server.should.allow.extra.CRLF.after.request.line
TBD                                     | server.must.reject.header.with.space.between.header.name.and.colon
TBD                                     | server.must.reject.request.with.multiple.different.content.length
TBD                                     | server.should.reject.obs.in.header.value
TBD                                     | server.should.send.501.to.unimplemented.methods
TBD                                     | server.should.send.501.to.unknown.transfer.encoding
TBD                                     | server.should.send.status.line.in.start.line
