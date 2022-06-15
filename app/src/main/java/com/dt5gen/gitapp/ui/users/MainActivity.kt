package com.dt5gen.gitapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dt5gen.gitapp.app
import com.dt5gen.gitapp.databinding.ActivityMainBinding
import com.dt5gen.gitapp.domain.entities.UserEntity

class MainActivity : AppCompatActivity(), UsersContract.View {

    private lateinit var binding: ActivityMainBinding
    private val adapter: UsersAdapter = UsersAdapter()

    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        presenter = extractPresenter()
        presenter.attach(this)
    }

    private fun extractPresenter(): UsersContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersContract.Presenter
            ?: UsersPresenter(app.users2Repo)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }


    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            presenter.onRefresh()
        }
        initRecyclerView()
        showProgress(false)


    }


    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()

    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter

    }
}