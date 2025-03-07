package org.fossify.gallery.svg

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.caverock.androidsvg.SVG
import java.io.InputStream
import com.github.penfeizhou.animation.avif.AVIFDrawable
import com.github.penfeizhou.animation.avif.decode.AVIFDecoder

@GlideModule
class SvgModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // 保留现有的 SVG 支持
        registry.register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
                .append(InputStream::class.java, SVG::class.java, SvgDecoder())

        // 添加 AVIF 动画支持
        registry.append(Registry.BUCKET_ANIMATION, APNGDrawable::class.java, APNGDecoder.APNGDrawableDecoder(context))
    }

    override fun isManifestParsingEnabled() = false
}
