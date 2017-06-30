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
import org.reaktivity.specification.nukleus.NukleusRule;

public class FlowControlIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/http/rfc7230/flow.control");

    private final NukleusRule nukleus = new NukleusRule()
            .directory("target/nukleus-itests");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(nukleus).around(timeout);

    @Test
    @Specification({
        "${scripts}/request.with.content.length.and.end.late.target.window/client",
        "${scripts}/request.with.content.length.and.end.late.target.window/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void shouldWaitForTargetWindowAndWriteDataBeforeProcessingSourceEnd() throws Exception
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
        "${scripts}/multiple.requests.pipelined.fragmented/client",
        "${scripts}/multiple.requests.pipelined.fragmented/server"})
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void multipleRequestsPipelinedFragmented() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }


}
