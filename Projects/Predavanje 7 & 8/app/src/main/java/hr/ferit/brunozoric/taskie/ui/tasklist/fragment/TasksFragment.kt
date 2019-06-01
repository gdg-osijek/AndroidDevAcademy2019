package hr.ferit.brunozoric.taskie.ui.tasklist.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.*
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import hr.ferit.brunozoric.taskie.presentation.TasksFragmentPresenter
import hr.ferit.brunozoric.taskie.ui.activities.ContainerActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksFragment : BaseFragment(), AddTaskFragmentDialog.TaskAddedListener, TasksFragmentContract.View {

    private val adapter by lazy { TaskAdapter { onItemSelected(it) } }

    private val presenter: TasksFragmentContract.Presenter by lazy {
        TasksFragmentPresenter(BackendFactory.getTaskieInteractor())
    }

    companion object {
        fun newInstance(): Fragment {
            return TasksFragment()
        }
    }

    override fun getLayoutResourceId() = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
        getAllTasks()
    }

    private fun initListeners() {
        addTask.setOnClickListener { addTask() }
    }

    private fun onItemSelected(task: BackendTask) {
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.id)
        }
        startActivity(detailsIntent)
    }

    override fun onTaskAdded(task: BackendTask) {
        adapter.addData(task)
    }

    private fun addTask() {
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun getAllTasks() {
        progress.visible()
        presenter.onGetAllTasks()
    }


    private fun checkList(notes: MutableList<BackendTask>) {
        if (notes.isEmpty()) {
            noData.visible()
        } else {
            noData.gone()
        }
    }

    private fun onTaskiesReceived(taskies: MutableList<BackendTask>) = adapter.setData(taskies)

    override fun onTaskListRecieved(tasks: MutableList<BackendTask>) {
        progress.gone()
        checkList(tasks)
        onTaskiesReceived(tasks)
    }

    override fun onGetTasksFailed() {
        progress.gone()
        activity?.displayToast("Failed")
    }
}