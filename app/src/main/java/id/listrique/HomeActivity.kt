package id.listrique

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import id.listrique.util.PLNPowerList
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val items = PLNPowerList.map { it.name }
        val adapter = ArrayAdapter(this, R.layout.list_item_power, items)
        powerField.setAdapter(adapter)

        btnNext.setOnClickListener {
            val power = PLNPowerList.find { it.name == powerField.text.toString() }
            AddDeviceActivity.startActivity(this, CalculatePower(power))
        }
    }

    companion object {
        fun startActivityWithClearTask(context: Context) {
            context.startActivity(
                Intent(context, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            )
        }
    }
}