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
package org.reaktivity.specification.http.internal;

import static org.kaazing.k3po.lang.internal.el.ExpressionFactoryUtils.newExpressionFactory;
import static org.reaktivity.specification.http.internal.Functions.header;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.agrona.DirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kaazing.k3po.lang.internal.el.ExpressionContext;
import org.reaktivity.nukleus.http.internal.types.HttpHeaderFW;

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
        System.out.println(randomBytes);
    }

    @Test
    public void headerTest()
    {
        byte[] expected = new byte[] { (byte) 0x00, (byte) "name".length(), (byte) 'n', (byte) 'a', (byte) 'm', (byte) 'e',
                (byte) "value".length(), (byte) 'v', (byte) 'a', (byte) 'l', (byte) 'u', (byte) 'e' };
        byte[] actual = header("name", "value");
        DirectBuffer header = new UnsafeBuffer(actual);
        HttpHeaderFW fw = new HttpHeaderFW().wrap(header, 0, actual.length);
        Assert.assertEquals(fw.representation(), 0x00);
        Assert.assertEquals(fw.name().asString(), "name");
        Assert.assertEquals(fw.value().asString(), "value");

        Assert.assertArrayEquals(expected, actual);

        byte[] expected2 = new byte[] { (byte) 0x00, (byte) ":status".length(), (byte) ':', (byte) 's', (byte) 't', (byte) 'a',
                (byte) 't', (byte) 'u', (byte) 's', (byte) "200".length(), (byte) '2', (byte) '0', (byte) '0' };
        byte[] actual2 = header(":status", "200");
        DirectBuffer header2 = new UnsafeBuffer(actual2);
        HttpHeaderFW fw2 = new HttpHeaderFW().wrap(header2, 0, actual2.length);
        Assert.assertEquals(fw2.representation(), 0x00);
        Assert.assertEquals(fw2.name().asString(), ":status");
        Assert.assertEquals(fw2.value().asString(), "200");

        Assert.assertArrayEquals(expected2, actual2);
    }

}
