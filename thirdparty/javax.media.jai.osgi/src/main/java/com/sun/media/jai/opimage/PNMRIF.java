/*
 * $RCSfile: PNMRIF.java,v $
 *
 * Copyright (c) 2005 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.1 $
 * $Date: 2005/02/11 04:56:39 $
 * $State: Exp $
 */
package com.sun.media.jai.opimage;
import java.io.InputStream;
import java.awt.RenderingHints;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderedImageFactory;
import javax.media.jai.NullOpImage;
import javax.media.jai.OpImage;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;

/**
 * A <code>RIF</code> supporting the "PNM" operation in the
 * rendered image layer.
 *
 * @since EA2
 * @see javax.media.jai.operator.PNMDescriptor
 */
public class PNMRIF implements RenderedImageFactory {

    /** Constructor. */
    public PNMRIF() {}

    /**
     * Creates a <code>RenderedImage</code> representing the contents
     * of a PNM-encoded image.
     *
     * @param paramBlock A <code>ParameterBlock</code> containing the PNM
     *        <code>SeekableStream</code> to read.
     * @param renderHints An instance of <code>RenderingHints</code>,
     *        or null.
     */
    public RenderedImage create(ParameterBlock paramBlock,
                                RenderingHints renderHints) {
        return CodecRIFUtil.create("pnm", paramBlock, renderHints);
    }
}
