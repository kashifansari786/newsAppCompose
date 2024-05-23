package com.kashif.newsapp.domain.useCases.news

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
data class NewsUseCases(val getNews:GetNews,
                        val searchNews: SearchNews,
    val updateArticle: UpdateArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle)
