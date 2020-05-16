package tech.thdev.app.ui.first

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import tech.thdev.app.databinding.FirstFragmentBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class FirstFragment : Fragment() {

    companion object {
        const val MY_PERMISSION_REQUEST_STORAGE = 1000
    }

    private lateinit var binding: FirstFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FirstFragmentBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRequestPermission.setOnClickListener {
            readWriteFile()
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("External Storage")
            .setMessage("Granted permission")
            .setPositiveButton("ok") { _, _ ->
                requestPermission()
            }
            .setNegativeButton("cancel", null)
            .show()
    }

    private fun requestPermission() {
        // No explanation needed, we can request the permission.
        requestPermissions(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            MY_PERMISSION_REQUEST_STORAGE
        )
    }

    private fun readWriteFile() {
        Log.i(
            "TEMP",
            "checkPermission : ${ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )}"
        )

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                showDialog()
            } else {
                requestPermission()
            }
        } else {
            Log.e("TEMP", "permission Granted")
            writeFile()
            readFile()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSION_REQUEST_STORAGE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d("TEMP", "Permission granted")
                    writeFile()
                    readFile()

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {
                    Log.d("TEMP", "Permission denied")

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    /**
     * Create file example.
     */
    private fun writeFile() {
        val message = "Request App Permissions" +
                "\n\nEvery Android app runs in a limited-access sandbox. " +
                "If an app needs to use resources or information outside of " +
                "its own sandbox, the app has to request the appropriate permission. " +
                "You declare that your app needs a permission by listing the permission " +
                "in the app manifest and then requesting that the user approve each " +
                "permission at runtime (on Android 6.0 and higher)." +
                "\n\nThis page describes " +
                "how to use the Android Support Library to check for and request permissions. " +
                "The Android framework provides similar methods as of Android 6.0 (API level 23), " +
                "but using the support library makes it easier to provide compatibility with " +
                "older versions of Android."
        try {
            val file = File(getExternalStorageDirectory(), "temp.txt")
            Log.d("TEMP", "file path ${file.absolutePath} path ${file.path}")
            if (file.exists().not()) {
                file.createNewFile()
            }
            FileOutputStream(file).use {
                it.write(message.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readFile() {
        val file = File(getExternalStorageDirectory(), "temp.txt")
        if (file.exists()) {
            binding.tvMessage.text = file.readText()
        }
    }
}
