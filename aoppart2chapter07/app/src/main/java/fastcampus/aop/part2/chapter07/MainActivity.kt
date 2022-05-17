package fastcampus.aop.part2.chapter07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val recordButton:RecordButton by lazy {
        findViewById(R.id.recorButton)
    }

    private var stare = State.BEFORE_RECORDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView(){
        recordButton.updateIconWithState(state)
    }

}