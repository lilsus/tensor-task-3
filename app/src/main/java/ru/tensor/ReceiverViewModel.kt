package ru.tensor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiverViewModel : ViewModel() {
    private val mutableMessage = MutableLiveData<String>()

    val message: LiveData<String>
        get() = mutableMessage

    fun changeMessage(message: String) {
        mutableMessage.value = message
    }
}