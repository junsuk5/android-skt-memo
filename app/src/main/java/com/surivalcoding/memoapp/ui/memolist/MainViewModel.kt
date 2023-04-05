package com.surivalcoding.memoapp.ui.memolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surivalcoding.memoapp.data.model.Memo
import com.surivalcoding.memoapp.data.repository.MemoRepository
import com.surivalcoding.memoapp.data.repository.MemoryMemoRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainUiState(
    val items: List<Memo> = emptyList(),
    val selectedMemo: Memo? = null,
)

class MainViewModel(
    private val repository: MemoRepository = MemoryMemoRepositoryImpl()
) : ViewModel() {

    private var _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    init {
        loadMemo()
    }

    fun insertMemo(content: String) {
        viewModelScope.launch {
            repository.insert(
                Memo(
                    id = 100,
                    content = content,
                )
            )
        }
        loadMemo()
    }

    fun selectMemo(memo: Memo) {
        _state.update {
            it.copy(selectedMemo = memo)
        }
    }

    fun updateMemo(content: String) {
        viewModelScope.launch {
            state.value.selectedMemo?.let {
                repository.update(
                    it.copy(content = content)
                )
            }
        }
        loadMemo()

        _state.update {
            it.copy(selectedMemo = null)
        }
    }

    fun deleteMemo(memo: Memo) {
        viewModelScope.launch {
            repository.delete(memo)
        }
        loadMemo()
    }

    private fun loadMemo() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    items = repository.findAll()
                )
            }
        }
    }

}