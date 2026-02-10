package com.neo.parking.feature.login.vm

import androidx.lifecycle.viewModelScope
import com.neo.core.data.network.launchWithResponse
import com.neo.core.domain.usecase.LoginUseCase
import com.neo.core.ui.BaseViewModel
import com.neo.core.utils.DataStore.DataStore
import com.neo.core.utils.DataStore.DataStoreKey
import com.neo.core.utils.cache.LocalCache
import com.neo.core.utils.cache.LocalKey
import com.neo.parking.feature.Register.vm.UIIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: DataStore,
    private val localCache: LocalCache
) : BaseViewModel<UiState, UiIntent, Event>(UiState()) {

    fun doLogin() {
        viewModelScope.launch {
            loginUseCase.doLogin(
                state.value.email,
                state.value.password
            ).launchWithResponse(
                {
                    dataStore.saveString(DataStoreKey.REFRESH_TOKEN.name, it.refreshToken)
                    dataStore.saveBoolean(
                        DataStoreKey.REMEMBER_LOGIN.name,
                        state.value.rememberLogin
                    )
                    localCache.saveString(LocalKey.TOKEN.name, it.token)
                    localCache.saveString(LocalKey.REFRESH_TOKEN.name, it.refreshToken)
                    emitEffect(Event.ONLOGGED)
                }, {
                    it
                }
            )
        }
    }

    override fun sendIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.DoLoginIntent -> {
                doLogin()
            }

            is UiIntent.EmailUpdateIntent -> {
                updateUiState { copy(email = intent.email) }

            }

            is UiIntent.PasswordUpdateIntent -> {
                updateUiState { copy(password = intent.password) }

            }

            is UiIntent.UpdateRememberIntent -> {
                updateUiState { copy(rememberLogin = !state.value.rememberLogin) }

            }

            is UiIntent.CheckLoggedIntent -> {
                viewModelScope.launch {
                    val logged = dataStore.getBoolean(DataStoreKey.REMEMBER_LOGIN.name)
                    if (logged) {
                        dataStore.getString(DataStoreKey.REFRESH_TOKEN.name)?.let {
                            localCache.saveString(LocalKey.REFRESH_TOKEN.name, it)
                        }
                        emitEffect(
                            Event.ONLOGGED
                            //todo cambiar a futuro
                        )
                    }
                }
            }
        }

    }

}