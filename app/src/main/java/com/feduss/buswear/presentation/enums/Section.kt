package com.feduss.buswear.presentation.enums

sealed class Section(val baseRoute: String, val parametricRoute: String = "") {
    object Home: Section("home")
    object LineDirections: Section("lines","lines/{lineId}")
    object LineStops: Section("lines","lines/{lineId}/{directionName}")
    object LineTimes: Section("lines","lines/{lineId}/{directionName}/{stopName}")

    fun withArgs(args: List<String>? = null, optionalArgs: Map<String, String>? = null): String {
        var destinationRoute = baseRoute
        args?.let { argsNotNull ->
            for(arg in argsNotNull) {
                destinationRoute += "/$arg"
            }
        }
        optionalArgs?.let { optionalArgsNotNull ->
            destinationRoute+= "?"
            optionalArgsNotNull.onEachIndexed { index, (optionalArgName, optionaArgValue) ->
                destinationRoute += "$optionalArgName=$optionaArgValue"

                if (optionalArgsNotNull.count() > 1 && index < optionalArgsNotNull.count() - 1) {
                    destinationRoute += "&"
                }
            }
        }
        return destinationRoute
    }
}
