package com.example.numanvaccine.presentation.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {
    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                //endpoint_success.json
                "characters" -> MockResponse().setResponseCode(200).setBody(getJsonContent("endpoint_success.json"))
                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
        }
    }


    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }
}