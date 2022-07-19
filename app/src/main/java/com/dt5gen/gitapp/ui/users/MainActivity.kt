package com.dt5gen.gitapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dt5gen.gitapp.app
import com.dt5gen.gitapp.databinding.ActivityMainBinding
import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: UsersAdapter = UsersAdapter {
        viewModel.onProfileClick(it)
    }

    @Inject
    lateinit var usersRepo: UsersRepo

    private val viewModel: UsersViewModel by lazy { UsersViewModel(usersRepo) }

    private var viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        app.appComponent.inject(this)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {

        viewModelDisposable.addAll(
            viewModel.progressObservableData.subscribe { showProgress(it) },
            viewModel.usersObservableData.subscribe { showUsers(it) },
            viewModel.errorsObservableData.subscribe { showError(it) },
            viewModel.openObservableUserProfileData.subscribe { openAboutUserScreen() }
        )
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun openAboutUserScreen() {
        startActivity(Intent(this, AboutUserActivity::class.java))
    }


    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()

    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }
}