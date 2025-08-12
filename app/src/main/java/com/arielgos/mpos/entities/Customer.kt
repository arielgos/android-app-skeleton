package com.arielgos.mpos.entities

import com.arielgos.mpos.core.annotations.Entity
import com.arielgos.mpos.core.annotations.Key

@Entity data class Customer(@field:Key val id: Long, val name: String) {}