package hr.ferit.brunozoric.taskie.model.request

data class UserDataRequest(val email: String, val password: String, val name: String? = null)