package tech.thdev.app.common.smartlock

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.tasks.OnCompleteListener


class SmartLockViewModel(val activity: FragmentActivity) :
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    lateinit var goToContent: () -> Unit
    lateinit var onCredentialRetrieved: (Credential) -> Unit

    companion object {
        const val TAG = "SmartLockViewModel"
        const val RC_SAVE = 10000
    }

    private val googleApiClient: GoogleApiClient by lazy {
        GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .enableAutoManage(activity, 0, this)
                .addApi(Auth.CREDENTIALS_API)
                .build()
    }

    private val credentialsClient: CredentialsClient by lazy {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            Credentials.getClient(activity, CredentialsOptions.Builder()
                    .forceEnableSaveDialog()
                    .build())
        } else {
            Credentials.getClient(activity)
        }
    }

    private val credentialsClientRequest: CredentialRequest by lazy {
        CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .build()
    }

    init {
        googleApiClient
    }

    fun loadCredential() {
        // show progress...

        credentialsClient.request(credentialsClientRequest).addOnCompleteListener(
                OnCompleteListener<CredentialRequestResponse> { task ->
                    if (task.isSuccessful) {
                        // See "Handle successful credential requests"
                        if (::onCredentialRetrieved.isInitialized) {
                            onCredentialRetrieved(task.result.credential)
                        }
                        return@OnCompleteListener
                    }
                })
    }

    fun saveCredential(userName: String, userPassowrd: String) {

    }

    fun saveCredential(credential: Credential) {
        // Credential is valid so save it.
        credentialsClient.save(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "SAVE: OK")
                Toast.makeText(activity, "Credentials saved", Toast.LENGTH_SHORT).show()
                if (::goToContent.isInitialized) {
                    goToContent()
                }
                return@addOnCompleteListener
            }

            when (it.exception) {
                is ResolvableApiException -> {
                    // Try to resolve the save request. This will prompt the user if
                    // the credential is new.
                    try {
                        (it.exception as ResolvableApiException).startResolutionForResult(activity, RC_SAVE)
                    } catch (e: IntentSender.SendIntentException) {
                        // Could not resolve the request
                        Log.e(TAG, "Failed to send resolution.", e)
                        Toast.makeText(activity, "Save failed", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    // Request has no resolution
                    Log.e(TAG, "${it.exception}")
                    Toast.makeText(activity, "Save failed", Toast.LENGTH_SHORT).show()

                    if (::goToContent.isInitialized) {
                        goToContent()
                    }
                }
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult:$requestCode:$resultCode:$data")
        if (requestCode == RC_SAVE) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "SAVE: OK")
                Toast.makeText(activity, "Credentials saved", Toast.LENGTH_SHORT).show()

                if (::goToContent.isInitialized) {
                    goToContent()
                }
            } else {
                Log.e(TAG, "SAVE: Canceled by user")
            }
        }
    }

    fun smartLockSignIn(userName: String, userPassword: String) {
        val credential = Credential.Builder(userName).setPassword(userPassword).build()
        // Login....
    }

    override fun onConnected(p0: Bundle?) {
        Log.d("TEMP", "onConnected")
    }

    override fun onConnectionSuspended(cause: Int) {
        Log.d("TEMP", "onConnectionSuspended: $cause")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d("TEMP", "onConnectionFailed: $connectionResult")
    }
}