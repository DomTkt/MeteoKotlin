package com.example.a727222.weatherapp

import android.content.Context
import android.util.Log
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URI
import java.util.*

class FakeInterceptorK(var context : Context) : Interceptor{
    private val TAG = FakeInterceptor::class.java.simpleName
    private val FILE_EXTENSION = ".json"
    private lateinit var mContext: Context

    private var mContentType = "application/json"

    /**
     * Set content type for header
     * @param contentType Content type
     * @return FakeInterceptor
     */

    init{
        this.context = context
    }

    fun setContentType(contentType: String): FakeInterceptorK {
        mContentType = contentType
        return this
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val listSuggestionFileName = ArrayList<String>()
        val method = chain.request().method().toLowerCase()

        var response: Response? = null
        // Get Request URI.
        val uri = chain.request().url().uri()
        Log.d(TAG, "--> Request url: [" + method.toUpperCase() + "]" + uri.toString())

        val defaultFileName = getFileName(chain)

        //create file name with http method
        //eg: getLogin.json
        listSuggestionFileName.add(method + upCaseFirstLetter(defaultFileName))

        //eg: login.json
        listSuggestionFileName.add(defaultFileName)

        val responseFileName = getFirstFileNameExist(listSuggestionFileName, uri)
        if (responseFileName != null) {
            val fileName = getFilePath(uri, responseFileName)
            Log.d(TAG, "Read data from file: $fileName")
            try {
                val `is` = mContext.assets.open(fileName)
                val r = BufferedReader(InputStreamReader(`is`))
                val responseStringBuilder = StringBuilder()
//                var line: String =
//                        while (r.readLine() != null) {
//                            responseStringBuilder.append(line).append('\n')
//                        }
                Log.d(TAG, "Response: " + responseStringBuilder.toString())
                response = Response.Builder()
                        .code(200)
                        .message(responseStringBuilder.toString())
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_0)
                        .body(ResponseBody.create(MediaType.parse(mContentType), responseStringBuilder.toString().toByteArray()))
                        .addHeader("content-type", mContentType)
                        .build()
            } catch (e: IOException) {
                Log.e(TAG, e.message, e)
            }

        } else {
            for (file in listSuggestionFileName) {
                Log.e(TAG, "File not exist: " + getFilePath(uri, file))
            }
            response = chain.proceed(chain.request())
        }

        Log.d(TAG, "<-- END [" + method.toUpperCase() + "]" + uri.toString())
        return response
    }

    private fun upCaseFirstLetter(str: String): String {
        return str.substring(0, 1).toUpperCase() + str.substring(1)
    }

    @Throws(IOException::class)
    private fun getFirstFileNameExist(inputFileNames: List<String>, uri: URI): String? {
        var mockDataPath = uri.host + uri.path
        mockDataPath = mockDataPath.substring(0, mockDataPath.lastIndexOf('/'))
        Log.d(TAG, "Scan files in: $mockDataPath")
        //List all files in folder
        val files = mContext.assets.list(mockDataPath)
        for (fileName in inputFileNames) {
            if (fileName != null) {
                for (file in files!!) {
                    if (fileName == file) {
                        return fileName
                    }
                }
            }
        }
        return null
    }

    private fun getFileName(chain: Interceptor.Chain): String {
        val fileName = chain.request().url().pathSegments()[chain.request().url().pathSegments().size - 1]
        return if (fileName.isEmpty()) "index$FILE_EXTENSION" else fileName + FILE_EXTENSION
    }

    private fun getFilePath(uri: URI, fileName: String): String {
        val path: String
        if (uri.path.lastIndexOf('/') != uri.path.length - 1) {
            path = uri.path.substring(0, uri.path.lastIndexOf('/') + 1)
        } else {
            path = uri.path
        }
        return uri.host + path + fileName
    }
}