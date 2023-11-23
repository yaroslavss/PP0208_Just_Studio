package com.yara.juststudioapp.ui.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yara.juststudioapp.model.Feedback

class FeedbackViewModel : ViewModel() {

    private val feedbackList = listOf(
        Feedback(
            name = "Сергей Иванов",
            date = "22.11.2023",
            rating = 4.5,
            text = "Значимость этих проблем настолько очевидна, что начало повседневной работы по формированию позиции позволяет оценить значение!!!"
        ),
        Feedback(
            name = "Сергей Иванов",
            date = "22.11.2023",
            rating = 4.5,
            text = "Значимость этих проблем настолько очевидна, что начало повседневной работы по формированию позиции позволяет оценить значение!!!"
        ),
        Feedback(
            name = "Сергей Иванов",
            date = "22.11.2023",
            rating = 4.5,
            text = "Значимость этих проблем настолько очевидна, что начало повседневной работы по формированию позиции позволяет оценить значение!!!"
        )
    )
    private val _feedbackListLiveData = MutableLiveData<List<Feedback>>()
    val feedbackListLiveData: LiveData<List<Feedback>> = _feedbackListLiveData

    init {
        _feedbackListLiveData.postValue(feedbackList)
    }
}