@file:Suppress("UNREACHABLE_CODE")

package com.example.tareapreferenciaskotlin
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.tareapreferenciaskotlin.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val sharedPrefFile = "datospreferencias"

    private var _binding: FragmentFirstBinding? = null
    private lateinit var mSharedPreferences: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)


        fun guardar() {

            val entero = binding.txtingresoentero.text
            val cadena = binding.txttexto.text
            val opcion = binding.txtboolean.isChecked
            val decimal = binding.txtingresodecimal.text
            val id: Int = Integer.parseInt(entero.toString())
            val name: String = cadena.toString()
            val alternativa: Boolean = opcion.toString().toBoolean()
            val floatnumber: Float = decimal.toString().toFloat()


            val pref =
                requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
            pref?.edit { putInt("id_key", id) }
            pref?.edit { putString("name_key", name) }
            pref?.edit { putBoolean("name_boolean", alternativa) }
            pref?.edit { putFloat("name_float", floatnumber) }



            Toast.makeText(context, "Datos Guardados en Caché", Toast.LENGTH_LONG).show()
        }

        fun mostrar() {

            val pref =
                requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
            val valorentero = pref?.getInt("id_key", 0)
            val valorcadena = pref?.getString("name_key", "NO HAY CADENA")
            val valoropcion = pref?.getBoolean("name_boolean", false)
            val valordecimal = pref?.getFloat("name_float", 0.0F)

            if (valorentero == 0 && valorcadena.equals("NO HAY CADENA")) {
                binding.txtresultadoentero.text = "VALOR: ${valorentero}".toString()
                binding.txtresultadotexto.text = "${valorcadena}".toString()
                binding.txtresultadodecimal.text = "NO HAY VALOR ALMACENADO".toString()
                binding.txtboolean.text = "NO HAY OPCION GUARDADA EN CACHE"
                binding.txtresultadoentero.setTextColor(Color.RED)
                binding.txtresultadotexto.setTextColor(Color.RED)
                binding.txtresultadodecimal.setTextColor(Color.RED)
                binding.txtboolean.setTextColor(Color.RED)


            } else {
                binding.txtresultadoentero.text = valorentero.toString()
                binding.txtresultadotexto.text = valorcadena.toString()
                binding.txtboolean.isChecked = valoropcion.toString().toBoolean()
                binding.txtresultadodecimal.text = valordecimal.toString()

                binding.txtboolean.text = valoropcion.toString()
                binding.txtboolean.setTextColor(Color.BLUE)
                binding.txtresultadoentero.setTextColor(Color.BLUE)
                binding.txtresultadotexto.setTextColor(Color.BLUE)
                binding.txtresultadodecimal.setTextColor(Color.BLUE)
            }
        }

        fun limpiardatosdecache() {
            requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()
            Toast.makeText(context, "Datos Borrados de Caché", Toast.LENGTH_LONG).show()
            binding.txtresultadoentero.text = ""
            binding.txtresultadotexto.text = ""
            binding.txtresultadodecimal.text = ""

        }

        binding.btnguardar.setOnClickListener { guardar() }
        binding.btnmostrar.setOnClickListener { mostrar() }
        binding.btnborrar.setOnClickListener { limpiardatosdecache() }


        return binding.root
    }

}


