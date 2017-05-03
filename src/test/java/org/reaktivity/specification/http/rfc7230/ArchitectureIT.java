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
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;
import org.reaktivity.specification.nukleus.NukleusRule;

public class ArchitectureIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/http/rfc7230/architecture");

    private final NukleusRule nukleus = new NukleusRule()
            .directory("target/nukleus-itests");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(nukleus).around(timeout);

    @Test
    @Specification({
        "${scripts}/request.and.response/client",
        "${scripts}/request.and.response/server"})
    public void requestAndResponse() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.header.host.missing/client",
        "${scripts}/request.header.host.missing/server"})
    public void shouldRejectRequestWhenHostHeaderMissing() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.uri.with.percent.chars/client",
        "${scripts}/request.uri.with.percent.chars/server"})
    public void shouldAcceptRequestWithPercentChars() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.uri.with.user.info/client",
        "${scripts}/request.uri.with.user.info/server"})
    public void shouldRejectRequestWithUserInfo() throws Exception
    {
        k3po.finish();
    }


    @Test
    @Specification({
        "${scripts}/request.version.1.2+/client",
        "${scripts}/request.version.1.2+/server"})
    public void shouldRespondVersionHttp11WhenRequestVersionHttp12plus() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.version.invalid/client",
        "${scripts}/request.version.invalid/server"})
    public void shouldRejectRequestWhenVersionInvalid() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.version.missing/client",
        "${scripts}/request.version.missing/server"})
    public void shouldRejectRequestWhenVersionMissing() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.version.not.http.1.x/client",
        "${scripts}/request.version.not.http.1.x/server"})
    public void shouldRejectRequestWhenVersionNotHttp1x() throws Exception
    {
        k3po.finish();
    }
}
