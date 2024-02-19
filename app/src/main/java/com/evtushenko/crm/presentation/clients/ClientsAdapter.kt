package com.evtushenko.crm.presentation.clients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evtushenko.crm.R
import com.evtushenko.crm.databinding.ClientsItemBinding
import com.evtushenko.crm.models.ClientGender
import com.evtushenko.crm.models.ClientItem

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 18.02.2024
 */
class ClientsAdapter :
    ListAdapter<ClientItem, ClientsAdapter.ClientViewHolder>(ClientDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(
            ClientsItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ClientViewHolder(
        private val binding: ClientsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ClientItem) {
            binding.clientsItemTitle.text = item.title
            binding.clientsItemSubtitle.text = item.subTitle
            binding.clientsItemAvatar.setImageResource(
                when (item.gender) {
                    ClientGender.FEMALE -> {
                        R.drawable.ic_female_avatar_64dp
                    }

                    ClientGender.MALE -> {
                        R.drawable.ic_male_avatar_64dp
                    }
                }
            )
        }
    }

    class ClientDiffUtil : DiffUtil.ItemCallback<ClientItem>() {
        override fun areItemsTheSame(oldItem: ClientItem, newItem: ClientItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ClientItem, newItem: ClientItem): Boolean =
            oldItem == newItem
    }
}