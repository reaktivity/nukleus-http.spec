Tests in this directory correspond to those in the [k3po http specification](https://github.com/k3po/k3po/tree/develop/specification/http/src/main/scripts/org/kaazing/specification/http/rfc7230/) 
as follows, grouped by subfolder:

### architecture

nukleus-http.spec              | k3po
------------------------------ | ----
request.and.response           | client.must.send.host.identifier
request.header.host.missing    | inbound.must.reject.requests.missing.host.identifier 
request.uri.with.user.info     | inbound.must.reject.requests.with.user.info.on.uri 
request.version.1.2+           | inbound.must.reply.with.version.one.dot.one.when.received.higher.minor.version 
request.version.missing        | inbound.must.send.version
request.uri.with.percent.chars | inbound.should.allow.requests.with.percent.chars.in.uri
request.version.not.1.x        | origin.server.should.send.505.on.major.version.not.equal.to.one
request.version.invalid        | response.must.be.400.on.invalid.version
TODO: response.version.invalid |
response.version.missing       | outbound.must.send.version
TODO: response.version.not.http.1.x

### connection.management

nukleus-http.spec                             | k3po
---------------------------------             | ----
request.with.connection.close                 | client.must.close.connection.after.request.with.connection.close
Covered in request.with.connection.close      | server.must.close.its.half.of.connection.after.sending.response.if.it.receives.a.close
response.with.connection.close                | server.must.close.connection.after.response.with.connection.close
multiple.requests.same.connection             | connections.should.persist.by.default
multiple.requests.pipelined                   | server.should.accept.http.pipelining
multiple.requests.pipelined.with.retry        | client.with.pipelining.must.not.retry.pipelining.immediately.after.failure
first.pipelined.response.has.connection.close | client.must.not.reuse.tcp.connection.when.receives.connection.close
upgrade.request.and.response                  | server.getting.upgrade.request.must.respond.with.upgrade.header
upgrade.request.and.response.with.data        | server.that.is.upgrading.must.send.a.101.response
request.and.upgrade.required.response         | server.that.sends.upgrade.required.must.include.upgrade.header
TODO: proxy.must.not.forward.connection.header| intermediary.must.remove.connection.header.on.forward.request
TBD                                           | proxy.must.not.retry.non.idempotent.requests
TBD                                           | reverse.proxy.connection.established
                                 
### message.format

nukleus-http.spec                       | k3po
--------------------------------------- | ----
request.with.start.line.too.long        | server.should.send.414.to.request.with.too.long.a.request[URI]
request.with.content.length             | inbound.should.process.request.with.content.length
request.with.headers                    | inbound.should.accept.headers
response.with.content.length            | outbound.should.process.response.with.content.length
response.with.headers                   | outbound.should.accept.headers
invalid.request.whitespace.after.start.line | inbound.should.reject.request.with.whitespace.between.start.line.and.first.header
invalid.request.not.http                | non.http.request.to.http.server.should.be.responded.to.with.400
request.with.unimplemented.method       | server.should.send.501.to.unimplemented.methods
request.with.extra.CRLF.after.request.line | robust.server.should.allow.extra.CRLF.after.request.line
request.with.obsolete.line.folding      | server.should.reject.obs.in.header.value
request.with.unknown.transfer.encoding  | server.should.send.501.to.unknown.transfer.encoding
post.request.with.no.content            | client.should.send.content.length.header.in.post.even.if.no.content
head.request.and.response               | head.response.must.not.have.content
head.request.and.response.with.content.length | head.response.must.not.have.content.though.may.have.content.length
request.with.header.value.too.long      | inbound.on.receiving.field.with.length.larger.than.wanting.to.process.must.reply.with.4xx
TBD                                     | inbound.should.reject.invalid.request.line
TBD                                     | server.must.reject.header.with.space.between.header.name.and.colon
TBD                                     | server.must.reject.request.with.multiple.different.content.length
TBD                                     | on.response.proxy.must.remove.space.in.header.with.space.between.header.name.and.colon
TBD                                     | gateway.must.reject.request.with.multiple.different.content.length
TBD                                     | proxy.or.gateway.must.reject.obs.in.header.value
TBD                                     | proxy.should.preserve.unrecongnized.headers
none (covered by architecture/request.and.response) | outbound.should.accept.no.headers
none (covered by architecture/request.and.response) | request.must.start.with.request.line
none (covered by architecture/request.and.response) | server.should.send.status.line.in.start.line

### flow.control

nukleus-http.spec                       | k3po
--------------------------------------- | ----
request.headers.too.long                | server.should.send.414.to.request.with.too.long.a.request[URI]


#### Migration plan for Agrona-based flow control scripts

- DONE multiple.requests.pipelined:
  - removed, covered in connection.management/multiple.requests.pipelined
- DONE multiple.requests.pipelined.fragmented (server only): 
  - low level done, use connection.management/concurrent.requests/server for high level
- DONE multiple.requests.with.content.length.pipelined.fragmented (server only):
  - low level done, connection.management/multiple.requests.serialized/server for high-level
- DONE multiple.requests.with.response.flow.control (server only, initial window for response forces content of second response to be fragmented)
  - use connection.management/multiple.requests.pipelined with parameterized initial window (89)
- DONE request.flow.controlled (client only):
  - use architecture/request.and.response: network server script forces client to fragment by setting initial window to 3
- DONE request.with.content.flow.controlled (client and server):
  - use message.format/request.with.content.length with initial window property on server scripts
- DONE request.fragmented (server only):
  - only low level needed, use message.format/request.with.headers for high level
- DONE request.fragmented.with.content.length (server only)
  - only low level needed, use message.format/request.with.content for high-level
- DONE request.headers.too.long (client only: shouldNotWriteRequestExceedingMaximumHeadersSize, server case is covered by message.format/request.with.header.value.too.long)
  - high-level scripts done (no low level needed)
- DONE request.with.content.length.and.end.late.target.window (server only)
  - low level scripts request.with.content.length.and.transport.close added, used  message.format/request.with.content.length for high-level
- DONE request.with.fragmented.content.flow.controlled (server only)
  - use low level request.fragmented.with.content.length with high level message.format/request.with.content.length with server initial window property
- DONE request.with.upgrade.and.data (server only, but should add client case too)
  response.with.upgrade.and.data (server only, but should add client case too)
  - use connection.management/upgrade.request.and.response.with.data, enhanced to have data flow in both directions, with parameterized initial window for client and server
- DONE response.first.fragment.maximum.headers (client only)
  - low-level and high-level scripts done
- response.flow.controlled (server only)
  - use architecture/request.and.response with initial window parameterized in low-level client script  
- response.fragmented (client only)
  - low-level scripts done (use message.format/response.with.header for high-level)
- response.fragmented.with.content.length (client only)
  - low level scripts done (use message.format/response.with.content.length for high-level)
- response.headers.too.long (client and server)
  - Client (shouldRejectResponseExceedingMaximumHeadersSize): low level server script gets reset when writing response (low level server.response.reset), high level client script gets no response (high level client.no.response)  
  - Server (shouldNotWriteResponseExceedingMaximumHeadersSize): high level server gets reset when writing response (server.response.reset), low level client gets 5xx response (client.5xx.response)
- response.with.content.flow.controlled
  - use message.format/response.with.content.length with initial window property on server scripts
- response.with.content.length.and.end.late.target.window (client only)
  - covered by message.format/response.with.content.length (probably, depending on timing) 
- response.with.fragmented.content.flow.controlled
  - use low-level response.fragmented.with.content.length with high-level message.format/response.with.content.length with server initial window property

