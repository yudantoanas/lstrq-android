package id.listrique

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import id.listrique.util.BUNDLE
import id.listrique.util.toCurrencyString
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        MobileAds.initialize(this)
        adView.run {
            loadAd(AdRequest.Builder().build())
        }

        with(intent.getBundleExtra(BUNDLE)?.getParcelable(POWER) ?: CalculatePower()) {
            totalDevices.text = "${deviceList?.size} perangkat"
            powerConsumed.text = "${intent.getDoubleExtra(TOTAL_POWER, 0.0)} kWh"
            powerConsumptionLimit.text = plnPower?.name
            pricePerUnit.text = "${plnPower?.price.toCurrencyString()}/kWh"
            priceUpdate.text = "per Oktober 2020"

            val cost = intent.getDoubleExtra(TOTAL_DAILY, 0.0)
            dailyCost.text = cost.toCurrencyString()
            monthlyCost.text = (cost * 30).toCurrencyString()
        }


        btnRecalculate.setOnClickListener { HomeActivity.startActivityWithClearTask(this) }
    }

    companion object {
        private const val POWER = "POWER"
        const val TOTAL_DAILY = "TOTAL_DAILY"
        const val TOTAL_POWER = "TOTAL_POWER"
        fun startActivity(
            context: Context,
            power: CalculatePower? = null,
            powerConsumed: Double,
            dailyCost: Double
        ) {
            context.startActivity(
                Intent(context, ResultActivity::class.java).apply {
                    putExtra(BUNDLE, Bundle().apply {
                        putParcelable(POWER, power)
                    })
                    putExtra(TOTAL_DAILY, dailyCost)
                    putExtra(TOTAL_POWER, powerConsumed)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            )
        }
    }
}