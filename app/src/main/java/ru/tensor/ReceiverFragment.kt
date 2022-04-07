package ru.tensor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ReceiverFragment : Fragment() {

    companion object {
        private const val MESSAGE_KEY = "MESSAGE_KEY"

        fun newInstance(message: String): Fragment =
            ReceiverFragment().apply {
                arguments = Bundle().apply {
                    putString(MESSAGE_KEY, message)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_receiver, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val messageTextView = view.findViewById<TextView>(R.id.message)

        val viewModel = ViewModelProvider(this).get(ReceiverViewModel::class.java)
        viewModel.message.observe(viewLifecycleOwner) { message ->
            messageTextView.text = message
        }

        if (savedInstanceState == null) {
            val message = requireArguments().getString(MESSAGE_KEY)!!
            viewModel.changeMessage(message)
        }

        view.findViewById<Button>(R.id.read_button).setOnClickListener {
           viewModel.changeMessage("All messages is read")
        }
    }
}