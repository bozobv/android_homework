package hu.bme.aut.android.high_fighter.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import hu.bme.aut.android.high_fighter.databinding.FragmentLoginBinding
import hu.bme.aut.android.high_fighter.extensions.validateNonEmpty


class LoginFragment : Fragment() {
    private var progressDialog: ProgressDialog? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private fun validateForm() = binding.etEmail.validateNonEmpty() && binding.etPassword.validateNonEmpty()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener { registerClick() }
        binding.btnLogin.setOnClickListener { loginClick() }
    }


    private fun registerClick() {
        if (!validateForm()) {
            return
        }

        val pd = ProgressDialog(activity)
        pd.setMessage("Töltés")
        pd.show()

        firebaseAuth
            .createUserWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            .addOnSuccessListener { result ->
                pd.hide()

                val firebaseUser = result.user
                val profileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(firebaseUser?.email?.substringBefore('@'))
                    .build()
                firebaseUser?.updateProfile(profileChangeRequest)

                Toast.makeText(activity,"Regisztráció sikeres!",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                pd?.let { dialog ->
                    if (dialog.isShowing) {
                        dialog.dismiss()
                    }
                }
                progressDialog = null

                Toast.makeText(activity,exception.message,Toast.LENGTH_SHORT).show()

            }
    }

    private fun loginClick() {
        if (!validateForm()) {
            return
        }

        showProgressDialog()

        firebaseAuth
            .signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            .addOnSuccessListener {
                hideProgressDialog()

                val action = LoginFragmentDirections.actionLoginFragmentToMenuFragment()
                findNavController().navigate(action)
                //finish()
            }
            .addOnFailureListener { exception ->
                hideProgressDialog()

                Toast.makeText(activity,exception.localizedMessage,Toast.LENGTH_SHORT).show();
            }
    }

    fun showProgressDialog() {
        if (progressDialog != null) {
            return
        }

        progressDialog = ProgressDialog(activity).apply {
            setCancelable(false)
            setMessage("Töltés...")
            show()
        }
    }

    protected fun hideProgressDialog() {
        progressDialog?.let { dialog ->
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        progressDialog = null
    }

}