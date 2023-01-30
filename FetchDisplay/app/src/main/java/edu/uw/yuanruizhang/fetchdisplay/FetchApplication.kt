package edu.uw.yuanruizhang.fetchdisplay

import android.app.Application
import android.content.Context
import edu.uw.yuanruizhang.fetchdisplay.manager.FetchManager
import edu.uw.yuanruizhang.fetchdisplay.repository.DataRepository

class FetchApplication: Application() {
    lateinit var dataRepository: DataRepository
    //lateinit var refreshEmpManager: RefreshEmpManager

    //val fetchManager: FetchManager by lazy { FetchManager() }

    override fun onCreate() {
        super.onCreate()
        //this.refreshEmpManager = RefreshEmpManager(this)
        dataRepository = DataRepository()
    }
}