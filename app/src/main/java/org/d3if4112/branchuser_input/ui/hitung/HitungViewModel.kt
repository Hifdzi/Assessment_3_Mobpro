package org.d3if4112.branchuser_input.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2014.hitungbmi.db.BmiDao
import org.d3if2014.hitungbmi.db.BmiEntity
import org.d3if4112.branchuser_input.model.HasilBmi
import org.d3if4112.branchuser_input.model.KategoriBmi
import org.d3if4112.branchuser_input.model.hitungBmi

class HitungViewModel(private val db: BmiDao) : ViewModel() {

    private val hasilBmi = MutableLiveData<HasilBmi?>()
    private val navigasi = MutableLiveData<KategoriBmi?>()

    fun hitungBmi(berat: Float, tinggi: Float, isMale: Boolean) {
        val dataBmi = BmiEntity(
            berat = berat,
            tinggi = tinggi,
            isMale = isMale,
        )
        hasilBmi.value = dataBmi.hitungBmi()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataBmi)
            }
        }
    }

    fun mulaiNavigasi() {
        navigasi.value = hasilBmi.value?.kategori
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi(): LiveData<KategoriBmi?> = navigasi

    fun getHasilBmi(): LiveData<HasilBmi?> = hasilBmi
}