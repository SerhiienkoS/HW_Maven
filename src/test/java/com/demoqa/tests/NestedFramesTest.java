package com.demoqa.tests;

import com.demoqa.pages.HomePage;
import com.demoqa.pages.NestedFramesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class NestedFramesTest extends TestBase {
    private NestedFramesPage frames;

    @BeforeMethod
    public void precondition() {
        frames = new HomePage(driver).getNestedFrames();
    }

    @Test
    public void leftFrameTest() {
        Assert.assertEquals(frames.getLeftText(), "LEFT");
    }

    @Test
    public void middleFrameTest() {
        Assert.assertEquals(frames.getMiddleText(), "MIDDLE");
    }

    @Test
    public void rightFrameTest() {
        Assert.assertEquals(frames.getRightText(), "RIGHT");
    }

    @Test
    public void bottomFrameTest() {
        Assert.assertEquals(frames.getBottomText(), "BOTTOM");
    }
}
