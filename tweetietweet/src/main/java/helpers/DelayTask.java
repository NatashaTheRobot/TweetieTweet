package helpers;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;

/**
 * Created by Natasha Murashev on 2/10/14.
 */
public class DelayTask extends AsyncTask {
    int count = 0;
    private ProgressBar progressBar;

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        while (count < 5) {
            SystemClock.sleep(1000); count++;
            publishProgress(count * 20);
        }
        return "Complete";
    }

//    @Override
//    protected void onProgressUpdate(Object[] values) {
//        progressBar.setProgress(values[0]);
//    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
