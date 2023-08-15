package com.example.contacts.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ViewHolderContactBinding
import com.example.contacts.shared.contact.domain.entity.Contact

/**
 * Adapter class to list Contact.
 */
class ContactAdapter(
    private val contacts: List<Contact>,
    private val actionClick: (Contact) -> Unit,
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(var binding: ViewHolderContactBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** Generates a new instance of [onCreateViewHolder]. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ViewHolderContactBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    /** Returns the total number of contacts in the dataset. */
    override fun getItemCount(): Int {
        return contacts.size
    }

    /** Binds data to the ContactViewHolder at the specified position. */
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact: Contact = contacts[position]
        holder.binding.tvName.text = contact.userName
        holder.itemView.setOnClickListener {
            actionClick(contact)
        }
    }

}