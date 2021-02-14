package za.co.dotmark.parkometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import za.co.dotmark.parkometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ParkingMeterViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ParkingMeterViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.reset()

        viewModel.enablePayment.observe(this, Observer {
            btnPay.visibility = if(it) View.VISIBLE else View.GONE
        })

        btnPay.setOnClickListener { viewModel.pay(etPay.text.toString()) }
        btnReset.setOnClickListener { viewModel.reset() }
    }
}