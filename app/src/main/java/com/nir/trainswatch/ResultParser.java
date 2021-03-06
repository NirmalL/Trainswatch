package com.nir.trainswatch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nirmal on 11/8/2016.
 */

public class ResultParser extends AsyncTask<String, Void, ArrayList<Train>> {
    Activity searchActivity;
    Context context;
    protected ResultParser(Activity searchActivity, Context context) {
        this.searchActivity = searchActivity;
        this.context = context;
    }

    @Override
    protected ArrayList<Train> doInBackground(String... strings) {
        ArrayList<Train> trains;// = new ArrayList<Train>();
        String start = strings[0], end = strings[1];

        MalindaAdapter malingaAdapter = new MalindaAdapter(start, end);
        trains = malingaAdapter.getResults();

//        try {
//            Document doc = Jsoup.connect(strings[0]).get();
//            Elements rows = doc.select("div.table-responsive table.table > tbody > tr");
//            // TODO: receive result list of Train object and fill it
//            trains = new ArrayList<Train>();
//            if (rows!=null && rows.size()>0) {
//                for (int i=0; i<rows.size(); i++) {
//                    Elements one = rows.get(i).select("td");
//                    if (one.size()<8) {
//                        continue;
//                    }
////                    Elements two;
////                    if (rows.size()>1)
////                        two = rows.get(i+1).select("td");
//                    Train train = new Train();
//                    train.startTime = one.get(1).select("font").html();
//                    train.endTime = one.get(2).select("font").html();
//                    train.frequency = one.get(5).html();
//                    train.name = one.get(6).html();
//                    train.type = one.get(7).html();
//                    // TODO: get other data too
//                    trains.add(train);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return trains;
    }

    @Override
    protected void onPostExecute(ArrayList<Train> trains) {
        super.onPostExecute(trains);
        if (trains!=null && trains.size()>0) {
            Toast.makeText(SearchActivity.context, trains.size()+" trains, ["+trains.get(0).name+", ... ]", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(searchActivity, ResultsActivity.class);
            intent.putExtra("trains", trains);
            searchActivity.startActivity(intent);
        } else {
            Toast.makeText(SearchActivity.context, "Web service access failed. Please contact the developer.", Toast.LENGTH_SHORT).show();
        }
    }
}
