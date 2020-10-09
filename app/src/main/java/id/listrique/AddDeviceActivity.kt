package id.listrique

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import id.listrique.util.BUNDLE
import id.listrique.util.resetTextField
import id.listrique.util.value
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function4
import kotlinx.android.synthetic.main.activity_add_device.*

class AddDeviceActivity(var power: CalculatePower? = null) : AppCompatActivity() {
    lateinit var disposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        power = intent.getBundleExtra(BUNDLE)?.getParcelable(POWER)
        disposable = CompositeDisposable()

        btnAdd.setOnClickListener {
            addDevice()

            Toast.makeText(this, "Berhasil Menambahkan Perangkat", Toast.LENGTH_SHORT).show()
            startActivity(this)
        }
        btnNext.setOnClickListener {
            addDevice()

            val dailyConsumption = power?.calculateDailyConsumption()
            val dailyCost = dailyConsumption.value() * power?.plnPower?.price.value()
            ResultActivity.startActivity(this, power, dailyConsumption.value(), dailyCost.value())
        }

        val emptyFieldStream: Observable<Boolean> = Observable.combineLatest(
            deviceName.textChanges().map { it.isNotEmpty() },
            hourUsed.textChanges().map { it.isNotEmpty() },
            powerConsumed.textChanges().map { it.isNotEmpty() },
            totalDevices.textChanges().map { it.isNotEmpty() },
            Function4 { t1, t2, t3, t4 ->
                return@Function4 t1 && t2 && t3 && t4
            }
        )

        disposable.add(emptyFieldStream.subscribe { isAllFieldValid ->
            btnAdd.isEnabled = isAllFieldValid
            btnNext.isEnabled = !power?.deviceList.isNullOrEmpty() || isAllFieldValid
        })
    }

    private fun addDevice() {
        power?.run {
            val list = ArrayList<Device>()
            // retrieve existing list
            list.addAll(deviceList ?: emptyList())
            // add new one
            list.add(
                Device(
                    name = deviceName.text.toString(),
                    hourUsed = hourUsed.text.toString().toInt(),
                    powerUsed = powerConsumed.text.toString().toInt(),
                    quantity = totalDevices.text.toString().toInt()
                )
            )

            // update device list
            deviceList = list
        }
    }

    private fun CalculatePower.calculateDailyConsumption(): Double {
        var totalPowerUsed: Int = 0
        deviceList?.forEach {
            totalPowerUsed += (it.powerUsed.value() * it.hourUsed.value() * it.quantity.value())
        }

        return totalPowerUsed.div(1000.0)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        deviceName.resetTextField()
        hourUsed.resetTextField()
        powerConsumed.resetTextField()
        totalDevices.resetTextField()
    }

    companion object {
        private const val POWER = "POWER"
        fun startActivity(context: Context, power: CalculatePower? = null) {
            context.startActivity(
                Intent(context, AddDeviceActivity::class.java).apply {
                    putExtra(BUNDLE, Bundle().apply {
                        putParcelable(POWER, power)
                    })
                }
            )
        }
    }
}