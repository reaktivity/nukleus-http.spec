/**
 * Copyright 2016-2021 The Reaktivity Project
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
package org.reaktivity.specification.nukleus.http2.streams.rfc7540;

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

public class MessageFormatIT
{
    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("nukleus", "org/reaktivity/specification/nukleus/http2/streams/rfc7540/message.format");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/continuation.frames/client",
            "${nukleus}/continuation.frames/server"
    })
    public void continuationFrames() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/dynamic.table.requests/client",
            "${nukleus}/dynamic.table.requests/server"
    })
    public void dynamicTableRequests() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/max.frame.size/client",
            "${nukleus}/max.frame.size/server"
    })
    public void maxFrameSize() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/max.frame.size.error/client",
            "${nukleus}/max.frame.size.error/server"
    })
    public void maxFrameSizeError() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/priority.frame.size.error/client",
            "${nukleus}/priority.frame.size.error/server"
    })
    public void priorityFrameSizeError() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/rst.stream.frame.size.error/client",
            "${nukleus}/rst.stream.frame.size.error/server"
    })
    public void rstStreamFrameSizeError() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/window.frame.size.error/client",
            "${nukleus}/window.frame.size.error/server"
    })
    public void windowFrameSizeError() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/max.nukleus.data.frame.size/client",
            "${nukleus}/max.nukleus.data.frame.size/server"
    })
    public void maxNukleusDataFrameSize() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/connection.headers/client",
            "${nukleus}/connection.headers/server"
    })
    public void connectionHeaders() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://streams/http2#0\"")
    @Specification({
            "${nukleus}/stream.id.order/client",
            "${nukleus}/stream.id.order/server"
    })
    public void streamIdOrder() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }
}
