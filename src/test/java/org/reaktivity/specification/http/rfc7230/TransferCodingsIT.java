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

public class TransferCodingsIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/http/rfc7230/transfer.codings");

    private final NukleusRule nukleus = new NukleusRule()
            .directory("target/nukleus-itests");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(nukleus).around(timeout);

    @Test
    @Specification({
        "${scripts}/request.transfer.encoding.chunked/client",
        "${scripts}/request.transfer.encoding.chunked/server" })
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void requestTransferEncodingChunked() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/invalid.chunked.request.no.crlf.at.end.of.chunk/client",
        "${scripts}/invalid.chunked.request.no.crlf.at.end.of.chunk/server" })
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void invalidRequestTransferEncodingChunked() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.transfer.encoding.chunked/client",
        "${scripts}/response.transfer.encoding.chunked/server" })
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void responseTransferEncodingChunked() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Ignore("${scripts}/requires enhancement https://github.com/k3po/k3po/issues/313")
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void requestTransferEncodingChunkedExtension() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Ignore("${scripts}/requires enhancement https://github.com/k3po/k3po/issues/313")
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void responseTransferEncodingChunkedExtension() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.transfer.encoding.chunked.with.trailer/client",
        "${scripts}/request.transfer.encoding.chunked.with.trailer/server" })
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void requestTransferEncodingChunkedWithTrailer() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.transfer.encoding.chunked.with.trailer/client",
        "${scripts}/response.transfer.encoding.chunked.with.trailer/server" })
    @ScriptProperty("serverTransport \"nukleus://http/streams/source\"")
    public void responseTransferEncodingChunkedWithTrailer() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

}
