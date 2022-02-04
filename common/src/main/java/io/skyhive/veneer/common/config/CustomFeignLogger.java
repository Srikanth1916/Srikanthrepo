package io.skyhive.veneer.common.config;

import feign.Request;
import feign.Response;
import feign.Util;
import feign.slf4j.Slf4jLogger;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Custom feign logger.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
public class CustomFeignLogger extends Slf4jLogger {
    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        FeignRequest feignRequest = new FeignRequest();
        feignRequest.method = request.httpMethod().name();
        feignRequest.url = request.url();

        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
            for (String field : request.headers().keySet()) {
                for (String value : Util.valuesOrEmpty(request.headers(), field)) {
                    feignRequest.addHeader(field, value);
                }
            }

            if (request.body() != null && logLevel.ordinal() >= Level.FULL.ordinal()) {
                feignRequest.body = (request.charset() != null ? new String(request.body(), request.charset())
                        : "Binary data");
            }
        }
        System.out.println(String.format("%s == >> Request : %s ", configKey, feignRequest));
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
            throws IOException {
        FeignResponse feignResponse = new FeignResponse();
        int status = response.status();
        feignResponse.status = response.status();
        feignResponse.reason = (response.reason() != null && logLevel.compareTo(Level.NONE) > 0
                ? " " + response.reason()
                : "");
        feignResponse.timeTaken = elapsedTime;

        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
            for (String field : response.headers().keySet()) {
                for (String value : Util.valuesOrEmpty(response.headers(), field)) {
                    feignResponse.addHeader(field, value);
                }
            }

            if (response.body() != null && (HttpStatus.Series.valueOf(status) == HttpStatus.Series.CLIENT_ERROR
                    || HttpStatus.Series.valueOf(status) == HttpStatus.Series.SERVER_ERROR)) {
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyData.length > 0) {
                    feignResponse.body = Util.decodeOrDefault(bodyData, StandardCharsets.UTF_8, "Binary data");
                }
                System.out.println(String.format("%s == >> Response : %s ", configKey, feignResponse));
                return response.toBuilder().body(bodyData).build();
            } else {
                System.out.println(String.format("%s == >> Response : %s ", configKey, feignResponse));
            }
        }
        return response;
    }

    @Setter
    private class FeignResponse {
        private int status;
        private String reason;
        private long timeTaken;
        private List<String> headers;
        private String body;

        /**
         * Add header.
         *
         * @param key   the key
         * @param value the value
         */
        public void addHeader(String key, String value) {
            if (headers == null) {
                headers = new ArrayList<>();
            }
            headers.add(String.format("%s: %s", key, value));
        }

        @Override
        public String toString() {
            return String.format(
                    "Status = %s, Reason = %s, TimeTaken = %s, Headers = %s Body = %s, BodyLength = %s Bytes", status,
                    reason, timeTaken, headers, body, (body != null && body.trim().length() > 0 ? body.length() : 0));
        }
    }

    @Setter
    private class FeignRequest {
        private String method;
        private String url;
        private List<String> headers;
        private String body;

        /**
         * Add header.
         *
         * @param key   the key
         * @param value the value
         */
        public void addHeader(String key, String value) {
            if (headers == null) {
                headers = new ArrayList<>();
            }
            headers.add(String.format("%s: %s", key, value));
        }

        @Override
        public String toString() {
            return String.format("Method = %s, url = %s, Headers = %s Body = %s, BodyLength = %s Bytes", method, url,
                    headers, body, (body != null && body.trim().length() > 0 ? body.length() : 0));
        }
    }
}