package com.rhea.tvapp.util

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object Util {
    fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    fun getHeightInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.heightPixels ?: 0
        return (width * percent) / 100
    }

    inline fun <reified T> getJsonFromFile(assets: AssetManager, fileName: String): T{
        val `is`: InputStream = assets.open(fileName)
        return Gson().fromJson(BufferedReader(InputStreamReader(`is`)), T::class.java)
    }
}