package com.chullian.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chullian.app.DefaultPreference
import com.chullian.app.Repository
import com.chullian.app.model.Item
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityVM @Inject constructor(
    private var repo: Repository,
    private var defaultPreference: DefaultPreference
) : ViewModel() {

    private var itemsMLD = MutableLiveData<List<Item>>()
    var itemLD: LiveData<List<Item>> = itemsMLD


    @InternalCoroutinesApi
    fun insertItems() {
        viewModelScope.launch {
            if (!defaultPreference.areItemsLoaded) {
                var items = listOf<Item>(
                    Item(0, "Samsung", false),
                    Item(1, "Apple", true),
                    Item(2, "Huawei", true),
                    Item(3, "Oppo", true),
                    Item(4, "Xaiomi", true)
                )
                repo.insertItems(items)
                defaultPreference.areItemsLoaded = true
            }
            getAllItems()
        }
    }

    @InternalCoroutinesApi
    fun getAllItems() {
        viewModelScope.launch {
            repo.getAllItems { result ->
                itemsMLD.postValue(result)
            }
        }
    }
}