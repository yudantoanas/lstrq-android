package id.listrique

import android.util.Log
import id.listrique.util.PLNPowerList
import id.listrique.util.value
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AddDeviceUnitTest {
    val testPower1 = CalculatePower(
        plnPower = PLNPowerList[0],
        deviceList = listOf(
            Device(
                name = "lampu",
                quantity = 7,
                powerUsed = 30,
                hourUsed = 12
            ),
            Device(
                name = "ac",
                quantity = 1,
                powerUsed = 750,
                hourUsed = 8
            ),
            Device(
                name = "tv",
                quantity = 1,
                powerUsed = 50,
                hourUsed = 5
            ),
            Device(
                name = "sound",
                quantity = 1,
                powerUsed = 10,
                hourUsed = 2
            ),
            Device(
                name = "setrika",
                quantity = 1,
                powerUsed = 350,
                hourUsed = 2
            ),
            Device(
                name = "kulkas",
                quantity = 1,
                powerUsed = 300,
                hourUsed = 24
            ),
            Device(
                name = "mesin",
                quantity = 1,
                powerUsed = 350,
                hourUsed = 2
            )
        )
    )

    @Test
    fun calculateDailyCost() {
        var totalPowerUsed = 0
        testPower1.deviceList?.forEach {
            totalPowerUsed += (it.powerUsed.value() * it.hourUsed.value() * it.quantity.value())
        }

        val powerInKwh = totalPowerUsed.div(1000.0)
        val powerConsumptionCost = 1467.26 * powerInKwh

        assertEquals(25515.65, powerConsumptionCost, 0.1)
    }
}