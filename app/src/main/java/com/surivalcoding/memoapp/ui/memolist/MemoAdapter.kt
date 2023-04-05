package com.surivalcoding.memoapp.ui.memolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surivalcoding.memoapp.data.local.MemoDao
import com.surivalcoding.memoapp.data.model.Memo
import javax.inject.Inject

class MemoAdapter(
    private val onClicked: (Memo) -> Unit,
    private val onLongClicked: (Memo) -> Unit,
) : ListAdapter<Memo, MemoAdapter.ViewHolder>(
    object : ItemCallback<Memo>() {
        override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem == newItem
        }
    }
) {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(android.R.id.text1)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(android.R.layout.simple_list_item_1, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val memo = getItem(position)

        viewHolder.textView.text = memo.content

        // 클릭 이벤트 정의
        viewHolder.itemView.setOnClickListener {
            // 콜백으로 처리하는 부분으로 돌려줘야 함
//            println(dataSet[viewHolder.adapterPosition])
            onClicked(getItem(viewHolder.adapterPosition))
        }

        viewHolder.itemView.setOnLongClickListener {
            onLongClicked(getItem(viewHolder.adapterPosition))
            return@setOnLongClickListener true
        }

    }

}
