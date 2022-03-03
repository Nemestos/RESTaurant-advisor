package com.tah.fourmetal.ui.form

//messages qui seront affiché si le validateur ne passe pas
private const val EMAIL_MESSAGE = "invalid email address"
private const val REQUIRED_MESSAGE = "this field is required"
private const val REGEX_MESSAGE = "value does not match the regex"

//une interface sealed permet d'avoir plusieurs classes pour une seule interface
sealed interface Validator {
    open class Email(var message: String = EMAIL_MESSAGE) : Validator
    open class Required(var message: String = REQUIRED_MESSAGE) : Validator
    open class Regex(var message: String = REGEX_MESSAGE, var regex: String) : Validator
}
