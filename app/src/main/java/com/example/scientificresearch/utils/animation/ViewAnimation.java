package com.example.scientificresearch.utils.animation;

import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class ViewAnimation extends AnimatorListenerAdapter{
//        public Boolean rotateFab(View v, Boolean isRotate)  {
//            animate = AnimatorListenerAdapter;
//            v.animate().setDuration(200)
//                    .setListener(animate)
//            .rotation(if (isRotate) 135f else 0f)
//
//            return isRotate
//        }
//
//        fun init(v: View) {
//            with(v) {
//                gone()
//                translationY = v.height.toFloat()
//                alpha = 0f
//            }
//        }
//
//        fun showAnimation(v: View, isShow: Boolean) {
//            if (isShow) {
//                showIn(v)
//            } else {
//                showOut(v)
//            }
//        }
//
//        private fun showOut(v: View) {
//            with(v) {
//                visiable()
//                alpha = 1f
//                translationY = 0f
//                animate()
//                        .setDuration(200)
//                        .translationY(v.height.toFloat())
//                        .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator?) {
//                        v.gone()
//                        super.onAnimationEnd(animation)
//                    }
//                })
//                .alpha(0f)
//                        .start()
//            }
//        }
//
//        private fun showIn(v: View) {
//            with(v) {
//                visiable()
//                alpha = 0f
//                translationY = v.height.toFloat()
//                animate()
//                        .setDuration(200)
//                        .translationY(0f)
//                        .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator?) {
//                        v.visiable()
//                        super.onAnimationEnd(animation)
//                    }
//                })
//                .alpha(1f)
//                        .start()
//            }
//        }
}
