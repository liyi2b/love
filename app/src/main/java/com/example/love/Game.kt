package com.example.love

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Game(val scope: CoroutineScope) {

    var counter = 0
    fun Play() {
        scope.launch {
            counter = 0
            while (counter < 1000) {
                delay(40)  // 控制遊戲更新頻率
                counter++
            }
        }
    }
}