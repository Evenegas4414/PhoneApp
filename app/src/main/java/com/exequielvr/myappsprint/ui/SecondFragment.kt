package com.exequielvr.myappsprint.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.exequielvr.myappsprint.databinding.FragmentSecondBinding
import com.exequielvr.myappsprint.viewmodel.ItemsViewModel


class SecondFragment : Fragment() {

    private lateinit var mBinding: FragmentSecondBinding
    private val mViewModel: ItemsViewModel by activityViewModels()

    private var itemId: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->

            itemId = bundle.getString("itemId")
            Log.d("Item id", itemId.toString())
        }

        itemId?.let { id ->
            mViewModel.getItemDetailByIDFromInternet(id)
        }

        mViewModel.getItemDetail().observe(viewLifecycleOwner, Observer {
            Log.d("Item id:", itemId.toString())
            var id = it.id
            var name = it.name

            Glide.with(mBinding.ivLogo2).load(it.image).into(mBinding.ivLogo2)
            mBinding.tvnombre2.text = it.name
            mBinding.tvprecio2.text = "$" + it.price.toString()

            mBinding.btMail.setOnClickListener {
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.data = Uri.parse("mailto")
                emailIntent.type = "text/plain"

                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("phone@mail.cl"))
                emailIntent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por Producto " + name + "id " + id
                )
                emailIntent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n" +
                            "Vi el producto " + name + " de código " + id + " y me gustaría que me contactaran a este\n" +
                            "correo o al siguiente número correo@test.com numero 987654321\n" +
                            " _________\n" +
                            "Quedo atento."
                )
                try {
                    startActivity(emailIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}