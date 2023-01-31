package edu.uw.yuanruizhang.fetchdisplay.adapter
import androidx.recyclerview.widget.DiffUtil
import edu.uw.yuanruizhang.fetchdisplay.model.Employee

class EmpDiffCallBack(private val newEmployees: List<Employee>, private val oldEmployees: List<Employee>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldEmployees.size

    override fun getNewListSize(): Int = newEmployees.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSingleEmp = newEmployees[newItemPosition]
        val oldSingleEmp = oldEmployees[oldItemPosition]

        return newSingleEmp.id == oldSingleEmp.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSingleEmp = newEmployees[newItemPosition]
        val oldSingleSong = oldEmployees[oldItemPosition]
        return newSingleEmp.id == oldSingleSong.id
    }
}