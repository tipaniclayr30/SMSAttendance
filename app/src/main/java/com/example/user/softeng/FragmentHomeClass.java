package com.example.user.softeng;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FragmentHomeClass extends Fragment {

     ListView lv;
     ArrayList<Model> clist;
     ClassListAdapter cadapter =null;

    DatabaseAttendance db;
    ArrayList<Class> c ;

    EditText editTextDays;
    TextView userId;
    private Switch dropswitch ;
    int drop=2,days;

    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.addclass1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClassDialog();
            }
        });

        lv = v.findViewById(R.id.classlist);
        clist = new ArrayList<>();
        cadapter = new ClassListAdapter(getActivity(),R.layout.row,clist);
        lv.setAdapter(cadapter);

        clist.clear();
        db = new DatabaseAttendance(getActivity());
        c = db.selectClass(NavMenu.ui);




        if (c.size()==0){
        }
        else{
            for (int i= 0 ; i< c.size(); i++ ) {

                clist.add(new Model(c.get(i).subject,c.get(i).cde,c.get(i).tme,c.get(i).drop,c.get(i).numdays));

            }
        }

        cadapter.notifyDataSetChanged();
        if(clist.size()==0) {
            Toast.makeText(getActivity(), "WALA", Toast.LENGTH_SHORT).show();
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Integer> arrCODE = new ArrayList<Integer>();
                arrCODE = db.selectUpdateclass(NavMenu.ui);

                ArrayList<Integer> aID = new ArrayList<Integer>();

                for (int i= 0 ; i< arrCODE.size(); i++ ){
                    aID.add(arrCODE.get(i));

                }

                FragmentStudents fs = new FragmentStudents ();
                Bundle args = new Bundle();
                args.putInt("id",aID.get(position));
                fs.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fs).commit();

                Toast.makeText(getActivity(), aID.get(position)+"", Toast.LENGTH_SHORT).show();

            }
        });



        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle("Update");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which ==0){
                            //update
                            ArrayList<Integer> arrID = new ArrayList<Integer>();

                            ArrayList<Integer> arrCODE = new ArrayList<Integer>();
                            arrCODE = db.selectUpdateclass(NavMenu.ui);

                            for (int i= 0 ; i< arrCODE.size(); i++ ) {
                                arrID.add(arrCODE.get(i));
                            }
                            Toast.makeText(getActivity(),arrID.get(position)+"",Toast.LENGTH_SHORT).show();
                            showDialogUpdateClass(getActivity(),arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });



        //1:08:41 END AT THAT TIME IN YOUTUBE  https://www.youtube.com/watch?v=q97_TbDxMWY

        // Inflate the layout for this fragment
        return v;
    }
    private void showDialogUpdateClass(Activity activity,final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_class_dialog);
        dialog.setTitle("Update Class");

        final EditText editTextName = dialog.findViewById(R.id.editTextRoom);
        final EditText editTextRoom = dialog.findViewById(R.id.editTextName);
        final EditText editTextTime = dialog.findViewById(R.id.editTextTime);
        editTextDays = dialog.findViewById(R.id.editTextDays);
        dropswitch = dialog.findViewById(R.id.dropswitch);
        editTextDays.setEnabled(false);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);




        dropswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextDays.setEnabled(true);

                    drop =1;
                } else {
                    editTextDays.setEnabled(false);
                    editTextDays.setText("");

                    drop = 2;
                }

            }
        });

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*1);
        int height = (int)(activity.getResources().getDisplayMetrics().widthPixels*1.1);

        dialog.getWindow().setLayout(width,height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             try {
                 if(drop==2){
                     days = 0;
                 }else{
                     days = Integer.parseInt(editTextDays.getText().toString());
                 }

                 db.updateClass(editTextName.getText().toString(), editTextRoom.getText().toString(), editTextTime.getText().toString(), drop, days, position);
                 updateClassList();
                 dialog.dismiss();
                 Toast.makeText(getActivity(),"Update Successful",Toast.LENGTH_SHORT).show();


             }catch(Exception error){
                 Log.e("Update error",error.getMessage());
             }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cancel
                dialog.dismiss();
            }
        });

    }

    public void updateClassList() {
        clist.clear();
        db = new DatabaseAttendance(getActivity());
        c = db.selectClass(NavMenu.ui);

        for (int i= 0 ; i< c.size(); i++ ) {

            clist.add(new Model(c.get(i).subject,c.get(i).cde,c.get(i).tme,c.get(i).drop,c.get(i).numdays));
        }

    }

    public void addClassDialog(){

        AddClassDialog addClassDialog = new AddClassDialog();
        addClassDialog.show(getFragmentManager(),"Add Class Dialog");

    }


}
