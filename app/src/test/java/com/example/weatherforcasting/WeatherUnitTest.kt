package com.example.weatherforcasting

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.banquemisr.challenge05.data.source.remote.WeatherEndPoint
import com.example.weatherforcasting.data.cititesrepo.WeatherRepo
import com.example.weatherforcasting.data.cititesrepo.WeatherRepoImplementer
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.data.source.AppDataBase
import com.example.weatherforcasting.data.source.local.WeatherDao
import com.example.weatherforcasting.data.source.local.dao.CityDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WeatherUnitTest {
    private lateinit var database: AppDataBase
    @Mock
    private lateinit var weatherService: WeatherEndPoint
    @Mock
    private lateinit var cityDao: CityDao
    @Mock
    private lateinit var weatherDao: WeatherDao
    lateinit var weatherRepo: WeatherRepo
    @Before
    fun setUp() {
       /* database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()*/
        MockitoAnnotations.openMocks(this)
        database = Mockito.mock(AppDataBase::class.java)
        weatherRepo = WeatherRepoImplementer(database,weatherService)

        cityDao = database.cityDao()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `testDataBase_of_weather_info`() {
        runBlocking {
            val weather = weatherRepo.getWeatherFromLocal(50)
            /*val city = City("test_cairo", "test_cairo", 1, 1.0, 1.0)
            cityDao.insertCity(city)
            val fetchedCity = cityDao.getCityById(1)*/
            assertNotNull(weather)
            assertNotNull(weather.error)

    }
}

@After
fun afterTest() {
    database.close()

}
}