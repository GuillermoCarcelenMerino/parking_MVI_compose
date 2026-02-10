package com.neo.core.domain.mappers

interface ResultMapper < T,  R> {
    fun map(data : T) : R
}