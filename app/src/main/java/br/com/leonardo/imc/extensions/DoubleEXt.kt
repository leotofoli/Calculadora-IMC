package br.com.leonardo.imc.extensions

fun Double.format(digitos: Int) = java.lang.String.format("%.${digitos}f", this)
