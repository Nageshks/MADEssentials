package com.nageshempire.mad.essentials.extensions

import androidx.appcompat.widget.SearchView

fun SearchView.onQuery(query: () -> Unit) = setOnQueryTextListener( object : SearchView.OnQueryTextListener{
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        query()
        return false
    }
})