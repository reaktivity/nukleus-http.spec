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
package org.reaktivity.specification.nukleus.http.streams.server.rfc7230.newsystem;

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

public class ArchitectureIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("streams", "org/reaktivity/specification/nukleus/http/streams/rfc7230/architecture");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    private final NukleusRule nukleus = new NukleusRule()
        .directory("target/nukleus-itests")
        .streams("target", "source")
        .streams("source", "target");

    @Rule
    public final TestRule chain = outerRule(nukleus).around(k3po).around(timeout);

    /*
     * The two high-level scripts are running against each other,
     * so are operating on the same streams files.
     * client is writing to target/source, reading from source/target
     * server is reading from target/source, writing to target/source
    */
    @Test
    @Specification({
        "${streams}/request.and.response/cooked/client",
        "${streams}/request.and.response/cooked/server"})
    @ScriptProperty({"target \"target\", source \"source\""})
    public void shouldCorrelateRequestAndResponseCooked() throws Exception
    {
        k3po.finish();
    }

    /*
     * The two raw scripts are running against each other,
     * so are operating on the same streams files.
     * client is writing to target/source, reading from source/target
     * server is reading from target/source, writing to source/target
    */
    @Test
    @Specification({
        "${streams}/request.and.response/raw/client",
        "${streams}/request.and.response/raw/server"})
    @ScriptProperty({
        "httpClientOptions {\"http:transport\":\"nukleus://streams/target\"," +
                      "\"nukleus:source\":\"source\"," +
                      "\"nukleus:route\":${nuklei:newReferenceId()}," +
                      "\"nukleus:window\":\"8192\"," +
                      "\"nukleus:transmission\":\"duplex\"}",
        "httpServerOptions {\"http:transport\":\"nukleus://streams/target\"," +
                      "\"nukleus:source\":\"source\"," +
                      "\"nukleus:route\":${nuklei:newReferenceId()}," +
                      "\"nukleus:window\":\"8192\"," +
                      "\"nukleus:transmission\":\"duplex\"}"

    })
    public void shouldCorrelateRequestAndResponseRaw() throws Exception
    {
        k3po.finish();
    }

    /*
     * The raw client script is simulating like an http request coming in in raw form
     * from tcp nukleus into http/source, so writing the raw request into http/source
     * and reading the (encoded, raw) response from source/http.
     * The cooked server script is reading that decoded request in high level form
     * from target/http and writing the high-level response back to http/target.
    */
    @Test
    @Specification({
        "${route}/input/new/controller",
        "${streams}/request.and.response/cooked/server",
        "${streams}/request.and.response/raw/client"})
    @ScriptProperty({
        "source \"http\"",
        "httpClientOptions {\"http:transport\":\"nukleus://streams/http\"," +
                      "\"nukleus:source\":\"source\"," +
                      "\"nukleus:route\":${newSourceInputRef}," + // property created by controller script
                      "\"nukleus:window\":\"8192\"," +
                      "\"nukleus:transmission\":\"duplex\"}"
    })
    public void testHttpNukleusAsServer() throws Exception
    {
        k3po.finish();
    }

    /*
     * The raw server script is behaving like an encoded http request going out to
     * to transport nukleus in target/http, so reading the request from target/http
     * and reading the (encoded) response into http/target.
     * The cooked client script is writing the high-level HTTP request to
     * http/source and reading the decoded high-level response from source/http.
    */
    @Test
    @Specification({
        "${route}/output/new/controller",
        "${streams}/request.and.response/raw/server",
        "${streams}/request.and.response/cooked/client"})
    @ScriptProperty({
        "source \"http\"",
        "httpServerOptions {\"http:transport\":\"nukleus://streams/http\"," +
                      "\"nukleus:source\":\"source\"," +
                      "\"nukleus:route\":${newTargetOutputRef}," + // property created by controller script
                      "\"nukleus:window\":\"8192\"," +
                      "\"nukleus:transmission\":\"duplex\"}"
    })
    public void testHttpNukleusAsClient() throws Exception
    {
        k3po.finish();
    }

}
