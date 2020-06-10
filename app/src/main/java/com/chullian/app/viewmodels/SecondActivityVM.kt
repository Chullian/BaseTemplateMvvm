package com.chullian.app.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chullian.app.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondActivityVM @Inject constructor(
    private val repo: Repository
) : ViewModel() {


    fun updateUnlockStatus(itemId: Int) {
        viewModelScope.launch {
            repo.updateLockStatus(itemId)
        }
    }

}
