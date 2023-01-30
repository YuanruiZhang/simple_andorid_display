package edu.uw.yuanruizhang.fetchdisplay.repository

import android.util.Log
import edu.uw.yuanruizhang.fetchdisplay.model.EmpLibrary
import edu.uw.yuanruizhang.fetchdisplay.model.Employee
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.google.gson.Gson

class DataRepository {
    private val EmpService = Retrofit.Builder()
        .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
        //.baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EmpService::class.java)
    suspend fun getEmps(): List<Employee> {
        var res = EmpService.getEmps()
        res = res
            .filterNot { it.name.isNullOrEmpty() }
            .sortedWith(compareBy<Employee> { it.listId }.thenBy { it.name })
        return res
    }
}

interface EmpService {
    @GET("hiring.json")
    suspend fun getEmps(): List<Employee>
}

//class DataRepository {
//    private val gson: Gson = Gson()
//    fun getEmps(): List<Employee> {
//        return gson.fromJson(empJSONString, Array<Employee>::class.java).toList()
//    }
//}


private val empJSONString = """
    [
        {"id": 755, "listId": 2, "name": "Item 684"},
        {"id": 203, "listId": 2, "name": "Item 684"},
        {"id": 684, "listId": 1, "name": "Item 684"},
        {"id": 276, "listId": 1, "name": "Item 276"},
        {"id": 736, "listId": 3, "name": "Item 276"},
        {"id": 926, "listId": 4, "name": "Item 276"},
        {"id": 808, "listId": 4, "name": "Item 808"}
    ]
""".trimIndent()