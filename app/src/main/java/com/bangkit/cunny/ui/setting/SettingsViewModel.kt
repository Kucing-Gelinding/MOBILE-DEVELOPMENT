package com.bangkit.cunny.ui.setting

import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsViewModel : ViewModel() {
    private val _logoutStatus = MutableLiveData<Boolean>()
    val logoutStatus: LiveData<Boolean> get() = _logoutStatus

    suspend fun logout(auth: FirebaseAuth, credentialManager: CredentialManager) {
        withContext(Dispatchers.IO) {
            try {
                // Sign out from FirebaseAuth
                auth.signOut()

                // Clear saved credentials
                credentialManager.clearCredentialState(ClearCredentialStateRequest())

                // Update logout status
                _logoutStatus.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
                _logoutStatus.postValue(false)
            }
        }
    }
}