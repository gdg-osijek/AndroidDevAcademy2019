package hr.ferit.brunozoric.taskie.model.response

import hr.ferit.brunozoric.taskie.model.BackendTask

data class GetTasksResponse(val notes: MutableList<BackendTask>? = mutableListOf())