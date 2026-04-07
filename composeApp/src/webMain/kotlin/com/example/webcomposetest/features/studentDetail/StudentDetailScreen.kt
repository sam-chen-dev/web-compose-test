package com.example.webcomposetest.features.studentDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.webcomposetest.models.Student
import com.example.webcomposetest.utils.IconButton
import compose.icons.TablerIcons
import compose.icons.tablericons.Check
import compose.icons.tablericons.Trash
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun StudentDetailScreen(
    id: Long,
    onBackTrigger: () -> Unit
) {
    val viewModel: StudentDetailViewModel = koinViewModel(parameters = { parametersOf(id) })
    val student by viewModel.student.collectAsStateWithLifecycle()
    val nameState = viewModel.nameState
    val onSaveClick: () -> Unit = { viewModel.saveStudent() }
    val onDeleteClick: () -> Unit = { viewModel.deleteStudent() }
    val isShowDelete = id != -1L

    LaunchedEffect(Unit) {
        viewModel.isSavedSuccessfully.collect { isSavedSuccessfully ->
            if (isSavedSuccessfully) {
                onBackTrigger()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isUpdateSuccessfully.collect { isUpdateSuccessfully ->
            if (isUpdateSuccessfully) {
                onBackTrigger()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isDeletedSuccessfully.collect { isDeletedSuccessfully ->
            if (isDeletedSuccessfully) {
                onBackTrigger()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar(onSaveClick, onDeleteClick, isShowDelete)
        Content(student, nameState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(onSaveClick: () -> Unit, onDeleteClick: () -> Unit, isShowDelete: Boolean) {
    TopAppBar(
        title = { Text("Student Detail") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            IconButton(TablerIcons.Check, "Save", onSaveClick)

            if (isShowDelete) {
                IconButton(TablerIcons.Trash, "Delete", onDeleteClick)
            }
        }
    )
}

@Composable
private fun Content(student: Student?, nameState: TextFieldState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NameTextField(nameState)
    }
}

@Composable
private fun NameTextField(state: TextFieldState) {
    OutlinedTextField(
        state = state,
        label = { Text("Name") },
        labelPosition = TextFieldLabelPosition.Attached(true)
    )
}