package ru.study.spriteanimation

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView as SurfaceView

class MainGamePanel(context: Context) : SurfaceView(context), SurfaceHolder.Callback  {
    private val mainThread: MainThread
    val elain = ElainAnimation(context,
            BitmapFactory.decodeResource(resources, R.drawable.walk_elaine),
            10, 50,
            5, 5)

    init {
        holder.addCallback(this)
        mainThread = MainThread(holder, this)
        isFocusable = true

    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        mainThread.running = true
        mainThread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        var retry = true
        while (retry) {
            mainThread.join()
            retry = false
        }
    }

    public override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.WHITE)
        elain.drawElain(canvas)
    }

    fun update() {
        elain.update(System.currentTimeMillis())
    }
}