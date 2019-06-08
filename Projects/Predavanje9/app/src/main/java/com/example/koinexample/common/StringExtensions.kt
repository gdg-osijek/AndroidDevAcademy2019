package com.example.koinexample.common

import java.util.regex.Pattern

private const val EMAIL_REGEX_PATTERN = "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
private const val PASSWORD_REGEX_PATTERN = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{6,}\$"
const val EMPTY_STRING = ""

fun String.isValidEmail() = Pattern.compile(EMAIL_REGEX_PATTERN).matcher(this).matches()

fun String.isValidPassword() = Pattern.compile(PASSWORD_REGEX_PATTERN).matcher(this).matches()

fun String.isValidName(): Boolean {
  return this.trim().count() >= 2
}