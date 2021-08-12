package com.sungshin.recyclear.check.spinner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.ItemSpinnerBinding

class SpinnerAdapterLabel(
    context: Context,
    @LayoutRes private val resId: Int,
    private val values: MutableList<SpinnerModel>
) : ArrayAdapter<SpinnerModel>(context, resId, values) {

    override fun getCount() = values.size


    override fun getItem(position: Int) = values[position]

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val model = values[position]
        try {
            binding.imageviewSpinnerCustom.setImageResource(model.image)
            binding.imageviewSpinnerCustom.setColorFilter(ContextCompat.getColor(context, R.color.black))
            binding.textviewSpinnerCustom.text = model.name
            binding.textviewSpinnerCustom.setTextColor(ContextCompat.getColor(context, R.color.black))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val model = values[position]
        try {
            binding.imageviewSpinnerCustom.setImageResource(model.image)
            binding.textviewSpinnerCustom.text = model.name

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

}