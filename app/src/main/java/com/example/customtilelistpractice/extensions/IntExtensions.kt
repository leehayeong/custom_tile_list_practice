package com.example.customtilelistpractice.extensions

import android.content.res.Resources

/**
 *  IntExtensions.kt
 *
 *  Created by Hayeong Lee on 2022/03/03
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
