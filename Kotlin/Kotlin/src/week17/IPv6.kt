package week17

fun main() = with(System.`in`.bufferedReader()) {
    var input = readLine()!!
    if (input.contains("::")) {
        val front = input.split("::")[0].split(":").size
        val back = input.split("::")[1].split(":").size
        input = if (front + back == 8) {
            input.replace("::", ":0000:")
        } else {
            input.replace("::", ":0000:".repeat(8 - (front + back)))
        }
    }
    input = input.replace("::", ":")
    if (input.split(":").size != 8) {
        if (input.last() == ':') {
            input = input.substring(0, input.length - 1)
        } else if (input.first() == ':') {
            input = input.substring(1, input.length)
        }
    }
    println(
        input.split(":")
            .joinToString(":") { str ->
                "0".repeat(4 - str.length).plus(str)
            }

    )
}