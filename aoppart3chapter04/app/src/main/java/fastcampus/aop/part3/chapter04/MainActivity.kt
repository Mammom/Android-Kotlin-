package fastcampus.aop.part3.chapter04

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import fastcampus.aop.part3.chapter04.adapter.BookAdapter
import fastcampus.aop.part3.chapter04.api.BookService
import fastcampus.aop.part3.chapter04.databinding.ActivityMainBinding
import fastcampus.aop.part3.chapter04.model.BestSellerDto
import fastcampus.aop.part3.chapter04.model.History
import fastcampus.aop.part3.chapter04.model.SearchBookDto
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter

    private lateinit var historyAdapter: HistoryAdapter

    private lateinit var bookService: BookService

    private lateinit var db:AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "BookSearchDB"
        ).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks(getString(R.string.interparkAPIKey)) //api키
            .enqueue(object : Callback<BestSellerDto> {

                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) {
                    // 성공

                    saveSearchKeyWord(keyword)

                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "NOT!! success")
                        return
                    }
                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    Log.e(TAG, t.toString())
                }

            })

        binding.searchEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun search(keyword: String) {
        bookService.getBooksByName(getString(R.string.interparkAPIKey), keyword)
            .enqueue(object : Callback<SearchBookDto> {

                override fun onResponse(
                    call: Call<SearchBookDto>,
                    response: Response<SearchBookDto>
                ) {
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "NOT!! success")
                        return
                    }
                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                    Log.e(TAG, t.toString())
                }

            })

    }


    private fun initBookRecyclerView() {
        adapter = BookAdapter()

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    private fun showHistoryView(){
        Thread{
            val keywords = db.historyDao().getAll().reversed()
        }

        binding.historyRecyclerView.isVisible = true
    }

    private fun hideHistoryView(){
        binding.historyRecyclerView.isVisible = false
    }

    private fun saveSearchKeyWord(keyword: String){
        Thread{
            db.historyDao().insertHistory(History(null,keyword))
        }.start()
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}