/**
 * Copyright 2016-2017 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.specification.nukleus.http.streams.rfc7230;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.ScriptProperty;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

public class MessageFormatIT
{
    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("scripts", "org/reaktivity/specification/nukleus/http/streams/rfc7230/message.format");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
            "${scripts}/request.with.headers/client",
            "${scripts}/request.with.headers/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void requestWithHeaders() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/request.with.content.length/client",
            "${scripts}/request.with.content.length/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void requestWithContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.with.headers/client",
            "${scripts}/response.with.headers/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void responseWithHeaders() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }


    @Test
    @Specification({
            "${scripts}/response.with.content.length/client",
            "${scripts}/response.with.content.length/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void responseWithContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/request.with.extra.CRLF.before.request.line/client",
            "${scripts}/request.with.extra.CRLF.before.request.line/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void robustServerShouldAllowExtraCRLFBeforeRequestLine() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }


    @Test
    @Specification({
            "${scripts}/post.request.with.no.content/client",
            "${scripts}/post.request.with.no.content/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void postRequestWithNoContent() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/head.request.and.response/client",
            "${scripts}/head.request.and.response/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void headRequestAndResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/head.request.and.response.with.content.length/client",
            "${scripts}/head.request.and.response.with.content.length/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void headRequestAndResponseWithContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/gateway.must.reject.request.with.multiple.different.content.length/client",
            "${scripts}/gateway.must.reject.request.with.multiple.different.content.length/gateway",
            "${scripts}/gateway.must.reject.request.with.multiple.different.content.length/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("proxy tests not tests implemented")
    public void gatewayMustRejectResponseWithMultipleDifferentContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/on.response.proxy.must.remove.space.in.header.with.space.between.header.name.and.colon/client",
            "${scripts}/on.response.proxy.must.remove.space.in.header.with.space.between.header.name.and.colon/server",
            "${scripts}/on.response.proxy.must.remove.space.in.header.with.space.between.header.name.and.colon/proxy" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("proxy tests not tests implemented")
    public void onResponseProxyMustRemoveSpaceInHeaderWithSpaceBetweenHeaderNameAndColon() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/proxy.gets.response.with.multiple.content.lengths/client",
            "${scripts}/proxy.gets.response.with.multiple.content.lengths/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("proxy tests not tests implemented")
    public void proxyGetsResponseWithMultipleContentLengths() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/proxy.or.gateway.must.reject.obs.in.header.value/client",
            "${scripts}/proxy.or.gateway.must.reject.obs.in.header.value/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("proxy tests not tests implemented")
    public void proxyOrGatewayMustRejectOBSInHeaderValue() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/proxy.should.preserve.unrecognized.headers/client",
            "${scripts}/proxy.should.preserve.unrecognized.headers/server",
            "${scripts}/proxy.should.preserve.unrecognized.headers/proxy" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("proxy tests not tests implemented")
    public void proxyShouldPreserveUnrecognizedHeaders() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

}
