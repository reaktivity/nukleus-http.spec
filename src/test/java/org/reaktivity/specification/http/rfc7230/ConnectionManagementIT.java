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

public class ConnectionManagementIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/http/rfc7230/connection.management");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.1">RFC 7230 section 6.1: Connection</a>.
     *
     * The "close" connection option is defined for a sender to signal that this connection will be closed after
     * completion of the response. For example,
     *
     * Connection: close
     *
     * in either the request or the response header fields indicates that the sender is going to close the connection
     * after the current request/response is complete (Section 6.6).
     *
     * See also <a href="https://tools.ietf.org/html/rfc7230#section-6.6">RFC 7230 section 6.6: Tear-down</a>. <blockquote> A
     * server that receives a "close" connection option MUST initiate a close of the connection (see below) after it
     * sends the final response to the request that contained "close". The server SHOULD send a "close" connection
     * option in its final response on that connection. The server MUST NOT process any further requests received on
     * that connection. </blockquote>
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/request.with.connection.close/client",
        "${scripts}/request.with.connection.close/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldCloseConnectionAfterRequestWithConnectionClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }


    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.1">RFC 7230 section 6.1: Connection</a>.
     *
     * The "close" connection option is defined for a sender to signal that this connection will be closed after
     * completion of the response. For example,
     *
     * Connection: close
     *
     * in either the request or the response header fields indicates that the sender is going to close the connection
     * after the current request/response is complete (Section 6.6).
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}response.with.connection.close/client",
        "${scripts}response.with.connection.close/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldCloseConnectionAfterSendingResponseWithConnectionClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.3">RFC 7230 section 6.3: Persistence</a>.
     *
     * HTTP implementations SHOULD support persistent connections.
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/concurrent.requests.different.connections/client",
        "${scripts}/concurrent.requests.different.connections/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldProcessConcurrentRequestsOnDifferentConnections() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.3">RFC 7230 section 6.3: Persistence</a>.
     *
     * HTTP implementations SHOULD support persistent connections.
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/multiple.requests.same.connection/client",
        "${scripts}/multiple.requests.same.connection/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldProcessMultipleRequestsOnSameConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.notifyBarrier("WRITE_RESPONSE_ONE");
        k3po.notifyBarrier("WRITE_RESPONSE_TWO");
        k3po.notifyBarrier("WRITE_RESPONSE_THREE");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.3.2">RFC 7230 section 6.3.2: Pipelining</a>.
     * <p>
     * A client can send multiple requests with out getting a response, but a server must send responses back it order
     * </p>
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/multiple.requests.pipelined/client",
        "${scripts}/multiple.requests.pipelined/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldProcessMultipleRequestsPipelined() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.3.2">RFC 7230 section 6.3.2: Pipelining</a>.
     * <p>
     * If client is pipelining requests and the connection closes from underneath the client should retry requests, and
     * the first retry must not be pipelined with other requests
     * </p>
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/multiple.requests.pipelined.with.retry/client",
        "${scripts}/multiple.requests.pipelined.with.retry/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldNotRetryPipeliningImmediatelyAfterFailure() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.6">RFC 7230 section 6.6: Tear-down</a>. <blockquote> A
     * client that receives a "close" connection option MUST cease sending requests on that connection and close the
     * connection after reading the response message containing the "close"; if additional pipelined requests had been
     * sent on the connection, the client SHOULD NOT assume that they will be processed by the server. </blockquote>
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/first.pipelined.response.has.connection.close/client",
        "${scripts}/first.pipelined.response.has.connection.close/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldNotReuseConnectionAfterResponseWithConnectionClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }


    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.7">RFC 7230 section 6.7: Upgrade</a>. <blockquote> A
     * server that sends a 101 (Switching Protocols) response MUST send an Upgrade header field to indicate the new
     * protocol(s) to which the connection is being switched; if multiple protocol layers are being switched, the sender
     * MUST list the protocols in layer-ascending order. </blockquote>
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/upgrade.request.and.response/client",
        "${scripts}/upgrade.request.and.response/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldRespondWithUpgradeHeaderWhenActingAsServer() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.7">RFC 7230 section 6.7: Upgrade</a>. <blockquote> A
     * server that sends a 426 (Upgrade Required) response MUST send an Upgrade header field to indicate the acceptable
     * protocols, in order of descending preference. </blockquote>
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/request.and.upgrade.required.response/client",
        "${scripts}/request.and.upgrade.required.response/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldIncludeUpgradeHeaderWithUpgradeRequiredResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc7230#section-6.7">RFC 7230 section 6.7: Upgrade</a>.
     *
     * <blockquote> if the Upgrade header field is received in a GET request and the server decides to switch protocols,
     * it first responds with a 101 (Switching Protocols) message in HTTP/1.1 and then immediately follows that with the
     * new protocol's equivalent of a response to a GET on the target resource. </blockquote>
     *
     * @throws Exception when K3PO is not started
     */
    @Test
    @Specification({
        "${scripts}/upgrade.request.and.response.with.data/client",
        "${scripts}/upgrade.request.and.response.with.data/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldSendA101ResponseBeforeData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.incomplete.response.headers.and.abort/client",
        "${scripts}/request.incomplete.response.headers.and.abort/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldReportResponseAbortedBeforeResponseHeadersComplete() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.incomplete.response.headers.and.end/client",
        "${scripts}/request.incomplete.response.headers.and.end/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldReportResponseEndedBeforeResponseHeadersComplete() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.incomplete.response.headers.and.reset/client",
        "${scripts}/request.incomplete.response.headers.and.reset/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldReportConnectStreamResetBeforeResponseHeadersComplete() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.no.response.and.end/client",
        "${scripts}/request.no.response.and.end/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldHandleConnectReplyStreamEndedWithNoResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.no.response.and.reset/client",
        "${scripts}/request.no.response.and.reset/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldHandleConnectStreamResetWithNoResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.response.headers.incomplete.data.and.abort/client",
        "${scripts}/request.response.headers.incomplete.data.and.abort/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldReportResponseAbortedWithIncompleteData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.response.headers.incomplete.data.and.end/client",
        "${scripts}/request.response.headers.incomplete.data.and.end/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldReportResponseEndedWithIncompleteData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.response.headers.incomplete.data.and.reset/client",
        "${scripts}/request.response.headers.incomplete.data.and.reset/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldReportConnectStreamResetWhenResponseHasIncompleteData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.and.abort/client",
        "${scripts}/request.and.abort/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldAbortRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/pending.request.second.request.and.abort/client",
        "${scripts}/pending.request.second.request.and.abort/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldAbortEnqueuedRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.notifyBarrier("SEND_FIRST_RESPONSE");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/partial.request.receive.reset/client",
        "${scripts}/partial.request.receive.reset/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldReportRequestReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.response.and.end/client",
        "${scripts}/request.response.and.end/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldProcessRequestResponseAndEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.response.and.reset/client",
        "${scripts}/request.response.and.reset/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldProcessRequestResponseAndReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }


    @Test
    @Specification({
        "${scripts}/send.end.after.upgrade.request.completed/client",
        "${scripts}/send.end.after.upgrade.request.completed/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void  shouldSendEndWhenEndReceivedAfterUpgradeRequestCompleted() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    // Proxy tests only have "cooked" versions

    @Test
    @Specification({
        "${scripts}/request.response.and.abort/client",
        "${scripts}/request.response.and.abort/server" })
    @ScriptProperty("serverTransport \"nukleus://streams/http#0\"")
    public void shouldFreeConnectionWhenAbortReceivedAfterCompleteResponse() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }
}
