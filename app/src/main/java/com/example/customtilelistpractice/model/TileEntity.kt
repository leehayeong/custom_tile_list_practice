package com.example.customtilelistpractice.model

import android.view.animation.RotateAnimation
import com.example.customtilelistpractice.bigShakingAnimation
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
    val span: Int,
) {
    val shakingAnimation: RotateAnimation = if (span == 1) bigShakingAnimation else smallShakingAnimation
}
