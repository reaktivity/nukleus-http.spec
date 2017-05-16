@REM
@REM Copyright 2016-2017 The Reaktivity Project
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

mv connection.management/client.must.close.connection.after.request.with.connection.close/request.rpt connection.management/client.must.close.connection.after.request.with.connection.close/client.rpt
mv connection.management/client.must.close.connection.after.request.with.connection.close/response.rpt connection.management/client.must.close.connection.after.request.with.connection.close/server.rpt
mv connection.management/client.must.not.reuse.tcp.connection.when.receives.connection.close/request.rpt connection.management/client.must.not.reuse.tcp.connection.when.receives.connection.close/client.rpt
mv connection.management/client.must.not.reuse.tcp.connection.when.receives.connection.close/response.rpt connection.management/client.must.not.reuse.tcp.connection.when.receives.connection.close/server.rpt
mv connection.management/client.with.pipelining.must.not.retry.pipelining.immediately.after.failure/request.rpt connection.management/client.with.pipelining.must.not.retry.pipelining.immediately.after.failure/client.rpt
mv connection.management/client.with.pipelining.must.not.retry.pipelining.immediately.after.failure/response.rpt connection.management/client.with.pipelining.must.not.retry.pipelining.immediately.after.failure/server.rpt
mv connection.management/server.getting.upgrade.request.must.respond.with.upgrade.header/request.rpt connection.management/server.getting.upgrade.request.must.respond.with.upgrade.header/client.rpt
mv connection.management/server.getting.upgrade.request.must.respond.with.upgrade.header/response.rpt connection.management/server.getting.upgrade.request.must.respond.with.upgrade.header/server.rpt
mv connection.management/server.must.close.connection.after.response.with.connection.close/request.rpt connection.management/server.must.close.connection.after.response.with.connection.close/client.rpt
mv connection.management/server.must.close.connection.after.response.with.connection.close/response.rpt connection.management/server.must.close.connection.after.response.with.connection.close/server.rpt
mv connection.management/server.must.close.its.half.of.connection.after.sending.response.if.it.receives.a.close/request.rpt connection.management/server.must.close.its.half.of.connection.after.sending.response.if.it.receives.a.close/client.rpt
mv connection.management/server.must.close.its.half.of.connection.after.sending.response.if.it.receives.a.close/response.rpt connection.management/server.must.close.its.half.of.connection.after.sending.response.if.it.receives.a.close/server.rpt
mv connection.management/server.should.accept.http.pipelining/request.rpt connection.management/server.should.accept.http.pipelining/client.rpt
mv connection.management/server.should.accept.http.pipelining/response.rpt connection.management/server.should.accept.http.pipelining/server.rpt
mv connection.management/server.that.is.upgrading.must.send.a.101.response/request.rpt connection.management/server.that.is.upgrading.must.send.a.101.response/client.rpt
mv connection.management/server.that.is.upgrading.must.send.a.101.response/response.rpt connection.management/server.that.is.upgrading.must.send.a.101.response/server.rpt
mv connection.management/server.that.sends.upgrade.required.must.include.upgrade.header/request.rpt connection.management/server.that.sends.upgrade.required.must.include.upgrade.header/client.rpt
mv connection.management/server.that.sends.upgrade.required.must.include.upgrade.header/response.rpt connection.management/server.that.sends.upgrade.required.must.include.upgrade.header/server.rpt
