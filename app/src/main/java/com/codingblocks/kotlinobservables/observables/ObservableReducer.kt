package com.codingblocks.kotlinobservables.observables

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ObservableReducer<R, T>(
    vararg observates: StandardObservableProperty<R>,
    reducer: (List<R>) -> T
): StandardObservableProperty<T>(reducer(observates.map { it.value })) {
    init {
        value = reducer(observates.map { it.value })
        observates.forEach {
            it.addValueChangeListener {
                value = reducer(observates.map { it.value })
            }
        }
    }

}