package com.feduss.buswear.presentation.enums

sealed class Params(val name: String) {
    object LineId: Params("lineId")
    object LineDirection: Params("lineDirection")
    object StopName: Params("stopName")
}
