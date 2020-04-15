package com.jayden.client.config

import com.jayden.client.common.Log
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

class LoggingRequestInterceptor : ClientHttpRequestInterceptor {

    companion object : Log

    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        traceRequest(request, body)
        val response = execution.execute(request, body)
        traceResponse(response)
        return response
    }

    private fun traceRequest(request: HttpRequest, body: ByteArray) {
        log.info("===========================[REQUEST]====================================");
        log.info("URI         : {}", request.uri)
        log.info("Method      : {}", request.method)
        log.info("Headers     : {}", request.headers)
        log.info("Request body: {}", String(body, Charset.forName("UTF-8")))
    }

    private fun traceResponse(response: ClientHttpResponse) {
        val inputStringBuilder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(response.body, "UTF-8"));
        var line = bufferedReader.readLine()
        while (line != null) {
            inputStringBuilder.append(line)
            inputStringBuilder.append('\n')
            line = bufferedReader.readLine()
        }
        log.info("===========================[RESPONSE]====================================");
        log.info("Status code  : {}", response.statusCode)
        log.info("Status text  : {}", response.statusText)
        log.info("Headers      : {}", response.headers)
        log.info("Response body: {}", inputStringBuilder.toString())
    }
}