package osamamattar.monitorsensor;

import android.hardware.Sensor;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by osama_mattar on 11/2/15.
 */


class SensorListAdapter extends ArrayAdapter<Sensor> {
    SensorListAdapter(SensorsFragment sensorsFragment) {
        super(sensorsFragment.getActivity(), getRowResourceId(),
                sensorsFragment.getContract().getSensorList());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = super.getView(position, convertView, parent);

        ((TextView) result).setText(getItem(position).getName());

        return (result);
    }

    private static int getRowResourceId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return (android.R.layout.simple_list_item_activated_1);
        }

        return (android.R.layout.simple_list_item_1);
    }
}