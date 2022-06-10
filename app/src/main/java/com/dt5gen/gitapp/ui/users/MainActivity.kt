package com.dt5gen.gitapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dt5gen.gitapp.App
import com.dt5gen.gitapp.app
import com.dt5gen.gitapp.data.FakeUsersRepoImpl
import com.dt5gen.gitapp.databinding.ActivityMainBinding
import com.dt5gen.gitapp.domain.UserEntity
import com.dt5gen.gitapp.domain.UsersRepo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: UsersAdapter = UsersAdapter()
    private val usersRepo: UsersRepo by lazy { app.users2Repo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            loadData()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun loadData() {
        Toast.makeText(this, "Работает!", Toast.LENGTH_SHORT).show()
        showProgress(true)

        usersRepo.getUsers(
            onSuccess = {
                showProgress(false)
                onDataLoaded(it)
            },
            onError = {
                showProgress(false)
                onError(it)
            }
        )
    }

    private fun onDataLoaded(data: List<UserEntity>){
        adapter.setData(data)
    }

    private fun onError(throwable: Throwable){
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()

    }


    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter

    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

}