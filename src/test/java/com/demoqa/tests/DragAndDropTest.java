package com.demoqa.tests;

import com.demoqa.pages.DragAndDropPage;
import com.demoqa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropTest extends TestBase {

    private DragAndDropPage dnd;

    @BeforeMethod
    public void precondition() {
        dnd = new HomePage(driver).getDragAndDrop();
    }

    @Test(description = "Стартовое состояние: A в левом, B в правом")
    public void defaultStateTest() {
        Assert.assertEquals(dnd.getColumnAText(), "A");
        Assert.assertEquals(dnd.getColumnBText(), "B");
    }

    @Test(description = "Перетаскивание A -> B: буквы меняются местами")
    public void dragAtoBTest() {
        dnd.dragAtoB();
        Assert.assertEquals(dnd.getColumnAText(), "B");
        Assert.assertEquals(dnd.getColumnBText(), "A");
    }

    @Test(description = "Туда-обратно: A->B, потом B->A, возвращаемся в исходное")
    public void dragBackAndForthTest() {
        dnd.dragAtoB().dragBtoA();
        Assert.assertEquals(dnd.getColumnAText(), "A");
        Assert.assertEquals(dnd.getColumnBText(), "B");
    }
}
