package IMS.ims.src;

public class Items {
    private int itemCount;
    private String itemId, itemName, itemCategory;

    public Items(String itemId, String itemName, String itemCategory, int itemCount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemCount = itemCount;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    @Override
    public String toString() {
        return itemId + "{itemName=" + itemName + ", itemCategory=" + itemCategory + ", itemCount=" + itemCount + '}';
    }
}