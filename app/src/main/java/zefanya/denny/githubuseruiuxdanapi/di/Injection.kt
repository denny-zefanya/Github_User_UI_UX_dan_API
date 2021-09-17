package zefanya.denny.githubuseruiuxdanapi.di

import android.content.Context
import zefanya.denny.githubuseruiuxdanapi.data.Repository
import zefanya.denny.githubuseruiuxdanapi.data.source.local.LocalDataSource
import zefanya.denny.githubuseruiuxdanapi.data.source.local.room.GithubUserDatabase
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): Repository {
        val githubUserDatabase = GithubUserDatabase.getInstance(context)
        val localDataSource = LocalDataSource(githubUserDatabase.githubuserDao())
        val remoteDataSource = RemoteDataSource.getInstance()

        return Repository(localDataSource, remoteDataSource)
    }
}