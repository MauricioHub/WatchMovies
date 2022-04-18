package com.example.watchmovies.ui.animations

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View

class AnimationManager {

    fun animate(view: View){
        translator(view)
        scalier(view)
        rotator(view)
    }

    private fun translator(view: View){
        val animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y,300f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun rotator(view: View) {
        val animator  = ObjectAnimator.ofFloat(view, View.ROTATION,-2160f,0f)
        animator.duration = 1000L
        animator.start()
    }

    private fun scalier(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,0.33f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,0.3f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            view,scaleX,scaleY
        )
        animator.duration = 1000L
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }
}