package android.ptc.com.ptcflixing

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException
import kotlin.jvm.Throws

class SplashScreenActivity : AppCompatActivity() {
    var _intent : Intent = Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _intent = Intent(this, MainActivity::class.java)
        getConfigurations()
    }

    @Throws(IOException::class)
    fun getConfigurations() {
        val httpUrl = HttpUrl.Builder()
                .scheme("https")
                .host("nd7d1.mocklab.io")
                .addPathSegment("configurations")
                .addPathSegment("")
                .build()
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(httpUrl)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
            }
            @Throws(IOException::class)
            override  fun  onResponse(
                    call: Call,
                    response: Response
            ) {
                val myResponse = response.body!!.string()
//                var x = Configurations.fromJson(myResponse)
//                var sharedPref: SharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
//                val editor = sharedPref.edit()
//                editor.
//                editor.apply()
                val sharedPref = getSharedPreferences(packageName, Context.MODE_PRIVATE) ?: return
                with (sharedPref.edit()) {
                    putString(packageName + "_configurations", myResponse)
                    apply()
                }
                startActivity(_intent)
            }
        })
    }
}