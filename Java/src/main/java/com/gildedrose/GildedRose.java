package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }
            handleQuality(item);
            handleExpiration(item);
            if (isExpired(item)) {
                handleExpired(item);
            }
        }
    }


    private static void handleQuality(Item item) {
        if (!item.name.equals("Aged Brie")
            && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            decreaseQuality(item);
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        increaseQuality(item);
                    }

                    if (item.sellIn < 6) {
                        increaseQuality(item);
                    }
                }
            }
        }
    }

    private static void handleExpiration(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private static void handleExpired(Item item) {
        if (!item.name.equals("Aged Brie")) {
            if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                decreaseQuality(item);
            } else {
                item.quality = 0;
            }
        } else {
            increaseQuality(item);
        }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
