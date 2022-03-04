package com.example.customtilelistpractice.model

import android.view.animation.RotateAnimation
import com.example.customtilelistpractice.bigShakingAnimation
import com.example.customtilelistpractice.extensions.dp
import com.example.customtilelistpractice.smallShakingAnimation

/**
 *  TileEntity.kt
 *
 *  Created by Hayeong Lee on 2022/03/02
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

data class TileEntity(
    val id: String,
    val name: String,
    val widthSpan: Int,
    val heightSpan: Int,
) {
    val shakingAnimation: RotateAnimation = if (widthSpan == 1) bigShakingAnimation else smallShakingAnimation
    val heightDp: Int = if (heightSpan == 1) 100.dp else 200.dp
}
