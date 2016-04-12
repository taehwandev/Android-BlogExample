package tech.thdev.recycler_lambda_example.base;

/**
 * Created by Tae-hwan on 3/28/16.
 * <p/>
 * Adapter default data struct.
 */
public class AdapterItem {

    public boolean isSelected;
    public int count;
    public int viewType;

    public AdapterItem() {

    }

    public AdapterItem(int viewType) {
        this.viewType = viewType;
    }
}
