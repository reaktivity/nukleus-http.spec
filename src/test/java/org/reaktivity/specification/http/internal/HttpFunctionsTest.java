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
package org.reaktivity.specification.http.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.kaazing.k3po.lang.internal.el.ExpressionFactoryUtils.newExpressionFactory;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.agrona.DirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kaazing.k3po.lang.internal.el.ExpressionContext;
import org.reaktivity.specification.http.internal.types.control.HttpRouteExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpBeginExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpChallengeExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpDataExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpEndExFW;

public class HttpFunctionsTest
{
    private ExpressionFactory factory;
    private ELContext ctx;

    @Before
    public void setUp() throws Exception
    {

        factory = newExpressionFactory();
        ctx = new ExpressionContext();
    }

    @Test
    public void shouldInvokeFunctionRandomInvalidVersion() throws Exception
    {
        String expressionText = "${http:randomInvalidVersion()}";
        ValueExpression expression = factory.createValueExpression(ctx, expressionText, String.class);
        String randomBytes = (String) expression.getValue(ctx);
        Assert.assertNotNull(randomBytes);
    }

    @Test
    public void shouldInvokeFunctionRandomBytes() throws Exception
    {
        String expressionText = "${http:randomBytes(42)}";
        ValueExpression expression = factory.createValueExpression(ctx, expressionText, byte[].class);
        expression.getValue(ctx);
    }

    @Test
    public void shouldGenerateRouteExtension()
    {
        byte[] build = HttpFunctions.routeEx().header("name", "value").build();
        DirectBuffer buffer = new UnsafeBuffer(build);
        HttpRouteExFW routeEx = new HttpRouteExFW().wrap(buffer, 0, buffer.capacity());
        routeEx.headers().forEach(onlyHeader ->
        {
            assertEquals("name", onlyHeader.name().asString());
            assertEquals("value", onlyHeader.value().asString());
        });
        assertTrue(routeEx.sizeof() > 0);
    }

    @Test
    public void shouldGenerateBeginExtension()
    {
        byte[] build = HttpFunctions.beginEx()
                                    .typeId(0x01)
                                    .header("name", "value")
                                    .build();
        DirectBuffer buffer = new UnsafeBuffer(build);
        HttpBeginExFW beginEx = new HttpBeginExFW().wrap(buffer, 0, buffer.capacity());
        assertEquals(0x01, beginEx.typeId());
        beginEx.headers().forEach(onlyHeader ->
        {
            assertEquals("name", onlyHeader.name().asString());
            assertEquals("value", onlyHeader.value().asString());
        });
        assertTrue(beginEx.headers().sizeof() > 0);
    }

    @Test
    public void shouldGenerateDataExtension()
    {
        byte[] build = HttpFunctions.dataEx()
                                    .typeId(0x01)
                                    .promise("name", "value")
                                    .build();
        DirectBuffer buffer = new UnsafeBuffer(build);
        HttpDataExFW dataEx = new HttpDataExFW().wrap(buffer, 0, buffer.capacity());
        assertEquals(0x01, dataEx.typeId());
        dataEx.promise().forEach(onlyHeader ->
        {
            assertEquals("name", onlyHeader.name().asString());
            assertEquals("value", onlyHeader.value().asString());
        });
        assertTrue(dataEx.promise().sizeof() > 0);
    }

    @Test
    public void shouldGenerateEndExtension()
    {
        byte[] build = HttpFunctions.endEx()
                                    .typeId(0x01)
                                    .trailer("name", "value")
                                    .build();
        DirectBuffer buffer = new UnsafeBuffer(build);
        HttpEndExFW endEx = new HttpEndExFW().wrap(buffer, 0, buffer.capacity());
        assertEquals(0x01, endEx.typeId());
        endEx.trailers().forEach(onlyHeader ->
        {
            assertEquals("name", onlyHeader.name().asString());
            assertEquals("value", onlyHeader.value().asString());
        });
        assertTrue(endEx.trailers().sizeof() > 0);
    }

    @Test
    public void shouldGenerateChallengeExtension()
    {
        byte[] build = HttpFunctions.challengeEx()
                                    .typeId(0x01)
                                    .header("name", "value")
                                    .build();
        DirectBuffer buffer = new UnsafeBuffer(build);
        HttpChallengeExFW challengeEx = new HttpChallengeExFW().wrap(buffer, 0, buffer.capacity());
        assertEquals(0x01, challengeEx.typeId());
        challengeEx.headers().forEach(onlyHeader ->
        {
            assertEquals("name", onlyHeader.name().asString());
            assertEquals("value", onlyHeader.value().asString());
        });
        assertTrue(challengeEx.headers().sizeof() > 0);
    }
}
