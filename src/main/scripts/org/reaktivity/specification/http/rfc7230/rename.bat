@REM
@REM Copyright 2016-2021 The Reaktivity Project
@REM
@REM The Reaktivity Project licenses this file to you under the Apache License,
@REM version 2.0 (the "License"); you may not use this file except in compliance
@REM with the License. You may obtain a copy of the License at:
@REM
@REM   http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
@REM WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
@REM License for the specific language governing permissions and limitations
@REM under the License.
@REM

mv architecture/request.uri.with.percent.chars/request.rpt architecture/request.uri.with.percent.chars/client.rpt
mv architecture/request.uri.with.percent.chars/response.rpt architecture/request.uri.with.percent.chars/server.rpt
mv architecture/request.uri.with.user.info/request.rpt architecture/request.uri.with.user.info/client.rpt
mv architecture/request.uri.with.user.info/response.rpt architecture/request.uri.with.user.info/server.rpt
mv architecture/request.version.1.2+/request.rpt architecture/request.version.1.2+/client.rpt
mv architecture/request.version.1.2+/response.rpt architecture/request.version.1.2+/server.rpt
mv architecture/request.version.invalid/request.rpt architecture/request.version.invalid/client.rpt
mv architecture/request.version.invalid/response.rpt architecture/request.version.invalid/server.rpt
mv architecture/request.version.missing/request.rpt architecture/request.version.missing/client.rpt
mv architecture/request.version.missing/response.rpt architecture/request.version.missing/server.rpt
mv architecture/request.version.not.http.1.x/request.rpt architecture/request.version.not.http.1.x/client.rpt
mv architecture/request.version.not.http.1.x/response.rpt architecture/request.version.not.http.1.x/server.rpt
connection.management/connections.should.persist.by.default/backend.rpt
connection.management/intermediary.must.remove.connection.header.on.forward.request/backend.rpt
connection.management/intermediary.must.remove.connection.header.on.forward.request/intermediary.rpt
connection.management/proxy.must.not.retry.non.idempotent.requests/proxy.rpt
connection.management/reverse.proxy.connection.established/proxy.rpt
message.format/gateway.must.reject.request.with.multiple.different.content.length/gateway.rpt
mv message.format/gateway.must.reject.request.with.multiple.different.content.length/request.rpt message.format/gateway.must.reject.request.with.multiple.different.content.length/client.rpt
mv message.format/gateway.must.reject.request.with.multiple.different.content.length/response.rpt message.format/gateway.must.reject.request.with.multiple.different.content.length/server.rpt
mv message.format/head.response.must.not.have.content.though.may.have.content.length/request.rpt message.format/head.response.must.not.have.content.though.may.have.content.length/client.rpt
mv message.format/head.response.must.not.have.content.though.may.have.content.length/response.rpt message.format/head.response.must.not.have.content.though.may.have.content.length/server.rpt
mv message.format/head.response.must.not.have.content/request.rpt message.format/head.response.must.not.have.content/client.rpt
mv message.format/head.response.must.not.have.content/response.rpt message.format/head.response.must.not.have.content/server.rpt
mv message.format/inbound.on.receiving.field.with.length.larger.than.wanting.to.process.must.reply.with.4xx/request.rpt message.format/inbound.on.receiving.field.with.length.larger.than.wanting.to.process.must.reply.with.4xx/client.rpt
mv message.format/inbound.on.receiving.field.with.length.larger.than.wanting.to.process.must.reply.with.4xx/response.rpt message.format/inbound.on.receiving.field.with.length.larger.than.wanting.to.process.must.reply.with.4xx/server.rpt
mv message.format/inbound.should.accept.headers/request.rpt message.format/inbound.should.accept.headers/client.rpt
mv message.format/inbound.should.accept.headers/response.rpt message.format/inbound.should.accept.headers/server.rpt
mv message.format/inbound.should.process.request.with.content.length/request.rpt message.format/inbound.should.process.request.with.content.length/client.rpt
mv message.format/inbound.should.process.request.with.content.length/response.rpt message.format/inbound.should.process.request.with.content.length/server.rpt
mv message.format/inbound.should.reject.invalid.request.line/request.rpt message.format/inbound.should.reject.invalid.request.line/client.rpt
mv message.format/inbound.should.reject.invalid.request.line/response.rpt message.format/inbound.should.reject.invalid.request.line/server.rpt
mv message.format/inbound.should.reject.request.with.whitespace.between.start.line.and.first.header/request.rpt message.format/inbound.should.reject.request.with.whitespace.between.start.line.and.first.header/client.rpt
mv message.format/inbound.should.reject.request.with.whitespace.between.start.line.and.first.header/response.rpt message.format/inbound.should.reject.request.with.whitespace.between.start.line.and.first.header/server.rpt
message.format/on.response.proxy.must.remove.space.in.header.with.space.between.header.name.and.colon/proxy.rpt
mv message.format/outbound.should.accept.headers/request.rpt message.format/outbound.should.accept.headers/client.rpt
mv message.format/outbound.should.accept.headers/response.rpt message.format/outbound.should.accept.headers/server.rpt
mv message.format/outbound.should.accept.no.headers/request.rpt message.format/outbound.should.accept.no.headers/client.rpt
mv message.format/outbound.should.accept.no.headers/response.rpt message.format/outbound.should.accept.no.headers/server.rpt
mv message.format/outbound.should.process.response.with.content.length/request.rpt message.format/outbound.should.process.response.with.content.length/client.rpt
mv message.format/outbound.should.process.response.with.content.length/response.rpt message.format/outbound.should.process.response.with.content.length/server.rpt
mv message.format/proxy.or.gateway.must.reject.obs.in.header.value/request.rpt message.format/proxy.or.gateway.must.reject.obs.in.header.value/client.rpt
mv message.format/proxy.or.gateway.must.reject.obs.in.header.value/response.rpt message.format/proxy.or.gateway.must.reject.obs.in.header.value/server.rpt
message.format/proxy.should.preserve.unrecongnized.headers/proxy.rpt
mv message.format/request.must.start.with.request.line/request.rpt message.format/request.must.start.with.request.line/client.rpt
mv message.format/request.must.start.with.request.line/response.rpt message.format/request.must.start.with.request.line/server.rpt
message.routing/firewall.intermediary.should.replace.host.in.via.header.with.pseudonym/proxy.rpt
message.routing/gateway.must.attach.appropriate.via.header.on.request.and.may.attach.on.response/proxy.rpt
mv message.routing/inbound.host.header.should.follow.request.line/request.rpt message.routing/inbound.host.header.should.follow.request.line/client.rpt
mv message.routing/inbound.host.header.should.follow.request.line/response.rpt message.routing/inbound.host.header.should.follow.request.line/server.rpt
mv message.routing/inbound.must.accept.absolute.form/request.rpt message.routing/inbound.must.accept.absolute.form/client.rpt
mv message.routing/inbound.must.accept.absolute.form/response.rpt message.routing/inbound.must.accept.absolute.form/server.rpt
mv message.routing/inbound.must.accept.asterick.form.options.request/request.rpt message.routing/inbound.must.accept.asterick.form.options.request/client.rpt
mv message.routing/inbound.must.accept.asterick.form.options.request/response.rpt message.routing/inbound.must.accept.asterick.form.options.request/server.rpt
mv message.routing/inbound.must.accept.origin.form/request.rpt message.routing/inbound.must.accept.origin.form/client.rpt
mv message.routing/inbound.must.accept.origin.form/response.rpt message.routing/inbound.must.accept.origin.form/server.rpt
mv message.routing/inbound.must.reject.request.with.400.if.host.header.does.not.match.uri/request.rpt message.routing/inbound.must.reject.request.with.400.if.host.header.does.not.match.uri/client.rpt
mv message.routing/inbound.must.reject.request.with.400.if.host.header.does.not.match.uri/response.rpt message.routing/inbound.must.reject.request.with.400.if.host.header.does.not.match.uri/server.rpt
mv message.routing/inbound.must.reject.request.with.400.if.host.header.occurs.more.than.once/request.rpt message.routing/inbound.must.reject.request.with.400.if.host.header.occurs.more.than.once/client.rpt
mv message.routing/inbound.must.reject.request.with.400.if.host.header.occurs.more.than.once/response.rpt message.routing/inbound.must.reject.request.with.400.if.host.header.occurs.more.than.once/server.rpt
mv message.routing/inbound.must.reject.request.with.400.if.missing.host.header/request.rpt message.routing/inbound.must.reject.request.with.400.if.missing.host.header/client.rpt
mv message.routing/inbound.must.reject.request.with.400.if.missing.host.header/response.rpt message.routing/inbound.must.reject.request.with.400.if.missing.host.header/server.rpt
mv message.routing/intermediary.must.accept.authority.form.connect.request/request.rpt message.routing/intermediary.must.accept.authority.form.connect.request/client.rpt
mv message.routing/intermediary.must.accept.authority.form.connect.request/response.rpt message.routing/intermediary.must.accept.authority.form.connect.request/server.rpt
mv message.routing/last.proxy.must.convert.options.in.absolute.form.to.asterick.form/request.rpt message.routing/last.proxy.must.convert.options.in.absolute.form.to.asterick.form/client.rpt
mv message.routing/last.proxy.must.convert.options.in.absolute.form.to.asterick.form/response.rpt message.routing/last.proxy.must.convert.options.in.absolute.form.to.asterick.form/server.rpt
message.routing/proxy.must.attach.appropriate.via.header/proxy.rpt
message.routing/proxy.must.attach.appropriate.via.headers.even.when.others/proxy.rpt
mv message.routing/proxy.must.not.modify.query.or.absolute.path.of.request/request.rpt message.routing/proxy.must.not.modify.query.or.absolute.path.of.request/client.rpt
mv message.routing/proxy.must.not.modify.query.or.absolute.path.of.request/response.rpt message.routing/proxy.must.not.modify.query.or.absolute.path.of.request/server.rpt
mv message.routing/proxy.must.not.transform.the.payload.of.a.request.that.contains.a.no.transform.cache.control/request.rpt message.routing/proxy.must.not.transform.the.payload.of.a.request.that.contains.a.no.transform.cache.control/client.rpt
mv message.routing/proxy.must.not.transform.the.payload.of.a.request.that.contains.a.no.transform.cache.control/response.rpt message.routing/proxy.must.not.transform.the.payload.of.a.request.that.contains.a.no.transform.cache.control/server.rpt
mv message.routing/proxy.must.not.transform.the.payload.of.a.response.that.contains.a.no.transform.cache.control/request.rpt message.routing/proxy.must.not.transform.the.payload.of.a.response.that.contains.a.no.transform.cache.control/client.rpt
mv message.routing/proxy.must.not.transform.the.payload.of.a.response.that.contains.a.no.transform.cache.control/response.rpt message.routing/proxy.must.not.transform.the.payload.of.a.response.that.contains.a.no.transform.cache.control/server.rpt
message.routing/proxy.should.rewrite.host.header/proxy.rpt
mv transfer.codings/request.transfer.encoding.chunked.with.trailer/request.rpt transfer.codings/request.transfer.encoding.chunked.with.trailer/client.rpt
mv transfer.codings/request.transfer.encoding.chunked.with.trailer/response.rpt transfer.codings/request.transfer.encoding.chunked.with.trailer/server.rpt
mv transfer.codings/request.transfer.encoding.chunked/request.rpt transfer.codings/request.transfer.encoding.chunked/client.rpt
mv transfer.codings/request.transfer.encoding.chunked/response.rpt transfer.codings/request.transfer.encoding.chunked/server.rpt
mv transfer.codings/response.transfer.encoding.chunked.with.trailer/request.rpt transfer.codings/response.transfer.encoding.chunked.with.trailer/client.rpt
mv transfer.codings/response.transfer.encoding.chunked.with.trailer/response.rpt transfer.codings/response.transfer.encoding.chunked.with.trailer/server.rpt
mv transfer.codings/response.transfer.encoding.chunked/request.rpt transfer.codings/response.transfer.encoding.chunked/client.rpt
mv transfer.codings/response.transfer.encoding.chunked/response.rpt transfer.codings/response.transfer.encoding.chunked/server.rpt
