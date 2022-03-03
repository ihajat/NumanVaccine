package com.example.numanvaccine.data.room.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.numanvaccine.data.room.mappers.CacheMapper
import com.example.numanvaccine.domain.model.Country
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryDatabaseTest : TestCase() {
    private lateinit var db: CountryDatabase
    private lateinit var dao: CountryDao
    private val cacheMapper = CacheMapper()

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase::class.java).build()
        dao = db.countryDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    fun writeAndReadCountry() = runBlocking {
        val country = Country(
            0,
            1,
            1,
            1,
            "United Kingdom",
            1,
            1,
            "",
            "na",
            "Europe",
            "GB",
            "na",
            0,
            "London",
            "na",
            0
        )
        dao.insert(cacheMapper.mapToData(country))
        val countrys = dao.getAll()

        assertEquals(countrys.size, 1)
    }

    // here we are first adding an item to the db and then checking if favourite
    // is updated
    @Test
    fun writeAndUpdateFavourite() = runBlocking {
        val country = Country(
            0,
            1,
            1,
            1,
            "United Kingdom",
            1,
            1,
            "",
            "na",
            "Europe",
            "GB",
            "na",
            0,
            "London",
            "na",
            1
        )
        dao.insert(cacheMapper.mapToData(country))
        country.favourite = 1
        dao.update(cacheMapper.mapToData(country))
        val countrys = dao.getAll()

        assertEquals(countrys.get(0).favourite, 1)
    }
}