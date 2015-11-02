package osamamattar.monitorsensor;

import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by osama_mattar on 11/2/15.
 */


public class SensorsFragment extends
        ContractListFragment<SensorsFragment.Contract> {
    static private final String STATE_CHECKED =
            "com.commonsware.android.sensor.monitor.STATE_CHECKED";
    private SensorListAdapter adapter = null;

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        adapter = new SensorListAdapter(this);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(adapter);

        if (state != null) {
            int position = state.getInt(STATE_CHECKED, -1);

            if (position > -1) {
                getListView().setItemChecked(position, true);
                getContract().onSensorSelected(adapter.getItem(position));
            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        l.setItemChecked(position, true);

        getContract().onSensorSelected(adapter.getItem(position));
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        state.putInt(STATE_CHECKED, getListView().getCheckedItemPosition());
    }

    interface Contract {
        void onSensorSelected(Sensor s);

        List<Sensor> getSensorList();
    }
}