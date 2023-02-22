package com.feduss.buswear.presentation.enums

sealed class Params(val name: String) {
    object LineId: Params("lineId")
    object DirectionName: Params("directionName")
    object StopName: Params("stopName")
}
