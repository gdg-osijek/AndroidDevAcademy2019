package hr.ferit.brunozoric.taskie.presentation

import com.nhaarman.mockito_kotlin.*
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.networking.interactors.TaskieInteractor
import hr.ferit.brunozoric.taskie.testUtils.CODE_NO_BODY
import hr.ferit.brunozoric.taskie.testUtils.INVALID_CODE
import hr.ferit.brunozoric.taskie.testUtils.VALID_CODE
import hr.ferit.brunozoric.taskie.testUtils.emptyBackendTask
import hr.ferit.brunozoric.taskie.ui.tasklist.fragment.AddTaskDilogContract
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AddTaskDialogDialogPresenterTest {

    private val interactor = mock<TaskieInteractor>()
    private val view = mock<AddTaskDilogContract.View>()

    private lateinit var presenter: AddTaskDialogDialogPresenter

    @Before
    fun setUp() {
        presenter = AddTaskDialogDialogPresenter(interactor)
        presenter.setView(view)
    }

    @Test
    fun `onAddTask saves new task`() {
        presenter.onAddTask(
            AddTaskRequest(
                "NekiTitle",
                "NekiContent",
                0
            )
        )

        verify(interactor).save(any(), any())
        verifyZeroInteractions(view)
    }

    @Test
    fun `onAddTask invalid title`() {
        presenter.onAddTask(
            AddTaskRequest(
                "",
                "NekiContent",
                0
            )
        )

        verify(view).showDataError()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTask invalid content`() {
        presenter.onAddTask(
            AddTaskRequest(
                "NekiTitle",
                "",
                0
            )
        )

        verify(view).showDataError()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTask invalid data`() {
        presenter.onAddTask(
            AddTaskRequest(
                "",
                "",
                0
            )
        )

        verify(view).showDataError()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTaskResponse unsuccessful, shows error`() {
        presenter.onAddTaskResponse(
            Response.error(INVALID_CODE, ResponseBody.create(null, ""))
        )

        verify(view).showDataError()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTaskResponse successful, bad code`() {
        presenter.onAddTaskResponse(
            Response.success(CODE_NO_BODY, emptyBackendTask())
        )

        verify(view).onTaskAddFailed()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTaskResponse successful, null body`() {
        presenter.onAddTaskResponse(
            Response.success(CODE_NO_BODY, null as BackendTask?) // we have to be explicit about the type
        )

        verify(view).onTaskAddFailed()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTaskResponse successful, valid body`() {
        presenter.onAddTaskResponse(
            Response.success(VALID_CODE, emptyBackendTask())
        )

        verify(view).onTaskAdded(any())
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTaskResponseCode invalid code`() {
        presenter.onAddTaskResponseCode(CODE_NO_BODY, emptyBackendTask())

        verify(view).onTaskAddFailed()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `onAddTaskResponseCode valid code and body`() {
        presenter.onAddTaskResponseCode(VALID_CODE, emptyBackendTask())

        verify(view).onTaskAddFailed()
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `handleOkResponse null body`() {
        presenter.handleOkResponse(null)

        verifyZeroInteractions(view, interactor)
    }

    @Test
    fun `handleOkResponse valid body`() {
        presenter.handleOkResponse(emptyBackendTask())

        verify(view).onTaskAdded(any())
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `handleSomethingWentWrong shows error`() {
        presenter.handleSomethingWentWrong()

        verify(view).onTaskAddFailed()
        verifyZeroInteractions(interactor)
    }
}