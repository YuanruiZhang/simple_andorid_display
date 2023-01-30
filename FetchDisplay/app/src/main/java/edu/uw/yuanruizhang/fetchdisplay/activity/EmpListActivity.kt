package edu.uw.yuanruizhang.fetchdisplay.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.uw.yuanruizhang.fetchdisplay.FetchApplication
import edu.uw.yuanruizhang.fetchdisplay.model.Employee

import edu.uw.yuanruizhang.fetchdisplay.adapter.EmpListAdapter
import edu.uw.yuanruizhang.fetchdisplay.databinding.ActivityEmpListBinding
import edu.uw.yuanruizhang.fetchdisplay.repository.DataRepository
import kotlinx.coroutines.launch


class EmpListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEmpListBinding
    private lateinit var adapter: EmpListAdapter

    private val FetchApp: FetchApplication by lazy {application as FetchApplication }
    private val dataRepository by lazy { FetchApp.dataRepository }

    private lateinit var employees: List<Employee>
    //private lateinit var dataRepository: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmpListBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {
            employees = listOf()
            adapter = EmpListAdapter(employees)
            loadSongList()
            //Set Adapter to Recycler View with data
            rvEmps.adapter = adapter

            //refresh features
            btnRefresh.setOnClickListener { loadSongList() }
            pullDownContainer.setOnRefreshListener {
                loadSongList()
                pullDownContainer.isRefreshing = false
            }

        }
    }

    private fun loadSongList() {
        lifecycleScope.launch {
            runCatching {
                Toast.makeText(this@EmpListActivity, "loading...", Toast.LENGTH_SHORT).show()
                employees = dataRepository.getEmps()
                adapter.updateSongs(employees)
            }.onFailure {
                Toast.makeText(this@EmpListActivity, "Error occurred when fetching employee data", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

