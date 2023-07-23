package com.exequielvr.myappsprint.model


fun fromInternetToItemsEntity(itemsList: List<Items>): List<ItemsEntity> {

    return itemsList.map {
        ItemsEntity(
            id = it.id,
            name = it.name,
            price = it.price,
            image = it.image
        )
    }
}

fun fromInternetToItemDetailEntity(item: ItemDetail): ItemsDetailEntity {

    return ItemsDetailEntity(
        id = item.id,
        name = item.name,
        price = item.price,
        image = item.image
    )
}












