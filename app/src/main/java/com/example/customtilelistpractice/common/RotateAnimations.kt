package com.example.customtilelistpractice.common

import android.view.animation.Animation
import android.view.animation.RotateAnimation

/**
 *  RotateAnimations.kt
 *
 *  Created by Hayeong Lee on 2022/03/03
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

val smallShakingAnimation =
    RotateAnimation(1f, -1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        duration = 150
        repeatMode = Animation.REVERSE
        repeatCount = Animation.INFINITE
    }

val bigShakingAnimation =
    RotateAnimation(1.5f, -1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        duration = 150
        repeatMode = Animation.REVERSE
        repeatCount = Animation.INFINITE
    }