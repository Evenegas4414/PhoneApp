package com.exequielvr.myappsprint.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.exequielvr.myappsprint.api.ItemsDatabase
import com.exequielvr.myappsprint.model.ItemsDetailEntity
import com.exequielvr.myappsprint.model.ItemsEntity
import com.exequielvr.myappsprint.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ItemRepository
    private val itemDetailLiveData = MutableLiveData<ItemsDetailEntity>()

    init {
        val bd = ItemsDatabase.getDataBase(application)
        val itemsDao = bd.getItemsDao()
        repository = ItemRepository(itemsDao)
        viewModelScope.launch {

            repository.fechItems()
        }
    }

    fun getItemList(): LiveData<List<ItemsEntity>> = repository.itemsListLiveData

    fun getItemDetail(): LiveData<ItemsDetailEntity> = itemDetailLiveData

    fun getItemDetailByIDFromInternet(id: String) = viewModelScope.launch {
        val itemDetail = repository.fetchItemDetail(id)
        itemDetail?.let {
            itemDetailLiveData.postValue(it)
        }
    }
}