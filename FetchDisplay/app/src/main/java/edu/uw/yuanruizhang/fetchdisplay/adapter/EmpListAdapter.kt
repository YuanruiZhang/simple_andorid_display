package edu.uw.yuanruizhang.fetchdisplay.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


import edu.uw.yuanruizhang.fetchdisplay.model.Employee
import edu.uw.yuanruizhang.fetchdisplay.databinding.ActivityEmpBinding

class EmpListAdapter(private var listOfEmps: List<Employee>): RecyclerView.Adapter<EmpListAdapter.EmpViewHolder>() {

    var onSongClickListener: (position: Int, employee: Employee) -> Unit = { _, _ ->  }

    //auotmically create more view holder whenever user scrolls
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        val binding = ActivityEmpBinding.inflate(LayoutInflater.from(parent.context))
        return EmpViewHolder(binding)
    }

    //fit the correct item into view holder
    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) {
        val singleEmp = listOfEmps[position]
        with(holder.binding) {
            //commented out the old way on loading local images. Using coil to fetch online pics
            //ivSongPic.setImageResource(singleSong.smallImageID)
            //ivSongPic.load(singleEmp.smallImageURL)
            tvEmpId.text = singleEmp.id.toString()
            tvEmpListId.text = singleEmp.listId.toString()
            tvEmpName.text = singleEmp.name

            itemRoot.setOnClickListener {
                onSongClickListener(position, singleEmp)
            }
        }
    }

    override fun getItemCount(): Int = listOfEmps.size


    fun updateSongs(newListOfSongs: List<Employee>) {
        //using diffutil: A fancier way to add animation when repositing list
        val callback = EmpDiffCallBack(newListOfSongs, listOfEmps)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)

        this.listOfEmps = newListOfSongs

        //notifyDataSetChanged()
    }

    class EmpViewHolder(val binding: ActivityEmpBinding): RecyclerView.ViewHolder(binding.root)


}