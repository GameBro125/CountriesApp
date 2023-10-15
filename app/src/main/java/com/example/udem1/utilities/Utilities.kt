package com.example.udem1.utilities

import java.util.Locale


fun correctNumber (value: Long):String {
    return String.format(Locale.US, "%,d", value)

}
