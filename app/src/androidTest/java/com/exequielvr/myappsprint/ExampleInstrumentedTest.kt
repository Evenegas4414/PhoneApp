package com.exequielvr.myappsprint

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.exequielvr.myappsprint.api.ItemsDatabase
import com.exequielvr.myappsprint.model.ItemsDao
import com.exequielvr.myappsprint.model.ItemsEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    // referencias
    private lateinit var daoTest : ItemsDao
    private lateinit var db: ItemsDatabase




    @Before
    fun setUp(){
        val context= ApplicationProvider.getApplicationContext<Context>()
        db= Room.inMemoryDatabaseBuilder(context, ItemsDatabase::class.java).build()
        daoTest= db.getItemsDao()

    }



    @After
    fun shutDown(){
        db.close()
    }
    @Test
    fun insertCoursesList()= runBlocking {

        val itemsEntity= listOf(

            ItemsEntity(1, "Prueba1",18,"url"),
            ItemsEntity(2, "Prueba2",17,"url"),

            )
        daoTest.insertAllItems(itemsEntity)
        val itemLiveData = daoTest.getAllItems()
        val itemList : List<ItemsEntity> = itemLiveData.value?: emptyList()


        MatcherAssert.assertThat(itemList, CoreMatchers.not(emptyList()))
        MatcherAssert.assertThat(itemList.size, CoreMatchers.equalTo(2))
    }
}