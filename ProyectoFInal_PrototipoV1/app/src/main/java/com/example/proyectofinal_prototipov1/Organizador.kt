package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Organizador(context: Context, attrs: AttributeSet) : View(context, attrs), OnSelectObject {

    private val paint = Paint()
    private val rects = mutableListOf<RectF>() // Rectángulos de los océanos
    private val targetRects = mutableListOf<RectF>() // Rectángulos de los espacios de destino
    private val oceanNames = mutableListOf<String>() // Nombres de los océanos
    private val randomizedNames = mutableListOf<String>() // Nombres aleatorios para los rectángulos
    private var draggingIndex: Int? = null // Índice del océano que está siendo arrastrado
    private var originalPosition: PointF? = null // Posición original del océano arrastrado
    private var lockedRects = mutableSetOf<Int>() // Índices de rectángulos bloqueados
    private var allCorrect = false // Variable para verificar si todos los océanos están bien organizados

    init {
        // Inicializar los rectángulos (4 océanos y 4 posiciones de destino)
        rects.add(RectF(100f, 800f, 300f, 1000f)) // Océano 1 (abajo)
        rects.add(RectF(350f, 800f, 550f, 1000f)) // Océano 2 (abajo)
        rects.add(RectF(600f, 800f, 800f, 1000f)) // Océano 3 (abajo)
        rects.add(RectF(850f, 800f, 1050f, 1000f)) // Océano 4 (abajo)

        // Posiciones de destino (arriba)
        targetRects.add(RectF(100f, 100f, 300f, 300f))
        targetRects.add(RectF(350f, 100f, 550f, 300f))
        targetRects.add(RectF(600f, 100f, 800f, 300f))
        targetRects.add(RectF(850f, 100f, 1050f, 300f))

        // Nombres de los océanos
        oceanNames.add("Pacífico")
        oceanNames.add("Atlántico")
        oceanNames.add("Índico")
        oceanNames.add("Ártico")

        // Mezclar los nombres de los océanos para asignarlos aleatoriamente a los rectángulos
        randomizedNames.addAll(oceanNames.shuffled())

        // Configuración de la pintura para los rectángulos
        paint.style = Paint.Style.FILL
        paint.textSize = 40f
        paint.color = Color.BLACK
        paint.textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibujar las posiciones de destino (arriba)
        paint.color = Color.GRAY
        for (target in targetRects) {
            canvas.drawRect(target, paint)
        }

        // Dibujar los rectángulos de los océanos (abajo)
        paint.color = Color.BLUE
        for (i in rects.indices) {
            canvas.drawRect(rects[i], paint)

            // Dibujar el nombre del océano dentro del rectángulo
            paint.color = Color.WHITE
            val centerX = rects[i].centerX()
            val centerY = rects[i].centerY() + 20f // Ajuste vertical para centrar el texto
            canvas.drawText(randomizedNames[i], centerX, centerY, paint)

            // Restaurar el color de pintura para el rectángulo
            paint.color = Color.BLUE
        }

        // Si todos los océanos están correctamente organizados, mostrar mensaje de felicitación
        if (allCorrect) {
            paint.color = Color.GREEN
            paint.textSize = 55f
            canvas.drawText("¡Felicitaciones! Eres Increíble :D", width / 2f, height / 2f, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (allCorrect) return true // No permitir más interacciones si ya está completo

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                draggingIndex = getTouchedRectIndex(event.x, event.y)
                draggingIndex?.let { index ->
                    // No permitir mover si ya está bloqueado
                    if (lockedRects.contains(index)) {
                        draggingIndex = null
                        return@let
                    }
                    // Guardar la posición original
                    originalPosition = PointF(rects[index].left, rects[index].top)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                draggingIndex?.let { index ->
                    // Mover el océano mientras se arrastra
                    val left = event.x - rects[index].width() / 2
                    val top = event.y - rects[index].height() / 2
                    rects[index].offsetTo(left, top)
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                draggingIndex?.let { index ->
                    // Validar si se soltó en una posición correcta
                    val targetIndex = oceanNames.indexOf(randomizedNames[index])
                    if (validateDrop(rects[index], targetRects[targetIndex])) {
                        // Posicionar el rectángulo en el centro de su posición de destino
                        val target = targetRects[targetIndex]
                        val left = target.left + (target.width() - rects[index].width()) / 2
                        val top = target.top + (target.height() - rects[index].height()) / 2
                        rects[index].offsetTo(left, top)
                        lockedRects.add(index) // Bloquear el rectángulo correctamente colocado
                    } else {
                        // Regresar el océano a su posición original
                        originalPosition?.let {
                            rects[index].offsetTo(it.x, it.y)
                        }
                    }
                    // Verificar si todos los océanos están bien organizados
                    allCorrect = rects.indices.all { validateDrop(rects[it], targetRects[oceanNames.indexOf(randomizedNames[it])]) }
                    invalidate()
                }
                draggingIndex = null
            }
        }
        return true
    }

    private fun getTouchedRectIndex(x: Float, y: Float): Int? {
        // Obtener el índice del océano que está siendo tocado
        rects.forEachIndexed { index, rect ->
            if (rect.contains(x, y)) {
                return index
            }
        }
        return null
    }

    override fun validateDrop(viewRect: RectF, targetRect: RectF): Boolean {
        // Validar si el océano está dentro de la posición de destino
        val tolerance = 50f
        val xCorrect = Math.abs(viewRect.left - targetRect.left) < tolerance
        val yCorrect = Math.abs(viewRect.top - targetRect.top) < tolerance
        return xCorrect && yCorrect
    }
}