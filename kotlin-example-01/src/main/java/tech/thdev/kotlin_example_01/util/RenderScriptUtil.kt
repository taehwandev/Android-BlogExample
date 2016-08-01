package tech.thdev.kotlin_example_01.util

import android.content.Context
import android.graphics.Bitmap
import android.support.v8.renderscript.Allocation
import android.support.v8.renderscript.Element
import android.support.v8.renderscript.RenderScript
import android.support.v8.renderscript.ScriptIntrinsicBlur

/**
 * Created by Tae-hwan on 8/1/16.
 *
 * Android RenderScript util.
 */

/**
 * Create blur half size image create
 */
fun Bitmap?.createBlurImage(context: Context, radius: Float = 25.0f): Bitmap? {
    return this?.let {
        var temp = radius
        if (radius < 0) {
            temp = 0f
        } else if (radius > 25.0f) {
            temp = 25.0f
        }

        val bitmap: Bitmap = Bitmap.createScaledBitmap(this, width / 2, height / 2, false)

        val renderScript: RenderScript = RenderScript.create(context)

        val blurInput: Allocation = Allocation.createFromBitmap(renderScript, bitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
        val blurOutput: Allocation = Allocation.createTyped(renderScript, blurInput.type)

        val blur: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))

        // Set the radius of the Blur. Supported range 0 < radius <= 25
        blur.setRadius(temp)
        blur.setInput(blurInput)
        blur.forEach(blurOutput)

        blurOutput.copyTo(bitmap)
        renderScript.destroy()

        return bitmap
    }
}