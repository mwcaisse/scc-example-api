package com.mwcaisse.examples.scc.recipeapi.exception

class EntityNotFoundException : Throwable {

    constructor(entityName: String, id: Long)
            : super("$entityName with an ID of $id not found!")
}