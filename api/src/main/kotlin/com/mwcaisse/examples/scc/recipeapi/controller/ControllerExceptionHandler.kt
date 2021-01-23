package com.mwcaisse.examples.scc.recipeapi.controller

import com.mwcaisse.examples.scc.recipeapi.exception.EntityNotFoundException
import com.mwcaisse.examples.scc.recipeapi.exception.EntityValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound() {    }

    @ExceptionHandler(EntityValidationException::class)
    fun handleEntityValidationException(ex: EntityValidationException) : ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(ex.message)
    }
}