package com.neo.parking.feature.parking.vm

import androidx.lifecycle.viewModelScope
import com.neo.core.data.network.launchWithResponse
import com.neo.core.domain.usecase.ParkingsUseCase
import com.neo.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParkingViewModel @Inject constructor(
    private val getParkingUseCase: ParkingsUseCase,
) : BaseViewModel<UiState, UiIntent, Event>(UiState()) {

    override fun sendIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.GETPARKINGSINTENT -> {
                viewModelScope.launch {
                    getParkingUseCase.getParkings().launchWithResponse({
                        updateUiState {
                            copy(
                                parkings = it
                            )
                        }
                    }, {

                        //todo mostrar error
                    })
                }
            }

            is UiIntent.SEARCHPARKINGS -> {

            }
        }

    }


}