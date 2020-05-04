package tech.thdev.app.ui.radio;

import tech.thdev.app.base.presenter.BasePresenter;
import tech.thdev.app.data.RadioItem;
import tech.thdev.simpleadapter.data.source.AdapterRepository;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class RadioPresenter extends BasePresenter<RadioPresenterView> {

    private AdapterRepository adapterRepository;

    public RadioPresenter(RadioPresenterView view, AdapterRepository adapterRepository) {
        super(view);

        this.adapterRepository = adapterRepository;
    }

    public void onRecyclerItemClick(int position) {
        for (int i = 0; i < adapterRepository.getItemCount(); i++) {
            RadioItem item = (RadioItem) adapterRepository.getItem(i);
            item.isSelected = position == i;
        }
        getView().notifyDataSetChanged();

        RadioItem radioItem = (RadioItem) adapterRepository.getItem(position);
        getView().showBottomSheet(radioItem);
    }

    public void removeRadioItem(int position) {
        adapterRepository.removeAt(position);
    }

    /**
     * Item add
     */
    public void addItem(String name) {
        adapterRepository.addItem(0, new RadioItem(name));
    }
}
