package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.mappers

import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model.User

fun User.toDomain(): yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.model.User {
    return yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.model.User(
        id = this.id ?: 0,
        name = this.name ?: "",
        surname = this.surname ?: "",
        email = this.email ?: "",
        password = this.password ?: "",
        address = this.address ?: "",
        phone = this.phone ?: "",
        img_path = this.img_path ?: "",
        barcode = this.barcode ?: ""
    )
}

fun yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.model.User.toData(): User {
    return User(
        id = this.id,
        name = this.name,
        surname = this.surname,
        email = this.email,
        password = this.password,
        address = this.address,
        phone = this.phone,
        img_path = this.img_path,
        barcode = this.barcode
    )
}