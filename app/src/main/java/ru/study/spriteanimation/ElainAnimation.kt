package ru.study.spriteanimation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class ElainAnimation(
        context: Context,
        private val bitmap: Bitmap,
        private val spriteX: Int, // X координата спрайта (верхний левый угол картинки)
        private val spriteY: Int, // Y координата спрайта (верхний левый угол картинки)
        fps: Int,
        private val frameCount: Int) // Число кадров в анимации
    : View(context) {
    private val spriteWidth = bitmap.width / frameCount // Ширина спрайта (одного кадра)
    private val spriteHeight = bitmap.height // Высота спрайта
    private val sourceRect = Rect(0, 0, spriteWidth, spriteHeight) // Прямоугольная область в bitmap, которую нужно нарисовать
    private var currentFrame = 0 // Текущий кадр
    private var frameTicker = 0L // Время обновления последнего кадра
    private val framePeriod = 1000 / fps // Сколько миллисекунд должно пройти перед сменой кадра (1000/fps)

    fun update(gameTime: Long) {
        if (gameTime > frameTicker + framePeriod) {
            frameTicker = gameTime
            currentFrame++
            if (currentFrame >= frameCount) {
                currentFrame = 0
            }
        }
        sourceRect.left = currentFrame * spriteWidth
        sourceRect.right = sourceRect.left + spriteWidth
    }

    fun drawElain(canvas: Canvas?) {
        val destRect = Rect(spriteX, spriteY,spriteX + spriteWidth, spriteY + spriteHeight)
        canvas?.drawBitmap(bitmap, sourceRect, destRect, null)
        canvas?.drawBitmap(bitmap, 20f, 200f, null)
        val paint = Paint()
        paint.setARGB(50, 0, 255, 0)
        canvas?.drawRect(20f + (currentFrame * destRect.width()),
                200f,
                20f + (currentFrame * destRect.width()) + destRect.width(),
                200f + destRect.height(), paint)
    }

    override fun onDraw(canvas: Canvas?) {
        drawElain(canvas)
    }
}