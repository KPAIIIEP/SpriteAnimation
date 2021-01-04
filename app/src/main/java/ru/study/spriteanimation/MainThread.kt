package ru.study.spriteanimation

import android.graphics.Canvas
import android.view.SurfaceHolder

class MainThread(val surfaceHolder: SurfaceHolder, val mainGamePanel: MainGamePanel) : Thread() {
    var running: Boolean = false

    override fun run() {
        var canvas: Canvas?
        while (running) {
            canvas = null
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    mainGamePanel.update()
                    mainGamePanel.onDraw(canvas)
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }
}