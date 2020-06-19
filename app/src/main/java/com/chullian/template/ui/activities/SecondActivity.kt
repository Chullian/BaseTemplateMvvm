package com.chullian.template.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chullian.template.databinding.ActivitySecondBinding
import com.chullian.template.tools.App
import com.chullian.template.tools.extensions.toast
import com.chullian.template.ui.viewmodels.SecondActivityVM
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SecondActivityVM

    private var _binding: ActivitySecondBinding? = null
    private val binding get() = _binding!!

    private var itemId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.include.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        itemId = intent.getIntExtra("itemId", 0)
        binding.Confirm.setOnClickListener {
            if (binding.checkBox.isChecked) {
                viewModel.updateUnlockStatus(itemId + 1)
                finish()
            } else {
                toast("Please confirm with the checkBox")
            }
        }
    }

} 