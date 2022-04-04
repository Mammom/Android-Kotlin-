package fastcampus.aop.part2.aop.part2.chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val clearButton : Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton : Button by lazy {
        findViewById(R.id.addButton)
    }

    private val runButton : Button by lazy {
        findViewById(R.id.runButton)
    }

    private val numberPicker:NumberPicker by lazy {
        findViewById(R.id.numberPicker)
    }

    private var didRun =false

    private val pickNumberSet = hashSetOf<Int>()

    private val numberTextView : List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.NumberTextView1),
            findViewById<TextView>(R.id.NumberTextView2),
            findViewById<TextView>(R.id.NumberTextView3),
            findViewById<TextView>(R.id.NumberTextView4),
            findViewById<TextView>(R.id.NumberTextView5),
            findViewById<TextView>(R.id.NumberTextView6)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun initClearButton(){
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextView.forEach{
                it.isVisible = false
            }


            didRun = false
        }
    }

    private fun initRunButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()

            didRun =true
            list.forEachIndexed {index, number ->
                val textView = numberTextView[index]

                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number,textView)
            }

        }
    }

    private fun initAddButton(){
        addButton.setOnClickListener {
            if (didRun){
                Toast.makeText(this, "초기화 후에 시도해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pickNumberSet.size>=6){
                Toast.makeText(this, "번호는 6개까지만 선택할수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this, "이미 선택한 번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val textView =numberTextView[pickNumberSet.size]
            textView.isVisible =true
            textView.text = numberPicker.value.toString()


            setNumberBackground(numberPicker.value,textView)

            pickNumberSet.add(numberPicker.value)
        }
    }

    private fun getRandomNumber():List<Int>{
        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45){
                    if(pickNumberSet.contains(i)){
                        continue
                    }
                    this.add(i)
                }
            }
        numberList.shuffle()
        val newList = pickNumberSet.toList() + numberList.subList(0,6 - pickNumberSet.size)
        return newList.sorted()
    }

    private fun setNumberBackground(number:Int,textView: TextView){
        when(number){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_y)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this,R.drawable.cicle_b)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_r)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_g)
            else -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_green)
        }
    }

}