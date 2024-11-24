package com.example.proyectofinal_prototipov1

import android.graphics.RectF

interface OnSelectObject {
    fun validateDrop(viewRect: RectF, targetRect: RectF): Boolean
}