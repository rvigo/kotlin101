package com.rvigo.kotlin101.delegation.byProperty

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ValidStoredItemsQuantity : ReadWriteProperty<Item, Int> {
    private var quantity: Int = 0

    override fun getValue(thisRef: Item, property: KProperty<*>): Int = quantity

    override fun setValue(thisRef: Item, property: KProperty<*>, value: Int) {
        if (value < 5) {
            throw IllegalArgumentException("Quantity must be greater than 5")
        }
        quantity = value
    }
}

// delegates the quantity property to StoredItemsValidation
class Item private constructor(private var name: String) {
    var quantity: Int by ValidStoredItemsQuantity()

    constructor(
        quantity: Int,
        name: String
    ) : this(name) {
        this.quantity = quantity
        this.name = name
    }
}