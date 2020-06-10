package com.chullian.app.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.chullian.app.App
import com.chullian.app.adapters.ItemRecyclerAdapter
import com.chullian.app.databinding.ActivityMainBinding
import com.chullian.app.model.Item
import com.chullian.app.viewmodels.MainActivityVM
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ItemRecyclerAdapter.ItemInteraction {

    @Inject
    lateinit var viewModel: MainActivityVM

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    var itemsList = mutableListOf<Item>()
    var adapter = ItemRecyclerAdapter(this)


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.include.toolbar)
        viewModel.insertItems()
        subscribeToObservers()
        binding.itemRecycler.adapter = adapter
        adapter.submitList(itemsList)
    }

    private fun subscribeToObservers() {

        viewModel.itemLD.observe(this){items->
            itemsList.clear()
            itemsList.addAll(items)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemSelected(position: Int, item: Item) {
        if(!item.isLocked) {
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("itemId", item.itemId)
            startActivity(intent)
        }
    }
}