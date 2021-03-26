package br.com.concrete.composekmmmoviesapp.shared

import kotlinx.coroutines.runBlocking

actual fun <T> runTest(block: suspend () -> T) { runBlocking { block() } }
