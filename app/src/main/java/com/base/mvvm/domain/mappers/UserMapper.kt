package com.base.mvvm.domain.mappers

import com.base.mvvm.domain.entities.UserEntity
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.models.User

class UserMapper : BaseMapper<UserEntity?, User?>() {
    override fun createObject(): User? {
        return null
    }

    override fun createEntity(): UserEntity? {
        return null
    }

    @Throws(MapperException::class)
    override fun defineObject(`object`: User?): User? {
        return null
    }

    @Throws(MapperException::class)
    override fun defineEntity(entity: UserEntity?): UserEntity? {
        return null
    }
}