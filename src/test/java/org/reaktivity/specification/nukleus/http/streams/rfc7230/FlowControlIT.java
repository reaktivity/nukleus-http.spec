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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.ScriptProperty;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

public class FlowControlIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/nukleus/http/streams/rfc7230/flow.control");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "${scripts}/response.first.fragment.maximum.headers/client",
        "${scripts}/response.first.fragment.maximum.headers/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldProcessResponseWhenFirstFragmentIsHeadersOfLength64() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.headers.too.long/client",
        "${scripts}/request.headers.too.long/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldRejectRequestWithHeadersExceedingMaximumLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.headers.too.long/client.no.response",
        "${scripts}/response.headers.too.long/server.no.response"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldRejectNetworkResponseWithHeadersTooLong() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.headers.too.long/client.response.reset",
        "${scripts}/response.headers.too.long/server.response.reset"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldRejectApplicationResponseWithHeadersTooLong() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.headers.with.padding/client",
            "${scripts}/response.headers.with.padding/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldProcessResponseHeadersFragmentedByPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.fragmented.with.padding/client",
            "${scripts}/response.fragmented.with.padding/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldProcessResponseFragmentedByPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.with.padding/client",
            "${scripts}/response.with.padding/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldProcessResponseWithPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

}
