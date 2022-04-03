package fastcampus.aop.part2.chapter01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEditText : EditText = findViewById(R.id.heightEditText)
        val weightEditText = findViewById<EditText>(R.id.weightEditText)
        val resultButton = findViewById<Button>(R.id.resultButton)

        resultButton.setOnClickListener {
            Log.d("MainActivity","click")

            if(heightEditText.text.isEmpty() || weightEditText.text.isEmpty()){
                Toast.makeText(this,"값이 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 아래로 빈값이 올수없다

            val height : Int = heightEditText.text.toString().toInt()
            val weight : Int = weightEditText.text.toString().toInt()

            val intent = Intent(this,ResultActivity::class.java)

            intent.putExtra("height",height)
            intent.putExtra("weight",weight)

            startActivity(intent)
        }
    }
}