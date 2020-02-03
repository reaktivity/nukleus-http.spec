@REM
@REM Copyright 2016-2020 The Reaktivity Project
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

mv client.should.send.content.length.header.in.post.even.if.no.content/request.rpt client.should.send.content.length.header.in.post.even.if.no.content/client.rpt
mv client.should.send.content.length.header.in.post.even.if.no.content/response.rpt client.should.send.content.length.header.in.post.even.if.no.content/server.rpt
mv non.http.request.to.http.server.should.be.responded.to.with.400/request.rpt non.http.request.to.http.server.should.be.responded.to.with.400/client.rpt
mv non.http.request.to.http.server.should.be.responded.to.with.400/response.rpt non.http.request.to.http.server.should.be.responded.to.with.400/server.rpt
mv robust.server.should.allow.extra.CRLF.after.request.line/request.rpt robust.server.should.allow.extra.CRLF.after.request.line/client.rpt
mv robust.server.should.allow.extra.CRLF.after.request.line/response.rpt robust.server.should.allow.extra.CRLF.after.request.line/server.rpt
mv server.must.reject.header.with.space.between.header.name.and.colon/request.rpt server.must.reject.header.with.space.between.header.name.and.colon/client.rpt
mv server.must.reject.header.with.space.between.header.name.and.colon/response.rpt server.must.reject.header.with.space.between.header.name.and.colon/server.rpt
mv server.must.reject.request.with.multiple.different.content.length/request.rpt server.must.reject.request.with.multiple.different.content.length/client.rpt
mv server.must.reject.request.with.multiple.different.content.length/response.rpt server.must.reject.request.with.multiple.different.content.length/server.rpt
mv server.should.reject.obs.in.header.value/request.rpt server.should.reject.obs.in.header.value/client.rpt
mv server.should.reject.obs.in.header.value/response.rpt server.should.reject.obs.in.header.value/server.rpt
mv server.should.send.414.to.request.with.too.long.a.request/request.rpt server.should.send.414.to.request.with.too.long.a.request/client.rpt
mv server.should.send.414.to.request.with.too.long.a.request/response.rpt server.should.send.414.to.request.with.too.long.a.request/server.rpt
mv server.should.send.501.to.unimplemented.methods/request.rpt server.should.send.501.to.unimplemented.methods/client.rpt
mv server.should.send.501.to.unimplemented.methods/response.rpt server.should.send.501.to.unimplemented.methods/server.rpt
mv server.should.send.501.to.unknown.transfer.encoding/request.rpt server.should.send.501.to.unknown.transfer.encoding/client.rpt
mv server.should.send.501.to.unknown.transfer.encoding/response.rpt server.should.send.501.to.unknown.transfer.encoding/server.rpt
mv server.should.send.status.line.in.start.line/request.rpt server.should.send.status.line.in.start.line/client.rpt
mv server.should.send.status.line.in.start.line/response.rpt server.should.send.status.line.in.start.line/server.rpt
