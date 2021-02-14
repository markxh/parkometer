package za.co.dotmark.parkometer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

class ParkingMeterViewModel : ViewModel() {

    var remainingFee = MutableLiveData("50")
    var change = MutableLiveData("0")
    var enablePayment = MutableLiveData(true)

    fun pay(inputAmount: String) {
        val remaining = calculate(inputAmount.toBigDecimal(), remainingFee.value!!.toBigDecimal())

        if(remaining <= BigDecimal(0)) {
            remainingFee.value = "0"
            change.value = (remaining * BigDecimal(-1)).toString()
            enablePayment.value = false
        } else {
            remainingFee.value = remaining.toString()
            change.value = "0"
        }
    }

    private fun calculate(inputAmount: BigDecimal, parkingFee: BigDecimal) : BigDecimal {
        return parkingFee - inputAmount
    }

    fun reset() {
        remainingFee.value = "50"
        change.value = "0"
        enablePayment.value = true
    }
}