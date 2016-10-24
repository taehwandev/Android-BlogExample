package tech.thdev.hugo_example.adapter.contract;

import tech.thdev.base.adapter.model.BaseRecyclerModel;
import tech.thdev.hugo_example.data.Items;

/**
 * Created by rgpkorea on 06/10/2016.
 */

public interface ListAdapterContract {

    interface View {
        void refresh();
    }

    interface Model extends BaseRecyclerModel<Items> {
        // Do noting ...
    }
}
