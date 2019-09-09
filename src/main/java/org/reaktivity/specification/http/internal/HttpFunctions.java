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

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.kaazing.k3po.lang.el.Function;
import org.kaazing.k3po.lang.el.spi.FunctionMapperSpi;
import org.reaktivity.specification.http.internal.types.control.HttpRouteExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpBeginExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpDataExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpEndExFW;
import org.reaktivity.specification.http.internal.types.stream.HttpChallengeExFW;

public final class HttpFunctions
{
    @Function
    public static HttpRouteExBuilder routeEx()
    {
        return new HttpRouteExBuilder();
    }

    @Function
    public static HttpBeginExBuilder beginEx()
    {
        return new HttpBeginExBuilder();
    }

    @Function
    public static HttpDataExBuilder dataEx()
    {
        return new HttpDataExBuilder();
    }

    @Function
    public static HttpEndExBuilder endEx()
    {
        return new HttpEndExBuilder();
    }

    @Function
    public static HttpChallengeExBuilder challengeEx()
    {
        return new HttpChallengeExBuilder();
    }

    @Function
    public static String randomInvalidVersion()
    {
        Random random = ThreadLocalRandom.current();
        String randomVersion = null;
        Pattern validVersionPattern = Pattern.compile("HTTP/1\\.(\\d)+");
        Matcher validVersionMatcher = null;
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                       "1234567890!@#$%^&*()_+-=`~[]\\{}|;':\",./<>?";
        StringBuilder result;
        do
        {
            result = new StringBuilder();
            int randomLength = random.nextInt(30) + 1;
            for (int i = 0; i < randomLength; i++)
            {
                result.append(chars.charAt(random.nextInt(chars.length())));
            }
            randomVersion = result.toString();
            validVersionMatcher = validVersionPattern.matcher(randomVersion);
        }
        while (randomVersion.length() > 1 && validVersionMatcher.matches());
        return randomVersion;
    }

    @Function
    public static byte[] randomAscii(
        int length)
    {
        Random random = ThreadLocalRandom.current();
        byte[] result = new byte[length];
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                          "1234567890!@#$%^&*()_+-=`~[]\\{}|;':\",./<>?";
        for (int i = 0; i < length; i++)
        {
            result[i] = (byte) alphabet.charAt(random.nextInt(alphabet.length()));
        }
        return result;
    }

    @Function
    public static byte[] randomBytes(
        int length)
    {
        Random random = ThreadLocalRandom.current();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++)
        {
            bytes[i] = (byte) random.nextInt(0x100);
        }
        return bytes;
    }

    @Function
    public static byte[] randomBytesUTF8(
        int length)
    {
        byte[] bytes = new byte[length];
        randomBytesUTF8(bytes, 0, length);
        return bytes;
    }

    public static final class HttpRouteExBuilder
    {
        private final HttpRouteExFW.Builder routeExRW;

        private HttpRouteExBuilder()
        {
            MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[1024 * 8]);
            this.routeExRW = new HttpRouteExFW.Builder().wrap(writeBuffer, 0, writeBuffer.capacity());
        }

        public HttpRouteExBuilder header(
            String name,
            String value)
        {
            routeExRW.headersItem(b -> b.name(name).value(value));
            return this;
        }

        public HttpRouteExBuilder override(
            String name,
            String value)
        {
            routeExRW.overridesItem(b -> b.name(name).value(value));
            return this;
        }

        public byte[] build()
        {
            final HttpRouteExFW routeEx = routeExRW.build();
            final byte[] array = new byte[routeEx.sizeof()];
            routeEx.buffer().getBytes(routeEx.offset(), array);
            return array;
        }
    }

    public static final class HttpBeginExBuilder
    {
        private final HttpBeginExFW.Builder beginExRW;

        private HttpBeginExBuilder()
        {
            MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[1024 * 8]);
            this.beginExRW = new HttpBeginExFW.Builder().wrap(writeBuffer, 0, writeBuffer.capacity());
        }

        public HttpBeginExBuilder typeId(
            int typeId)
        {
            beginExRW.typeId(typeId);
            return this;
        }

        public HttpBeginExBuilder header(
            String name,
            String value)
        {
            beginExRW.headersItem(b -> b.name(name).value(value));
            return this;
        }

        public byte[] build()
        {
            final HttpBeginExFW beginEx = beginExRW.build();
            final byte[] array = new byte[beginEx.sizeof()];
            beginEx.buffer().getBytes(beginEx.offset(), array);
            return array;
        }
    }

    public static final class HttpDataExBuilder
    {
        private final HttpDataExFW.Builder dataExRW;

        private HttpDataExBuilder()
        {
            MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[1024 * 8]);
            this.dataExRW = new HttpDataExFW.Builder().wrap(writeBuffer, 0, writeBuffer.capacity());
        }

        public HttpDataExBuilder typeId(
            int typeId)
        {
            dataExRW.typeId(typeId);
            return this;
        }

        public HttpDataExBuilder promise(
            String name,
            String value)
        {
            dataExRW.promiseItem(b -> b.name(name).value(value));
            return this;
        }

        public byte[] build()
        {
            final HttpDataExFW dataEx = dataExRW.build();
            final byte[] array = new byte[dataEx.sizeof()];
            dataEx.buffer().getBytes(dataEx.offset(), array);
            return array;
        }
    }

    public static final class HttpEndExBuilder
    {
        private final HttpEndExFW.Builder endExRW;

        private HttpEndExBuilder()
        {
            MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[1024 * 8]);
            this.endExRW = new HttpEndExFW.Builder().wrap(writeBuffer, 0, writeBuffer.capacity());
        }

        public HttpEndExBuilder typeId(
            int typeId)
        {
            endExRW.typeId(typeId);
            return this;
        }

        public HttpEndExBuilder trailer(
            String name,
            String value)
        {
            endExRW.trailersItem(b -> b.name(name).value(value));
            return this;
        }

        public byte[] build()
        {
            final HttpEndExFW endEx = endExRW.build();
            final byte[] array = new byte[endEx.sizeof()];
            endEx.buffer().getBytes(endEx.offset(), array);
            return array;
        }
    }

    public static final class HttpChallengeExBuilder
    {
        private final HttpChallengeExFW.Builder challengeExRW;

        private HttpChallengeExBuilder()
        {
            MutableDirectBuffer writeExBuffer = new UnsafeBuffer(new byte[1024 * 8]);
            this.challengeExRW = new HttpChallengeExFW.Builder().wrap(writeExBuffer, 0, writeExBuffer.capacity());
        }

        public HttpChallengeExBuilder typeId(
            int typeId)
        {
            challengeExRW.typeId(typeId);
            return this;
        }

        public HttpChallengeExBuilder header(
            String name,
            String value)
        {
            challengeExRW.headersItem(b -> b.name(name).value(value));
            return this;
        }

        public byte[] build()
        {
            final HttpChallengeExFW challengeEx = challengeExRW.build();
            final byte[] array = new byte[challengeEx.sizeof()];
            challengeEx.buffer().getBytes(challengeEx.offset(), array);
            return array;
        }
    }

    public static class Mapper extends FunctionMapperSpi.Reflective
    {
        public Mapper()
        {
            super(HttpFunctions.class);
        }

        @Override
        public String getPrefixName()
        {
            return "http";
        }
    }

    private static void randomBytesUTF8(
        byte[] bytes,
        int start,
        int end)
    {
        Random random = ThreadLocalRandom.current();
        for (int offset = start; offset < end;)
        {
            int remaining = end - offset;
            int width = Math.min(random.nextInt(4) + 1, remaining);

            offset = randomCharBytesUTF8(bytes, offset, width);
        }
    }

    private static int randomCharBytesUTF8(
        byte[] bytes,
        int offset,
        int width)
    {
        Random random = ThreadLocalRandom.current();
        switch (width)
        {
        case 1:
            bytes[offset++] = (byte) random.nextInt(0x80);
            break;
        case 2:
            bytes[offset++] = (byte) (0xc0 | random.nextInt(0x20) | 1 << (random.nextInt(4) + 1));
            bytes[offset++] = (byte) (0x80 | random.nextInt(0x40));
            break;
        case 3:
            // UTF-8 not legal for 0xD800 through 0xDFFF (see RFC 3269)
            bytes[offset++] = (byte) (0xe0 | random.nextInt(0x08) | 1 << random.nextInt(3));
            bytes[offset++] = (byte) (0x80 | random.nextInt(0x40));
            bytes[offset++] = (byte) (0x80 | random.nextInt(0x40));
            break;
        case 4:
            // UTF-8 ends at 0x10FFFF (see RFC 3269)
            bytes[offset++] = (byte) (0xf0 | random.nextInt(0x04) | 1 << random.nextInt(2));
            bytes[offset++] = (byte) (0x80 | random.nextInt(0x10));
            bytes[offset++] = (byte) (0x80 | random.nextInt(0x40));
            bytes[offset++] = (byte) (0x80 | random.nextInt(0x40));
            break;
        }
        return offset;
    }

    private HttpFunctions()
    {
        // utility
    }
}
