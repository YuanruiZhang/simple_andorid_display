package edu.uw.yuanruizhang.fetchdisplay.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.uw.yuanruizhang.fetchdisplay.FetchApplication
import edu.uw.yuanruizhang.fetchdisplay.model.Employee

import edu.uw.yuanruizhang.fetchdisplay.adapter.EmpListAdapter
import edu.uw.yuanruizhang.fetchdisplay.databinding.ActivityEmpListBinding
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
            loadEmpList(0)
            //Set Adapter to Recycler View with data
            rvEmps.adapter = adapter

            //Show Selected Group
            btnNum1.setOnClickListener { loadEmpList(1) }
            //Show Selected Group
            btnNum2.setOnClickListener { loadEmpList(2) }
            //Show Selected Group
            btnNum3.setOnClickListener { loadEmpList(3) }
            //Show Selected Group
            btnNum4.setOnClickListener { loadEmpList(4) }

            //Refresh
            pullDownContainer.setOnRefreshListener {
                loadEmpList(0)
                pullDownContainer.isRefreshing = false
            }

        }
    }

    private fun loadEmpList(num:Int) {
        lifecycleScope.launch {
            runCatching {
                Toast.makeText(this@EmpListActivity, "loading...", Toast.LENGTH_SHORT).show()
                employees = dataRepository.getEmps()
                employees = employees.filterNot { it.name.isNullOrEmpty() }
                if(num > 0){
                    employees = employees.filter { it.listId == num }
                }
                employees = employees.sortedWith(compareBy<Employee> { it.listId }.thenBy { it.name })
                adapter.updateEmps(employees)

            }.onFailure {
                Toast.makeText(this@EmpListActivity, "Error occurred when fetching employee data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

