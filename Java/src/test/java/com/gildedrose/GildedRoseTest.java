package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testsFromFixture() {
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(19, app.items[0].quality);
        assertEquals(1, app.items[1].quality);
        assertEquals(6, app.items[2].quality);
        assertEquals(80, app.items[3].quality);
        assertEquals(80, app.items[4].quality);
        assertEquals(21, app.items[5].quality);
        assertEquals(50, app.items[6].quality);
        assertEquals(50, app.items[7].quality);
    }


    /**
     * Once the sell by date has passed, Quality degrades twice as fast
     */
    @Test
    void testQualityDegradesTwiceAsFastAfterSellByDate() {
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 0, 20),
            new Item("Elixir of the Mongoose", 0, 7),
            new Item("Mana Cake", 0, 6)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(18, app.items[0].quality);
        assertEquals(5, app.items[1].quality);
        assertEquals(4, app.items[2].quality);
    }


    /**
     * The Quality of an item is never negative
     */
    @Test
    void testQualityIsNeverNegative() {
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 0, 0),
            new Item("Elixir of the Mongoose", 0, 1),
            new Item("Mana Cake", 0, 0)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
    }

    /**
     * "Aged Brie" actually increases in Quality the older it gets
     */
    @Test
    void testAgedBrieQualityIncreases() {
        Item[] items = new Item[]{
            new Item("Aged Brie", 2, 0),
            new Item("Aged Brie", 0, 0),
            new Item("Aged Brie", -1, 0)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(1, app.items[0].quality);
        assertEquals(2, app.items[1].quality);
        assertEquals(2, app.items[2].quality);
    }

    /**
     * The Quality of an item is never more than 50
     */
    @Test
    void testQualityIsNeverMoreThan50() {
        Item[] items = new Item[]{
            new Item("Aged Brie", -0, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 48),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
        assertEquals(50, app.items[2].quality);
        assertEquals(50, app.items[3].quality);
    }

    /**
     * "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
     */
    @Test
    void testSulfurasNeverHasToBeSoldOrDecreasesInQuality() {
        Item[] items = new Item[]{
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Sulfuras, Hand of Ragnaros", 100, 80)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(80, app.items[0].quality);
        assertEquals(80, app.items[1].quality);
        assertEquals(80, app.items[2].quality);
    }

    /**
     * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
     * Quality drops to 0 after the concert
     */
    @Test
    void testBackstagePassesQualityIncreases() {
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(21, app.items[0].quality);
        assertEquals(22, app.items[1].quality);
        assertEquals(23, app.items[2].quality);
        assertEquals(0, app.items[3].quality);
    }

    /**
     * "Conjured" items degrade in Quality twice as fast as normal items
     * todo: not sure if conjured brie or concert passes should increase in quality twice as fast as normal or is it only for degrading
     */
    @Test
    void testConjuredItemsDegradeTwiceAsFast() {
        Item[] items = new Item[]{
            new Item("Conjured +5 Dexterity Vest", 10, 20),
            new Item("Conjured Aged Brie", 2, 0),
            new Item("Conjured Aged Brie", 0, 0),
            new Item("Conjured Aged Brie", -1, 0),
            new Item("Conjured Elixir of the Mongoose", 5, 7),
            new Item("Conjured Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Conjured Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 10, 20),
            new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 5, 20),
            new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 0, 20)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(18, app.items[0].quality);
        assertEquals(2, app.items[1].quality);
        assertEquals(4, app.items[2].quality);
        assertEquals(4, app.items[3].quality);
        assertEquals(5, app.items[4].quality);
        assertEquals(80, app.items[5].quality);
        assertEquals(80, app.items[6].quality);
        assertEquals(22, app.items[7].quality);
        assertEquals(24, app.items[8].quality);
        assertEquals(26, app.items[9].quality);
        assertEquals(0, app.items[10].quality);
    }

}
