/*
 * $RCSfile: MlibRescaleRIF.java,v $
 *
 * Copyright (c) 2005 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.1 $
 * $Date: 2005/02/11 04:56:03 $
 * $State: Exp $
 */
package com.sun.media.jai.mlib;
import java.awt.RenderingHints;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderedImageFactory;
import javax.media.jai.ImageLayout;
import java.util.Map;
import com.sun.media.jai.opimage.RIFUtil;

/**
 * A <code>RIF</code> supporting the "Rescale" operation in the
 * rendered image mode using MediaLib.
 *
 * @see javax.media.jai.operator.RescaleDescriptor
 * @see MlibRescaleOpImage
 *
 */
public class MlibRescaleRIF implements RenderedImageFactory {

    /** Constructor. */
    public MlibRescaleRIF() {}

    /**
     * Creates a new instance of <code>MlibRescaleOpImage</code> in
     * the rendered image mode.
     *
     * @param args  The source image to be rescaled and the constants.
     * @param hints  May contain rendering hints and destination image layout.
     */
    public RenderedImage create(ParameterBlock args,
                                RenderingHints hints) {
        /* Get ImageLayout and TileCache from RenderingHints. */
        ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
        

        if (!MediaLibAccessor.isMediaLibCompatible(args, layout) ||
            !MediaLibAccessor.hasSameNumBands(args, layout)) {
            return null;
        }

	return new MlibRescaleOpImage(args.getRenderedSource(0),
                                      hints, layout,
                                      (double[])args.getObjectParameter(0),
                                      (double[])args.getObjectParameter(1));
    }
}
