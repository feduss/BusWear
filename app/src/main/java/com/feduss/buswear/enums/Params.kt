package com.feduss.buswear.enums

sealed class Params(val name: String) {
    object LineId: Params("lineId")
    object LineDirection: Params("lineDirection")
    object StopId: Params("stopId")
}
