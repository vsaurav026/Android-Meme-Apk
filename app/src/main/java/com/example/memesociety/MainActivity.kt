package com.example.memesociety



import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


var currenturl: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmeme()
    }



    private fun loadmeme(){

        pbar.visibility = View.VISIBLE


// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                // Display the first 500 characters of the response string.
               currenturl = response.getString("url")
                //Picasso.get().load(url).into(imagev)



                Glide.with(this).load(currenturl).listener(object : RequestListener<Drawable>{


                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {

                        pbar.visibility= View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbar.visibility = View.GONE
                        return false
                                        }
                }).into(newid2)




            },
            {
                Toast.makeText(this , "error" , Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)






    }



    fun memeshare(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hey , checkout meme society meme $currenturl")
        val chooser = Intent.createChooser(intent , "Share this meme....")
        startActivity(intent)







    }
    fun memenext(view: View) {

        loadmeme()
    }
}
