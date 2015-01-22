/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.glowroot.local.ui;

import javax.annotation.Nullable;

import com.google.common.net.MediaType;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpResponse;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

class ConditionalHttpContentCompressor extends HttpContentCompressor {

    @Override
    protected @Nullable Result beginEncode(HttpResponse response, String acceptEncoding)
            throws Exception {
        String contentType = response.headers().get(CONTENT_TYPE);
        if (contentType != null && contentType.equals(MediaType.ZIP.toString())) {
            // don't compress already zipped content
            return null;
        }
        return super.beginEncode(response, acceptEncoding);
    }
}
