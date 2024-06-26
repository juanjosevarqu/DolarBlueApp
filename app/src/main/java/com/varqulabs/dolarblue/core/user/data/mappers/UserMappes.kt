package com.varqulabs.dolarblue.core.user.data.mappers

import com.varqulabs.dolarblue.core.user.data.model.UserSerializable
import com.varqulabs.dolarblue.core.user.domain.model.User

//Mapper para el guardado de usuario en dataStore como String
fun User.toUserSerializable(): UserSerializable {
    return UserSerializable(
        token = token,
        userName = userName,
        email = email
    )
}

//Mapper para convertir el usuario guardado en String en un objecto User
fun UserSerializable.toUser(): User{
    return User(
        token = token,
        userName = userName,
        email = email
    )
}