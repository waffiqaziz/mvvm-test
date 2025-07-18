package com.waffiq.mvvmexample.presentation.adapter

import android.R.anim.fade_in
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.waffiq.mvvmexample.databinding.ItemListBinding
import com.waffiq.mvvmexample.domain.model.ResponseItemDomain
import com.waffiq.mvvmexample.presentation.ui.SecondActivity
import com.waffiq.mvvmexample.presentation.ui.SecondActivity.Companion.EXTRA_ID

class ListAdapter(private val context: Context) :
  RecyclerView.Adapter<ListAdapter.ViewHolder>() {

  private val listItem = ArrayList<ResponseItemDomain>()

  fun setList(itemList: List<ResponseItemDomain>) {
    val diffCallback = DiffCallback(this.listItem, itemList)
    val diffResult = DiffUtil.calculateDiff(diffCallback)

    this.listItem.clear()
    this.listItem.addAll(itemList)
    diffResult.dispatchUpdatesTo(this)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(listItem[position])
    holder.itemView.startAnimation(
      AnimationUtils.loadAnimation(holder.itemView.context, fade_in)
    )
  }

  override fun getItemCount(): Int = listItem.size

  inner class ViewHolder(private var binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    lateinit var data: ResponseItemDomain

    fun bind(data: ResponseItemDomain) {
      this@ViewHolder.data = data
      binding.tvTitle.contentDescription = data.title

      binding.tvTitle.text = data.title
      binding.containerResult.setOnClickListener {
        val a = Intent(context, SecondActivity::class.java)
        a.putExtra(EXTRA_ID,  data)
        context.startActivity(a)
      }
    }
  }

  inner class DiffCallback(
    private val oldList: List<ResponseItemDomain>,
    private val newList: List<ResponseItemDomain>,
  ) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
      oldList[oldItemPosition].id == newList[newItemPosition].id &&
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
      oldList[oldItemPosition].id == newList[newItemPosition].id &&
        oldList[oldItemPosition].title == newList[newItemPosition].title
  }
}
