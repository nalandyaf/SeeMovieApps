package com.base.mvvm.data.remote

import com.base.mvvm.domain.entities.BaseObjectEntity
import io.reactivex.Single
import java.util.*
import javax.inject.Singleton

@Singleton
abstract class BaseRepository<E : BaseObjectEntity?> {
    protected var entities: List<E> = arrayListOf()
    protected var remoteAPI: RemoteAPI = RetrofitFactory.instance!!.remoteAPI
    abstract fun get(): Single<List<E>?>?
    abstract fun getById(id: Int): Single<List<E>?>?

    companion object {
        private val instance: BaseRepository<*>? = null
    }

}