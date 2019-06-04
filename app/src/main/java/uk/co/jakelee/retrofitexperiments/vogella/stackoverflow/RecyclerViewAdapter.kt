package uk.co.jakelee.retrofitexperiments.vogella.stackoverflow

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class RecyclerViewAdapter(private val data: List<Answer>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = data.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var text: TextView = v.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_selectable_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val answer = data[position]
        holder.text.text = answer.toString()
        holder.itemView.setTag(answer.answerId)
    }

}