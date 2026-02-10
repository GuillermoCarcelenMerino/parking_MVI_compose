package com.neo.parking.feature.Register.vm

import androidx.lifecycle.viewModelScope
import com.neo.core.data.network.launchWithResponse
import com.neo.core.domain.usecase.CreateAccountUsecase
import com.neo.core.ui.BaseViewModel
import com.neo.core.utils.DataStore.DataStore
import com.neo.core.utils.DataStore.DataStoreKey
import com.neo.core.utils.cache.LocalCache
import com.neo.core.utils.cache.LocalKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createAccountUsecase: CreateAccountUsecase,
    private val dataStore: DataStore,
    private val localCache: LocalCache
) : BaseViewModel<UIState, UIIntent, Event>(UIState()) {


    override fun sendIntent(intent: UIIntent) {
        when (intent) {
            is UIIntent.CreateAccountIntent -> {
                viewModelScope.launch {
                    createAccountUsecase.createAccount(
                        state.value.mail,
                        state.value.pass,
                        state.value.name
                    ).launchWithResponse(
                        {
                            emitEffect(
                                Event.UserCreated
                            )
                        }, {
                            //todo control y visual con effect
                        }
                    )
                }
            }

            is UIIntent.UpdateNameIntent -> {
                updateUiState { copy(name = intent.name) }

            }

            is UIIntent.UpdateMailIntent -> {
                updateUiState { copy(mail = intent.mail) }
            }

            is UIIntent.UpdateMailConfirmationIntent -> {
                updateUiState { copy(mailConfirmation = intent.mail) }
            }

            is UIIntent.UpdatePasswordIntent -> {
                updateUiState { copy(pass = intent.pass) }
            }

        }
    }
}