package com.example.computer.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.jar.Attributes;

public class new_list_Activity extends AppCompatActivity {

    private ListView lvMenu;

    static String[]NameTopic = {"Topic New",
            "Topic New1",
            "Topic New2",
            "Topic New3",
            "Topic New4",
            "Topic New5",};
    static String[] DateName = {
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559"};
    int[] resId={R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list_);

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        lvMenu.setAdapter(new CustomAdpater(getApplicationContext(), NameTopic,DateName,resId));
    }

}


