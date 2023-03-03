package com.feduss.buswear.enums

sealed class Section(val baseRoute: String, val parametricRoute: String = "") {
    object LinesList: Section("lines")
    object LineDirections: Section("lines","lines/{lineId}")
    object LineStops: Section("lines","lines/{${Params.LineId.name}}/{${Params.LineDirection.name}}")
    object LineTimes: Section("lines","lines/{${Params.LineId.name}}/{${Params.LineDirection.name}}/{${Params.Stop.name}}")

    fun withArgs(args: List<String>? = null, optionalArgs: Map<String, String>? = null): String {
        var destinationRoute = baseRoute
        args?.let { argsNotNull ->
            for(arg in argsNotNull) {
                destinationRoute += "/$arg"
            }
        }
        optionalArgs?.let { optionalArgsNotNull ->
            destinationRoute+= "?"
            optionalArgsNotNull.onEachIndexed { index, (optionalArgName, optionalArgValue) ->
                destinationRoute += "$optionalArgName=$optionalArgValue"

                if (optionalArgsNotNull.count() > 1 && index < optionalArgsNotNull.count() - 1) {
                    destinationRoute += "&"
                }
            }
        }
        return destinationRoute
    }
}
