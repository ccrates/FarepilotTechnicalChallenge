package com.conradcrates.farepilottechnicalchallenge;

import android.graphics.Bitmap;

import com.conradcrates.farepilottechnicalchallenge.util.Utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Conrad on 09/10/2017.
 */

public class UtilsTests {

    @Test
    public void testSquareBitmapResizedCorrectly(){
        int dimen = 500;

        Bitmap bmp = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        Bitmap comp = Utils.resizeBitmap(bmp, dimen);

        Assert.assertEquals(dimen, comp.getHeight());
        Assert.assertEquals(dimen, comp.getWidth());
    }

    @Test
    public void testPortraitBitmapResizedCorrectly(){
        float initHeight = 1280;
        float initWidth = 720;

        float finalHeight = 500;
        float finalWidth = finalHeight / (initHeight / initWidth);

        Bitmap bmp = Bitmap.createBitmap((int)initWidth, (int)initHeight, Bitmap.Config.ARGB_8888);
        Bitmap comp = Utils.resizeBitmap(bmp, (int)finalHeight);

        Assert.assertEquals((int)finalHeight, comp.getHeight());
        Assert.assertEquals((int)finalWidth, comp.getWidth());
    }

    @Test
    public void testLandscapeBitmapResizedCorrectly(){
        float initHeight = 720;
        float initWidth = 1280;

        float finalWidth = 500;
        float finalHeight = finalWidth * (initHeight / initWidth);

        Bitmap bmp = Bitmap.createBitmap((int)initWidth, (int)initHeight, Bitmap.Config.ARGB_8888);
        Bitmap comp = Utils.resizeBitmap(bmp, (int)finalWidth);

        Assert.assertEquals((int)finalHeight, comp.getHeight());
        Assert.assertEquals((int)finalWidth, comp.getWidth());
    }
}