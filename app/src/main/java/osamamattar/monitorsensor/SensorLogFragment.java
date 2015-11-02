package osamamattar.monitorsensor;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by osama_mattar on 11/2/15.
 */


public class SensorLogFragment extends ListFragment implements
        SensorEventListener {
    private SensorLogAdapter adapter = null;
    private boolean isXYZ = false;

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        getListView().setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // unused
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        Float[] values = new Float[3];

        values[0] = e.values[0];
        values[1] = e.values[1];
        values[2] = e.values[2];

        adapter.add(values);
    }

    void init(boolean isXYZ) {
        this.isXYZ = isXYZ;
        adapter = new SensorLogAdapter(this);
        setListAdapter(adapter);
    }

    class SensorLogAdapter extends ArrayAdapter<Float[]> {
        public SensorLogAdapter(SensorLogFragment sensorLogFragment) {
            super(sensorLogFragment.getActivity(),
                    android.R.layout.simple_list_item_1,
                    new ArrayList<Float[]>());
        }

        @SuppressLint("DefaultLocale")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView row =
                    (TextView) super.getView(position, convertView, parent);
            String content = null;
            Float[] values = getItem(position);

            if (isXYZ) {
                content =
                        String.format("%7.3f / %7.3f / %7.3f / %7.3f",
                                values[0],
                                values[1],
                                values[2],
                                Math.sqrt(values[0] * values[0] + values[1]
                                        * values[1] + values[2] * values[2]));
            } else {
                content = String.format("%7.3f", values[0]);
            }

            row.setText(content);

            return (row);
        }
    }
}