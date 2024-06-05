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


    private void handleQuality(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality(item);
            if (item.sellIn < 6) {
                increaseQuality(item);
                increaseQuality(item);
            } else if (item.sellIn < 11) {
                increaseQuality(item);
            }
        } else if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else {
            decreaseQuality(item);
        }
    }

    private void handleExpiration(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private void handleExpired(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            decreaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
