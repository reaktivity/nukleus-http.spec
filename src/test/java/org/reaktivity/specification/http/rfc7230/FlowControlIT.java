/**
 * Copyright 2016-2018 The Reaktivity Project
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
package org.reaktivity.specification.http.rfc7230;

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
        .addScriptRoot("scripts", "org/reaktivity/specification/http/rfc7230/flow.control");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "${scripts}/multiple.requests.pipelined.fragmented/client",
        "${scripts}/multiple.requests.pipelined.fragmented/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void multipleRequestsPipelinedFragmented() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/multiple.requests.with.content.length.pipelined.fragmented/client",
        "${scripts}/multiple.requests.with.content.length.pipelined.fragmented/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void multipleRequestsWithContentLengthPipelinedFragmented() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.fragmented/client",
        "${scripts}/request.fragmented/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void fragmentedRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.fragmented.with.content.length/client",
        "${scripts}/request.fragmented.with.content.length/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void fragmentedRequestWithContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.with.content.length.and.transport.close/client",
        "${scripts}/request.with.content.length.and.transport.close/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void shouldDeferEndProcessingUntilRequestProcessed() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.chunked.with.extensions.filling.maximum.headers/client",
        "${scripts}/response.chunked.with.extensions.filling.maximum.headers/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessResponseWhenFirstChunkMetadataFillsMaxHeaders() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.first.fragment.maximum.headers/client",
        "${scripts}/response.first.fragment.maximum.headers/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessResponseWhenFirstFragmentIsHeadersOfLength64() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.fragmented/client",
        "${scripts}/response.fragmented/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessFragmentedResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.fragmented.with.content.length/client",
        "${scripts}/response.fragmented.with.content.length/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessFragmentedResponseWithContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.headers.too.long/client.response.reset",
        "${scripts}/response.headers.too.long/server.response.reset"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldRejectNetworkResponseWithHeadersTooLong() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.fragmented.with.padding/client",
            "${scripts}/response.fragmented.with.padding/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessResponseFragmentedByPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.headers.with.padding/client",
            "${scripts}/response.headers.with.padding/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessResponseHeadersFragmentedByPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }


    @Test
    @Specification({
            "${scripts}/request.with.padding/client",
            "${scripts}/request.with.padding/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessRequestWithPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.with.padding/client",
            "${scripts}/response.with.padding/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldProcessResponseWithPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/response.with.content.exceeding.window/client",
            "${scripts}/response.with.content.exceeding.window/server"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldHandleResponseWithContentViolatingWindow() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.headers.too.long/client.5xx.response",
        "${scripts}/response.headers.too.long/server.5xx.response"})
    @ScriptProperty({"serverTransport \"nukleus://http/streams/source\""})
    public void shouldRejectApplicationResponseWithHeadersTooLong() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.with.content.length.and.transport.close/client",
        "${scripts}/response.with.content.length.and.transport.close/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void shouldDeferEndProcessingUntilResponseProcessed() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

}
