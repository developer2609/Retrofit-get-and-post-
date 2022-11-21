package com.example.retrofit_get

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_get.databinding.ActivityMainBinding
import com.example.retrofit_get.databinding.ItemDialogBinding
import com.example.retrofit_get.models.MyToDo
import com.example.retrofit_get.models.MyToDpPostRequest
import com.example.retrofit_get.models.RvAdapter
import com.example.retrofit_get.retrofit.ApiClient
import com.example.retrofit_get.utils.Status
import com.example.retrofit_get.viewmodels.ToDoViewmodels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toDoViewmodels: ToDoViewmodels
    private lateinit var rvAdapter: RvAdapter
    private  var Tag="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         getDAta()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        postDAta()
        return super.onOptionsItemSelected(item)
    }
    private fun postDAta() {
         val alertDialog=AlertDialog.Builder(this).create()
        val itemDialogBinding=ItemDialogBinding.inflate(layoutInflater)
        alertDialog.setView(itemDialogBinding.root)

        itemDialogBinding.apply {
            btnSave.setOnClickListener {
                val myToDpPostRequest=MyToDpPostRequest(
                    spinnerXolat.selectedItem.toString(),
                    edtMuddat.text.toString(),
                    edtMatn.text.toString(),
                    edtTitle.text.toString(),




                )
                ApiClient.getApiServise().addToDo(myToDpPostRequest)
                    .enqueue(object :Callback<MyToDo>{
                        override fun onResponse(call: Call<MyToDo>, response: Response<MyToDo>) {
                           if (response.isSuccessful){
                               Toast.makeText(this@MainActivity, "${response.body()?.sarlavha} saqlandi", Toast.LENGTH_SHORT).show()
                               alertDialog.cancel()
                               getDAta()


                           }
                        }

                        override fun onFailure(call: Call<MyToDo>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "Saqlanmadi", Toast.LENGTH_SHORT).show()

                        }
                    })

            }
        }

        alertDialog.show()

    }

    private fun getDAta() {
        toDoViewmodels=ViewModelProvider(this).get(ToDoViewmodels::class.java)
        rvAdapter= RvAdapter()
        binding.rv.adapter=rvAdapter

        toDoViewmodels.getAllTodo()
            .observe(this){
                when(it.status){
                    Status.Loading->{
                        Log.d(TAG, "onCreate: loading")
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    Status.Error->{
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        binding.progressBar.visibility=View.GONE
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

                    }
                    Status.Succesful->{
                        Log.d(TAG, "onCreate: ${it.date}")
                        rvAdapter.list=it.date!!
                        rvAdapter.notifyDataSetChanged()
                        binding.progressBar.visibility=View.GONE
                    }

                }
            }

    }
}