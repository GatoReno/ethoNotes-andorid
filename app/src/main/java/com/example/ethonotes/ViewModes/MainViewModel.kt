package com.example.ethonotes.ViewModes

import androidx.lifecycle.ViewModel

public class MainViewModel(
    private val repo : IMainRepository
) : ViewModel(){

    fun callDCC2(){
        repo.callNetWokDCC2()
    }
    
}


public class MainRepositoryImp : IMainRepository{
    override fun callNetWokDCC2() {

    }

}

interface IMainRepository {
    fun callNetWokDCC2()
}