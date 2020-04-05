package tech.thdev.app.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.coroutines.flow.callbackFlow
import tech.thdev.app.databinding.MainFragmentBinding
import java.io.File

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private val sharedPreferences: SharedPreferences by lazy {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        android.util.Log.e("TEMP", "masterKeyAlias ${masterKeyAlias}")

        EncryptedSharedPreferences.create(
            "security_test",
            masterKeyAlias,
            requireContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (sharedPreferences.getString("edit", "").isNullOrEmpty()) {
            sharedPreferences
                .edit()
                .putString("edit", "test test test")
                .apply()
        }

        val biometricPrompt = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .setConfirmationRequired(true)
            .build()

        val listener = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                android.util.Log.i("TEMP", "Unlock error errorCode $errorCode errString $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // Unlocked
                android.util.Log.i("TEMP", "Unlock success")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                android.util.Log.i("TEMP", "Unlock failed")
            }
        }
        val biometric = BiometricPrompt(
            requireActivity(),
            ContextCompat.getMainExecutor(requireContext()),
            listener
        )

        binding.btnFileExample.setOnClickListener {
            // Although you can define your own key generation parameter specification, it's
            // recommended that you use the value specified here.
            val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
            val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

            val fileToRead = "my_sensitive_data.txt"
            val file = File(requireContext().cacheDir, fileToRead)
            if (file.exists()) {
                file.delete()
            }
            val encryptedFile = EncryptedFile.Builder(
                file,
                requireContext(),
                "masterKeyAlias--aaaa",
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            encryptedFile.openFileOutput().bufferedWriter().use {
                it.write(binding.editQuery.text.toString())
            }

            val content = encryptedFile.openFileInput().bufferedReader().useLines { lines ->
                lines.fold(" ") { working, line ->
                    "$working $line"
                }
            }


//            binding.tvShowQuery.text = content

            if (binding.editQuery.text.isNotEmpty()) {
                sharedPreferences
                    .edit()
                    .putString("edit", binding.editQuery.text.toString())
                    .apply()
            }
            binding.tvShowQuery.text = sharedPreferences.getString("edit", "")
        }
    }

}
