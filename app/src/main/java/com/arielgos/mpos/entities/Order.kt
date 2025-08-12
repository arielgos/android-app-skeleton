package com.arielgos.mpos.entities

import com.arielgos.mpos.db.annotations.Autoincrement
import com.arielgos.mpos.db.annotations.Entity
import com.arielgos.mpos.db.annotations.Key
import com.arielgos.mpos.db.annotations.DoNotPersist

@Entity
data class Order(@field:Autoincrement @field:Key val id: Long, val customerId: String, @field:DoNotPersist val customer: Customer) {}