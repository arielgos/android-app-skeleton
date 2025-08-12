package com.arielgos.mpos.entities

import com.arielgos.mpos.core.annotations.Entity
import com.arielgos.mpos.core.annotations.Key

@Entity data class User(@field:Key val id: Int, val email: String, val phone: String?, val number: Long?) {}


