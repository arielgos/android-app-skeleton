package com.arielgos.mpos.entities

import com.arielgos.mpos.core.annotations.Autoincrement
import com.arielgos.mpos.core.annotations.Entity
import com.arielgos.mpos.core.annotations.Key
import com.arielgos.mpos.core.annotations.Ignore

@Entity
data class Order(@field:Autoincrement @field:Key val id: Long, val customerId: String, @field:Ignore val customer: Customer) {}