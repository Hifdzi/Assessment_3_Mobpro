package org.d3if4112.branchuser_input.model

import org.d3if2014.hitungbmi.db.BmiEntity

fun BmiEntity.hitungBmi(): HasilBmi {
    val tinggiCm = tinggi / 100
    val bmi = berat / (tinggiCm * tinggiCm)
    val kategori = if (isMale) {
        when {
            bmi < 20.5 -> KategoriBmi.KURUS
            bmi >= 27.0 -> KategoriBmi.GEMUK
            else -> KategoriBmi.IDEAL
        }
    } else {
        when {
            bmi < 18.5 -> KategoriBmi.KURUS
            bmi >= 25.0 -> KategoriBmi.GEMUK
            else -> KategoriBmi.IDEAL
        }
    }
    return HasilBmi(bmi, kategori)
}