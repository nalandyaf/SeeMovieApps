package com.base.mvvm.data.remote

import com.base.mvvm.domain.entities.requests.LoginRequest
import com.base.mvvm.domain.entities.requests.RegistrationRequest
import com.base.mvvm.domain.entities.response.LoginResponse
import com.base.mvvm.domain.entities.response.RegistrationResponse
import io.reactivex.Single

class UserRepository private constructor() : BaseRepository<LoginResponse?>() {

    fun login(request: LoginRequest?): Single<LoginResponse?>? {
        return remoteAPI.login(request)
    }

    fun registration(request: RegistrationRequest?): Single<RegistrationResponse?>? {
        return remoteAPI.registration(request)
    }

    companion object {
        @kotlin.jvm.JvmStatic
        var instance: UserRepository? = null
            get() {
                if (field == null) {
                    field = UserRepository()
                }
                return field
            }
            private set
    }

    override fun get(): Single<List<LoginResponse?>?>? {
        return null
    }

    override fun getById(id: Int): Single<List<LoginResponse?>?>? {
        return null
    }

}