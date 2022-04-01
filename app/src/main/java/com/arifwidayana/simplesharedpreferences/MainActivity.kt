package com.arifwidayana.simplesharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arifwidayana.simplesharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val shareFile = "kotlinsharedpreference"

    companion object {
        const val KEY_NAME = "KEY_NAME"
        const val KEY_ID = "KEY_ID"
        const val DEFAULT_NAME = "DEFAULT_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared: SharedPreferences = this.getSharedPreferences(shareFile, Context.MODE_PRIVATE)
        binding.btnSave.setOnClickListener {
            val id = binding.etId.text.toString().toInt()
            val name = binding.etName.text.toString()
            val editor: SharedPreferences.Editor = shared.edit()
            editor.apply{
                putInt(KEY_ID, id)
                putString(KEY_NAME, name)
                apply()
            }
            Toast.makeText(this, "Data Anda Tersimpan", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener {
            val shareId = shared.getInt(KEY_ID, 0)
            val shareName = shared.getString(KEY_NAME, DEFAULT_NAME)
            if (shareId == 0 && shareName == DEFAULT_NAME) {
                binding.apply {
                    tvId.text = "Default ID : $shareId"
                    tvName.text = "Default Nama : $shareName"
                }
                Toast.makeText(this, "Data Ditampilkan", Toast.LENGTH_SHORT).show()
            } else {
                binding.apply {
                    tvId.text = shareId.toString()
                    tvName.text = shareName.toString()
                }
                Toast.makeText(this, "Data Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            val editor = shared.edit()
            editor.apply{
                clear()
                apply()
            }
            binding.tvId.text = ""
            binding.tvName.text = ""
        }

    }
}