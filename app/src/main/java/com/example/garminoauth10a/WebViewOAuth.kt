package com.example.garminoauth10a

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.OAuth1AccessToken
import com.github.scribejava.core.model.OAuth1RequestToken
import com.github.scribejava.core.oauth.OAuth10aService
import kotlinx.android.synthetic.main.activity_web_view_oauth.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.util.concurrent.ExecutionException

class WebViewOAuth : AppCompatActivity() {

    private val CLIENT_KEY = "9dcf7514-0d83-428d-986f-b8d6c4193ca3"
    private val CLIENT_SECRET_KEY = "SoGDpv5nwE9RRDxC8dVP9s3zR1hCf5SkdFn"
    private val CALLBACK_URL = "https://staging.rocketjourney.com/api/v2/garmin"


    var verifier: String? = null
    var requestToken: OAuth1RequestToken? = null
    var service: OAuth10aService? = null
    var accessToken: OAuth1AccessToken? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_oauth)

        webViewGarmin.clearCache(true)
        webViewGarmin.settings.javaScriptEnabled = true
        webViewGarmin.settings.builtInZoomControls = true
        webViewGarmin.settings.displayZoomControls = false

        service = ServiceBuilder(CLIENT_KEY)
                .apiSecret(CLIENT_SECRET_KEY)
                .callback(CALLBACK_URL)
                .debug()
                .build(GarminApi.instance())

        doAsync {
            try {
                requestToken = service?.requestToken
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }

            val url = service?.getAuthorizationUrl(requestToken)

            uiThread { loadURL(url) }
        }
    }

    fun loadURL(url: String?) {

        webViewGarmin.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                val uri = Uri.parse(url)
                if (url.contains("oauth_verifier")) {
                    webViewGarmin.visibility = View.GONE
                    Log.d("Log.d", url)
                    verifier = uri.getQueryParameter("oauth_verifier")
                    Toast.makeText(applicationContext, verifier, Toast.LENGTH_SHORT).show()
                    getAccessToken()
                }

                return false

            }
        }

        webViewGarmin.loadUrl(url)
    }

    private fun getAccessToken() {

        doAsync {

            try {
                accessToken = service?.getAccessToken(requestToken, verifier)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }

            uiThread {
                Toast.makeText(applicationContext, "Token = " + accessToken?.token + "Secret = " + accessToken?.tokenSecret, Toast.LENGTH_LONG).show()
            }
        }
    }
}
