package space.lobanovi.taskapp.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.databinding.FragmentAuthBinding
import space.lobanovi.taskapp.extenssion.snowToast
import java.util.concurrent.TimeUnit

@Suppress("ControlFlowWithEmptyBody")
class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var  auth = FirebaseAuth.getInstance()
    private var correctCode: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }


   private fun  initListeners() {
        binding.btnSend.setOnClickListener{
            if (binding.etPhone.text!!.isNotEmpty()){
                sendPhone()
                Toast.makeText(requireContext(), "Отправка", Toast.LENGTH_SHORT).show()
            }else{
                binding.etPhone.error = "Введите номер телефона"
            }
            binding.btnConfirm.setOnClickListener {
                sendCode()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun sendPhone() {
        auth.setLanguageCode("RU")
      val options = PhoneAuthOptions.newBuilder(auth)
          .setPhoneNumber(binding.etPhone.text.toString())
          .setTimeout(120L,TimeUnit.SECONDS)
          .setActivity(requireActivity())
          .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
              override fun onVerificationCompleted(p0: PhoneAuthCredential) {
              }

              override fun onVerificationFailed(p0: FirebaseException) {
                  snowToast("Невышло  "+p0.message.toString())
              }

              override fun onCodeSent(verificationCode: String, p1: PhoneAuthProvider.ForceResendingToken) {
                  correctCode = verificationCode
                  binding.etLayoutPhone.isVisible = false
                  binding.btnSend.isVisible = false

                  binding.smsCodeView.isVisible = true
                  binding.btnConfirm.isVisible = true
                  super.onCodeSent(verificationCode, p1)
              }

          })
          .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun initViews() {

    }
    private fun sendCode(){

            val credential = correctCode?.let { it1 -> PhoneAuthProvider.getCredential(it1,binding.smsCodeView.enteredCode) }

            if (credential != null) {
                signInWithPhoneAuthCredential(credential)

            }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.navigation_home)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    }
                }
            }
    }
}