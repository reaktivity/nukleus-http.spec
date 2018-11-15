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
package org.reaktivity.specification.http.internal;

import static org.kaazing.k3po.lang.internal.el.ExpressionFactoryUtils.newExpressionFactory;
import static org.reaktivity.specification.http.internal.Functions.headers;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.agrona.DirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kaazing.k3po.lang.internal.el.ExpressionContext;
import org.reaktivity.specification.http.internal.types.HttpHeaderFW;
import org.reaktivity.specification.http.internal.types.ListFW;

public class FunctionsTest
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
    public void shouldLoadFunctions() throws Exception
    {
        String expressionText = "${http:randomInvalidVersion()}";
        ValueExpression expression = factory.createValueExpression(ctx, expressionText, String.class);
        String randomBytes = (String) expression.getValue(ctx);
        Assert.assertNotNull(randomBytes);
    }

    @Test
    public void headerTest()
    {
        byte[] build = headers().item("name", "value").build();
        DirectBuffer buffer = new UnsafeBuffer(build);
        ListFW<HttpHeaderFW> headers = new ListFW<HttpHeaderFW>(new HttpHeaderFW()).wrap(buffer, 0, buffer.capacity());
        headers.forEach(onlyHeader ->
        {
            Assert.assertEquals("name", onlyHeader.name().asString());
            Assert.assertEquals("value", onlyHeader.value().asString());
        });
        Assert.assertTrue(headers.sizeof() > 0);
    }

}
