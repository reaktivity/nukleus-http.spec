/**
 * Copyright 2016-2019 The Reaktivity Project
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

public class ConnectionManagementIT
{
    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("scripts", "org/reaktivity/specification/nukleus/http/streams/rfc7230/connection.management");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "${scripts}/request.with.connection.close/client",
        "${scripts}/request.with.connection.close/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void clientAndServerMustCloseConnectionAfterRequestWithConnectionClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}response.with.connection.close/client",
        "${scripts}response.with.connection.close/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void serverMustCloseConnectionAfterResponseWithConnectionClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/concurrent.requests/client",
        "${scripts}/concurrent.requests/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void shouldProcessConcurrentRequests() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.notifyBarrier("REQUEST_ONE_RECEIVED");
        k3po.notifyBarrier("REQUEST_TWO_RECEIVED");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/concurrent.requests.with.content/client",
        "${scripts}/concurrent.requests.with.content/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void concurrentRequestsWithContent() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.notifyBarrier("REQUEST_ONE_RECEIVED");
        k3po.notifyBarrier("REQUEST_TWO_RECEIVED");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/multiple.requests.serialized/client",
        "${scripts}/multiple.requests.serialized/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void multipleRequestsSerialized() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/first.pipelined.response.has.connection.close/client",
        "${scripts}/first.pipelined.response.has.connection.close/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void clientMustNotReuseConnectionWhenReceivesConnectionClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/upgrade.request.and.response/client",
        "${scripts}/upgrade.request.and.response/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void serverGettingUpgradeRequestMustRespondWithUpgradeHeader() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.503.response/client",
        "${scripts}/request.and.503.response/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void requestAnd503Response() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.response.twice/client",
        "${scripts}/request.and.response.twice/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void requestAndResponseTwice() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.response.twice.awaiting.barrier/client",
        "${scripts}/request.and.response.twice.awaiting.barrier/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void requestAndResponseTwiceAwaitingBarrier() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.notifyBarrier("ISSUE_SECOND_REQUEST");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.response.with.incomplete.data/client",
        "${scripts}/request.and.response.with.incomplete.data/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void responseWithContentLengthAndIncompleteData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.response.with.incomplete.data.and.abort/client",
        "${scripts}/request.and.response.with.incomplete.data.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void responseWithContentLengthAndIncompleteDataAndAbort() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.response.with.incomplete.data.and.end/client",
        "${scripts}/request.and.response.with.incomplete.data.and.end/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void responseWithContentLengthAndIncompleteDataAndEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Ignore("Requires connect aborted, see k3po/k3po#454")
    @Test
    @Specification({
        "${scripts}/request.and.abort/client",
        "${scripts}/request.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void shouldProcessAbortFromClient() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/pending.request.second.request.and.abort/client",
        "${scripts}/pending.request.second.request.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void shouldProcessAbortFromClientWithPendingRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.receive.reset/client",
        "${scripts}/request.receive.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void requestIsReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.with.content.length.is.reset/client",
        "${scripts}/response.with.content.length.is.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void responseWithContentLengthIsReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.upgrade.required.response/client",
        "${scripts}/request.and.upgrade.required.response/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void serverThatSendsUpgradeRequiredMustIncludeUpgradeHeader() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/upgrade.request.and.response.with.data/client",
        "${scripts}/upgrade.request.and.response.with.data/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void serverThatIsUpgradingMustSendA101ResponseBeforeData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/concurrent.upgrade.requests.and.responses.with.data/client",
        "${scripts}/concurrent.upgrade.requests.and.responses.with.data/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void concurrentUpgradeRequestsandResponsesWithData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    // Proxy tests only have "cooked" versions

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.1">RFC 7230 section 6.1: Connection</a>.
     *
     * In order to avoid confusing downstream recipients, a proxy or gateway MUST remove or replace any received
     * connection options before forwarding the message.
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/proxy.must.not.forward.connection.header/client",
        "${scripts}/proxy.must.not.forward.connection.header/proxy",
        "${scripts}/proxy.must.not.forward.connection.header/backend" })
    public void intermediaryMustRemoveConnectionHeaderOnForwardRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
            "${scripts}/reverse.proxy.connection.established/client",
            "${scripts}/reverse.proxy.connection.established/proxy",
            "${scripts}/reverse.proxy.connection.established/backend" })
    public void reverseProxyConnectionEstablished() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.3.1">RFC 7230 section 6.3.1: Retrying Requests</a>.
     *
     * A proxy MUST NOT automatically retry non-idempotent requests.
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/proxy.must.not.retry.non.idempotent.requests/client",
        "${scripts}/proxy.must.not.retry.non.idempotent.requests/proxy",
        "${scripts}/proxy.must.not.retry.non.idempotent.requests/backend" })
    public void proxyMustNotRetryNonIdempotentRequests() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/send.end.after.upgrade.request.completed/client",
        "${scripts}/send.end.after.upgrade.request.completed/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void shouldSendEndWhenEndReceivedAfterUpgradeRequestCompleted() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/upgrade.request.and.abort/client",
        "${scripts}/upgrade.request.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void serverGettingAbortShouldPropagateAbortOnAllDirections() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.response/client",
        "${scripts}/request.response/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void requestAndResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.with.header.override/client",
        "${scripts}/request.with.header.override/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void shouldProxyRequestWithHeaderOverride() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.authority.with.no.port/client",
        "${scripts}/request.authority.with.no.port/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/http#0\"")
    public void requestAuthorityWithNoPort() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }
}
