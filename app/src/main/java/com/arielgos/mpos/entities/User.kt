package com.arielgos.mpos.entities

import com.arielgos.mpos.db.annotations.Entity
import com.arielgos.mpos.db.annotations.Key

@Entity
data class User(@field:Key val id: Int, val email: String) {}


