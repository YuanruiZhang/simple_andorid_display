package edu.uw.yuanruizhang.fetchdisplay.adapter
import androidx.recyclerview.widget.DiffUtil
import edu.uw.yuanruizhang.fetchdisplay.model.Employee

class EmpDiffCallBack(private val newEmployees: List<Employee>, private val oldSongs: List<Employee>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldSongs.size

    override fun getNewListSize(): Int = newEmployees.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSingleSong = newEmployees[newItemPosition]
        val oldSingleSong = oldSongs[oldItemPosition]

        return newSingleSong.id == oldSingleSong.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSingleSong = newEmployees[newItemPosition]
        val oldSingleSong = oldSongs[oldItemPosition]
        return newSingleSong.id == oldSingleSong.id
    }
}