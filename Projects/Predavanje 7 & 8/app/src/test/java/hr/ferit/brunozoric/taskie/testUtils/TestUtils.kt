package hr.ferit.brunozoric.taskie.testUtils

import hr.ferit.brunozoric.taskie.model.BackendTask

fun emptyBackendTask() = BackendTask(
    "",
    "",
    "",
    "",
    false,
    -1,
    false
)

const val INVALID_CODE = 403
const val CODE_NO_BODY = 201
const val VALID_CODE = 200