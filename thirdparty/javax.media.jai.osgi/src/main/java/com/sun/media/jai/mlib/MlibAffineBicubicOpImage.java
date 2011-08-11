/*
 * $RCSfile: MlibAffineBicubicOpImage.java,v $
 *
 * Copyright (c) 2005 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2005/12/15 18:35:45 $
 * $State: Exp $
 */
package com.sun.media.jai.mlib;
import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderedImageFactory;
import java.awt.geom.AffineTransform;
import javax.media.jai.AreaOpImage;
import javax.media.jai.BorderExtender;
import javax.media.jai.ImageLayout;
import javax.media.jai.Interpolation;
import javax.media.jai.InterpolationBicubic;
import javax.media.jai.InterpolationBicubic2;
import javax.media.jai.KernelJAI;
import javax.media.jai.OpImage;
import java.util.Map;
import com.sun.medialib.mlib.*;

// import com.sun.media.jai.test.OpImageTester;

/**
 * An OpImage class to perform Bicubic AffineTransform
 * on a source image.
 *
 */
public class MlibAffineBicubicOpImage extends MlibAffineOpImage {

    /**
     * Creates a MlibAffineNearestOpImage given a ParameterBlock containing the
     * image source and the AffineTransform.  The image dimensions are derived
     * from the source image.  The tile grid layout, SampleModel, and
     * ColorModel may optionally be specified by an ImageLayout
     * object.
     *
     * @param source a RenderedImage.
     * @param extender a BorderExtender, or null.
     * @param layout an ImageLayout optionally containing the tile grid layout,
     *        SampleModel, and ColorModel, or null.
     * @param tr the AffineTransform.
     * @param interp the Interpolation to be used (Nearest-Neighbour)
     * @param backgroundValues the user provided background colors
     */
    public MlibAffineBicubicOpImage(RenderedImage source,
                                    BorderExtender extender,
                                    Map config,
                                    ImageLayout layout,
                                    AffineTransform tr,
                                    Interpolation interp,
                                    double[] backgroundValues) {
	super(source,
              layout,
              config,
              extender,
              tr,
              interp,
              backgroundValues);
    }

    /**
     * Performs bicubic affine transformation on a specified
     * rectangle.  The sources are cobbled and extended.
     *
     * @param sources an array of source Rasters, guaranteed to provide all
     *                necessary source data for computing the output.
     * @param dest a WritableRaster tile containing the area to be computed.
     * @param destRect the rectangle within dest to be processed.
     */
    protected void computeRect(Raster[] sources,
                               WritableRaster dest,
                               Rectangle destRect) {

        // The default interpolation for this class is Bicubic
        int mlibInterpType = Constants.MLIB_BICUBIC;

        // Unless a Bicubic2 object is passed in as the interpolation
        if (interp instanceof InterpolationBicubic2) {
            mlibInterpType = Constants.MLIB_BICUBIC2;
        }

        Raster source = sources[0];
        Rectangle srcRect = source.getBounds();

        int formatTag = MediaLibAccessor.findCompatibleTag(sources,dest);

        MediaLibAccessor srcAccessor =
            new MediaLibAccessor(source,srcRect,formatTag);
        MediaLibAccessor dstAccessor =
            new MediaLibAccessor(dest,destRect,formatTag);

        //
        // The AffineTransform needs to be readjusted as per the
        // location of the current source & destination rectangle
        //

        // Clone the global transform so as not to write to an instance
        // variable as this method may be called from different threads.
        double[] medialib_tr = (double[])this.medialib_tr.clone();

        medialib_tr[2] = m_transform[0] * srcRect.x +
            m_transform[1] * srcRect.y +
            m_transform[2] -
            destRect.x;
        medialib_tr[5] = m_transform[3] * srcRect.x +
            m_transform[4] * srcRect.y +
            m_transform[5] -
            destRect.y;

	mediaLibImage srcML[], dstML[];

        switch (dstAccessor.getDataType()) {
        case DataBuffer.TYPE_BYTE:
        case DataBuffer.TYPE_USHORT:
        case DataBuffer.TYPE_SHORT:
        case DataBuffer.TYPE_INT:
	    srcML = srcAccessor.getMediaLibImages();
            dstML = dstAccessor.getMediaLibImages();
            if (setBackground)
                Image.Affine2(dstML[0],
                              srcML[0],
                              medialib_tr,
                              mlibInterpType,
                              Constants.MLIB_EDGE_DST_NO_WRITE,
                              intBackgroundValues);
            else
                Image.Affine(dstML[0],
                             srcML[0],
                             medialib_tr,
                             mlibInterpType,
                             Constants.MLIB_EDGE_DST_NO_WRITE);
            MlibUtils.clampImage(dstML[0], getColorModel());
            break;

        case DataBuffer.TYPE_FLOAT:
        case DataBuffer.TYPE_DOUBLE:
	    srcML = srcAccessor.getMediaLibImages();
            dstML = dstAccessor.getMediaLibImages();

            if (setBackground)
                Image.Affine2_Fp(dstML[0],
                                 srcML[0],
                                 medialib_tr,
                                 mlibInterpType,
                                 Constants.MLIB_EDGE_DST_NO_WRITE,
                                 backgroundValues);
            else
                Image.Affine_Fp(dstML[0],
                                srcML[0],
                                medialib_tr,
                                mlibInterpType,
                                Constants.MLIB_EDGE_DST_NO_WRITE);
            break;

        default:
            String className = this.getClass().getName();
            throw new RuntimeException(JaiI18N.getString("Generic2"));
        }

        if (dstAccessor.isDataCopy()) {
            dstAccessor.copyDataToRaster();
        }
    }

//     public static OpImage createTestImage(OpImageTester oit) {
// 	Interpolation interp = new InterpolationBicubic(8);
//         AffineTransform tr = new AffineTransform(0.707107,
//                                                  -0.707106,
//                                                  0.707106,
//                                                  0.707107,
//                                                  0.0,
//                                                  0.0);
//         return new MlibAffineBicubicOpImage(oit.getSource(), null, null,
//                                             new ImageLayout(oit.getSource()),
//                                             tr,
//                                             interp, null);
//     }

//     // Calls a method on OpImage that uses introspection, to make this
//     // class, discover it's createTestImage() call, call it and then
//     // benchmark the performance of the created OpImage chain.
//     public static void main (String args[]) {
//         String classname = "com.sun.media.jai.mlib.MlibAffineBicubicOpImage";
//         OpImageTester.performDiagnostics(classname, args);
//     }
}
