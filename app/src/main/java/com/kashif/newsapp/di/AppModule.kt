package com.kashif.newsapp.di

import android.app.Application
import androidx.compose.ui.unit.Constraints
import androidx.room.Room
import com.kashif.newsapp.data.local.NewsDao
import com.kashif.newsapp.data.local.NewsDatabase
import com.kashif.newsapp.data.local.NewsTypeConvertor
import com.kashif.newsapp.data.manager.LocalUserManagerImpl
import com.kashif.newsapp.data.remote.NewsApi
import com.kashif.newsapp.data.repository.NewsRepositoryImpl
import com.kashif.newsapp.domain.manager.LocalUserManager
import com.kashif.newsapp.domain.repository.NewsRepository
import com.kashif.newsapp.domain.useCases.app_entry.AppEntryUseCases
import com.kashif.newsapp.domain.useCases.app_entry.ReadAppEntry
import com.kashif.newsapp.domain.useCases.app_entry.SaveAppEntry
import com.kashif.newsapp.domain.useCases.news.DeleteArticle
import com.kashif.newsapp.domain.useCases.news.GetNews
import com.kashif.newsapp.domain.useCases.news.NewsUseCases
import com.kashif.newsapp.domain.useCases.news.SearchNews
import com.kashif.newsapp.domain.useCases.news.SelectArticle
import com.kashif.newsapp.domain.useCases.news.SelectArticles
import com.kashif.newsapp.domain.useCases.news.UpdateArticle
import com.kashif.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager( context: Application):LocalUserManager = LocalUserManagerImpl(context)

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager)=
         AppEntryUseCases(readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi,newsDao: NewsDao):NewsRepository=NewsRepositoryImpl(newsApi,newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository):NewsUseCases{
        return NewsUseCases(getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            updateArticle = UpdateArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constants.NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor()).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase):NewsDao=newsDatabase.newsDao

}