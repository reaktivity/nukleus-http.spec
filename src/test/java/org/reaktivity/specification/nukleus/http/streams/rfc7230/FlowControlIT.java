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
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;
import org.reaktivity.specification.nukleus.NukleusRule;

public class FlowControlIT
{
    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("streams", "org/reaktivity/specification/nukleus/http/streams/rfc7230/flow.control");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    private final NukleusRule nukleus = new NukleusRule()
        .directory("target/nukleus-itests")
        .streams("http", "source")
        .streams("source", "http#source")
        .streams("target", "http#source")
        .streams("http", "target")
        .streams("source", "http#target");

    @Rule
    public final TestRule chain = outerRule(nukleus).around(k3po).around(timeout);

    @Test
    @Specification({
        "${streams}/request.with.content.length.and.end.late.target.window/server/source",
        "${streams}/request.with.content.length.and.end.late.target.window/server/nukleus",
        "${streams}/request.with.content.length.and.end.late.target.window/server/target" })
    public void shouldWaitForTargetWindowAndWriteDataBeforeProcessingSourceEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.fragmented/server/source",
        "${streams}/request.fragmented/server/nukleus",
        "${streams}/request.fragmented/server/target" })
    public void shouldAcceptFragmentedRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.fragmented.with.content.length/server/source",
        "${streams}/request.fragmented.with.content.length/server/nukleus",
        "${streams}/request.fragmented.with.content.length/server/target" })
    public void shouldAcceptFragmentedRequestWithContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.headers.too.long/server/source",
        "${streams}/request.headers.too.long/server/nukleus" })
    public void shouldRejectRequestExceedingMaximumHeadersSize() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.with.content.flow.controlled/server/source",
        "${streams}/request.with.content.flow.controlled/server/nukleus",
        "${streams}/request.with.content.flow.controlled/server/target"})
    public void shouldSplitRequestDataToRespectTargetWindow() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.with.fragmented.content.flow.controlled/server/source",
        "${streams}/request.with.fragmented.content.flow.controlled/server/nukleus",
        "${streams}/request.with.fragmented.content.flow.controlled/server/target" })
    public void shouldAcceptRequestWithFragmentedContentWithTargetFlowControl() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.flow.controlled/server/source",
        "${streams}/response.flow.controlled/server/nukleus",
        "${streams}/response.flow.controlled/server/target" })
    public void shouldFlowControlResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.with.content.flow.controlled/server/source",
        "${streams}/response.with.content.flow.controlled/server/nukleus",
        "${streams}/response.with.content.flow.controlled/server/target" })
    public void shouldFlowControlResponseWithContent() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.headers.too.long/server/source",
        "${streams}/response.headers.too.long/server/nukleus",
        "${streams}/response.headers.too.long/server/target" })
    public void shouldNotWriteResponseExceedingMaximumHeadersSize() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/multiple.requests.combined/server/source",
        "${streams}/multiple.requests.combined/server/nukleus",
        "${streams}/multiple.requests.combined/server/target" })
    public void shouldAcceptMultipleRequestsInSameDataFrame() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/multiple.requests.combined.fragmented/server/source",
        "${streams}/multiple.requests.combined.fragmented/server/nukleus",
        "${streams}/multiple.requests.combined.fragmented/server/target" })
    public void shouldAcceptMultipleRequestsInSameDataFrameFragmented() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/multiple.requests.with.content.length.combined.fragmented/server/source",
        "${streams}/multiple.requests.with.content.length.combined.fragmented/server/nukleus",
        "${streams}/multiple.requests.with.content.length.combined.fragmented/server/target" })
    public void shouldAcceptMultipleRequestsWithContentLengthCombinedFragmented() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.fragmented/client/source",
        "${streams}/response.fragmented/client/nukleus",
        "${streams}/response.fragmented/client/target" })
    public void shouldAcceptFragmentedResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }
    @Test
    @Specification({
        "${streams}/response.fragmented.with.content.length/client/source",
        "${streams}/response.fragmented.with.content.length/client/nukleus",
        "${streams}/response.fragmented.with.content.length/client/target" })
    public void shouldAcceptFragmentedResponseWithContentLength() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.headers.too.long/client/source",
        "${streams}/response.headers.too.long/client/nukleus",
        "${streams}/response.headers.too.long/client/target"})
    public void shouldRejectResponseExceedingMaximumHeadersSize() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.with.content.flow.controlled/client/source",
        "${streams}/response.with.content.flow.controlled/client/nukleus",
        "${streams}/response.with.content.flow.controlled/client/target" })
    public void shouldSplitResponseDataToRespectTargetWindow() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.with.fragmented.content.flow.controlled/client/source",
        "${streams}/response.with.fragmented.content.flow.controlled/client/nukleus",
        "${streams}/response.with.fragmented.content.flow.controlled/client/target" })
    public void shouldSlabResponseDataWhenTargetWindowStillNegative() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/response.with.content.length.and.end.late.target.window/client/source",
        "${streams}/response.with.content.length.and.end.late.target.window/client/nukleus",
        "${streams}/response.with.content.length.and.end.late.target.window/client/target" })
    public void shouldWaitForSourceWindowAndWriteDataBeforeProcessingTargetEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.flow.controlled/client/source",
        "${streams}/request.flow.controlled/client/nukleus",
        "${streams}/request.flow.controlled/client/target" })
    public void shouldFlowControlRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.with.content.flow.controlled/client/source",
        "${streams}/request.with.content.flow.controlled/client/nukleus",
        "${streams}/request.with.content.flow.controlled/client/target" })
    public void shouldFlowControlRequestWithContent() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/request.headers.too.long/client/source",
        "${streams}/request.headers.too.long/client/nukleus" })
    public void shouldNotWriteRequestExceedingMaximumHeadersSize() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_OUTPUT");
        k3po.finish();
    }
}
