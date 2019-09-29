package utils

fun readJsonResponse(fileName: String) = ClassLoader.getSystemResource("responses/$fileName.json").readText()
