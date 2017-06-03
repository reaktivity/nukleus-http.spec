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
import org.reaktivity.specification.nukleus.NukleusRule;

public class FlowControlIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/nukleus/http/streams/rfc7230/flow.control");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    private final NukleusRule nukleus = new NukleusRule()
        .directory("target/nukleus-itests");

    @Rule
    public final TestRule chain = outerRule(nukleus).around(k3po).around(timeout);

    @Test
    @Specification({
        "${scripts}/request.with.content.length.and.end.late.target.window/client",
        "${scripts}/request.with.content.length.and.end.late.target.window/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldWaitForTargetWindowAndWriteDataBeforeProcessingSourceEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.fragmented/client",
        "${scripts}/request.fragmented/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    public void shouldAcceptFragmentedRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    //////////////////// DONE TO HERE

    @Test
    @Specification({
        "${scripts}/request.fragmented.with.content.length/client",
        "${scripts}/request.fragmented.with.content.length/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldAcceptFragmentedRequestWithContentLength() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.with.content.flow.controlled/client",
        "${scripts}/request.with.content.flow.controlled/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldSplitRequestDataToRespectTargetWindow() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.with.fragmented.content.flow.controlled/client",
        "${scripts}/request.with.fragmented.content.flow.controlled/server"})
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldAcceptRequestWithFragmentedContentWithTargetFlowControl() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.flow.controlled/client",
        "${scripts}/response.flow.controlled/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldFlowControlResponse() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.with.content.flow.controlled/client",
        "${scripts}/response.with.content.flow.controlled/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldFlowControlResponseWithContent() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.headers.too.long/client",
        "${scripts}/response.headers.too.long/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldNotWriteResponseExceedingMaximumHeadersSize() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/multiple.requests.pipelined/client",
        "${scripts}/multiple.requests.pipelined/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldAcceptMultipleRequestsInSameDataFrame() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/multiple.requests.pipelined.fragmented/client",
        "${scripts}/multiple.requests.pipelined.fragmented/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldAcceptMultipleRequestsInSameDataFrameFragmented() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/multiple.requests.with.content.length.pipelined.fragmented/client",
        "${scripts}/multiple.requests.with.content.length.pipelined.fragmented/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldAcceptMultipleRequestsWithContentLengthPipelinedFragmented() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/multiple.requests.with.response.flow.control/client",
        "${scripts}/multiple.requests.with.response.flow.control/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldFlowControlMultipleResponses() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.with.upgrade.and.data/client",
        "${scripts}/request.with.upgrade.and.data/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldFlowControlRequestDataAfterUpgrade() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.with.upgrade.and.data/client",
        "${scripts}/response.with.upgrade.and.data/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldFlowControlResponseDataAfterUpgrade() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.fragmented/client",
        "${scripts}/response.fragmented/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldAcceptFragmentedResponse() throws Exception
    {
        k3po.finish();
    }
    @Test
    @Specification({
        "${scripts}/response.fragmented.with.content.length/client",
        "${scripts}/response.fragmented.with.content.length/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldAcceptFragmentedResponseWithContentLength() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.headers.too.long/client",
        "${scripts}/response.headers.too.long/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldRejectResponseExceedingMaximumHeadersSize() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.with.content.flow.controlled/client",
        "${scripts}/response.with.content.flow.controlled/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldSplitResponseDataToRespectTargetWindow() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.with.fragmented.content.flow.controlled/client",
        "${scripts}/response.with.fragmented.content.flow.controlled/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldSlabResponseDataWhenTargetWindowStillNegative() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.with.content.length.and.end.late.target.window/client",
        "${scripts}/response.with.content.length.and.end.late.target.window/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldWaitForSourceWindowAndWriteDataBeforeProcessingTargetEnd() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.flow.controlled/client",
        "${scripts}/request.flow.controlled/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldFlowControlRequest() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.with.content.flow.controlled/client",
        "${scripts}/request.with.content.flow.controlled/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldFlowControlRequestWithContent() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.headers.too.long/client",
        "${scripts}/request.headers.too.long/server" })
    @ScriptProperty("serverConnect \"nukleus://http/streams/source\"")
    @Ignore("Not yet migrated onto k3po nukleus transport")
    public void shouldNotWriteRequestExceedingMaximumHeadersSize() throws Exception
    {
        k3po.finish();
    }


}
