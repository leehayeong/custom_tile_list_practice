package com.example.customtilelistpractice.tile.model

/**
 *  TileEntity.kt
 *
 *  Created by Hayeong Lee on 2022/03/02
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

enum class TileType {
    SQUARE, RECTANGLE
}

open class TileEntity(
    val type: TileType,
    val widthSpan: Int,
    val title: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TileEntity) return false

        if (type != other.type) return false
        if (widthSpan != other.widthSpan) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + widthSpan
        result = 31 * result + title.hashCode()
        return result
    }
}

class SquareTile(
    title: String
) : TileEntity(
    TileType.SQUARE,
    1,
    title
)

class RectangleTile(
    title: String
) : TileEntity(
    TileType.RECTANGLE,
    3,
    title
)
