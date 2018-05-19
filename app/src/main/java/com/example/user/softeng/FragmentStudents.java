package com.example.user.softeng;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class FragmentStudents extends Fragment {

    ListView lvs;
    ArrayList<Model1> slist;
    StudentListAdapter sadapter =null;

   static DatabaseAttendance db;
    ArrayList<Student> s ;

    FloatingActionButton fab;
    int id;


    ArrayList<Integer> arrID;
    ArrayList<StudentRetrieve> arrSI;

    static  ArrayList <Integer>recID;
    static ArrayList<String> recNAME;
    static String date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_students, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.addstudent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentDialog();
            }
        });

        Calendar c = Calendar.getInstance();
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date = AttendanceSet.date;

        lvs = v.findViewById(R.id.studentlist);
        lvs.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        slist = new ArrayList<>();
        sadapter = new StudentListAdapter(getActivity(),R.layout.strow,slist);
        lvs.setAdapter(sadapter);


        slist.clear();
        db = new DatabaseAttendance(getActivity());

        if ( date.equals(currentDateString)) {
            s = db.selectStudent(FragmentHomeClass.ci);

            if (s.size() == 0) {
            } else {
                for (int i = 0; i < s.size(); i++) {
                    slist.add(new Model1(s.get(i).user, s.get(i).course, s.get(i).idnum, s.get(i).name, s.get(i).pname, s.get(i).number));
                }
            }

            sadapter.notifyDataSetChanged();
            if (slist.size() == 0) {
                Toast.makeText(getActivity(), "WALA", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getActivity(),"THANKYOUU LORD!",Toast.LENGTH_SHORT);

        }

        arrID = new ArrayList();
        recID = new ArrayList();
        recNAME = new ArrayList();

        arrSI = db.selectStudentId(FragmentHomeClass.ci);

        for (int i= 0 ; i< arrSI.size(); i++ ) {

          arrID.add(arrSI.get(i).id);
          recID.add(arrSI.get(i).id);
          recNAME.add(arrSI.get(i).name);

        }


        lvs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


              String recI =((TextView)view).getText().toString();

              if (recID.contains(arrID.get(position)) ){
                  recID.remove(arrID.get(position));
                  recNAME.remove(recI);
              }
              else{
                  recID.add(arrID.get(position));
                  recNAME.add(recI);
              }


            }
        });


       lvs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               CharSequence[] items = {"Update"};

               AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

               dialog.setItems(items, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       if (which == 0) {
                           //update
                           ArrayList<Integer> arrID = new ArrayList<Integer>();

                           ArrayList<Integer> arrCODE = new ArrayList<Integer>();
                           arrCODE = db.selectUpdatestudent(FragmentHomeClass.ci);

                           for (int i = 0; i < arrCODE.size(); i++) {
                               arrID.add(arrCODE.get(i));
                           }

                           Toast.makeText(getActivity(), arrID.get(position) + "", Toast.LENGTH_SHORT).show();
                          showDialogUpdateStudent(getActivity(), arrID.get(position));
                       }
                   }
               });

               return true;
           }
       });

        return v;

    }

    private void showDialogUpdateStudent(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_student_dialog);
        dialog.setTitle("Update Student");

       final EditText txtID = dialog.findViewById(R.id.txtID);
       final EditText txtName = dialog.findViewById(R.id.name);
       final EditText txtParentName = dialog.findViewById(R.id.txtParentName);
       final EditText txtCellNum = dialog.findViewById(R.id.txtCellNum);
       Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
       Button btnCancel = dialog.findViewById(R.id.btnCancel);


        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*1);
        int height = (int)(activity.getResources().getDisplayMetrics().widthPixels*1.1);

        dialog.getWindow().setLayout(width,height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.updateSTUDENT(Integer.parseInt(txtID.getText().toString()),txtName.getText().toString(),txtParentName.getText().toString(),txtCellNum.getText().toString(),position);

                dialog.dismiss();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_student,new FragmentStudents()).commit();

                Toast.makeText(getActivity(),"Update Successful",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addStudentDialog(){
        AddStudentDialog addStudentDialog = new AddStudentDialog();
        addStudentDialog.show(getFragmentManager(),"Add Student Dialog");
    }



    public static void  saveAttendance(){
        //int user, int course, int student, String studentname, String date, int remark, int status

            int size = recID.size();
            for (int i = 0; i< size ; i++  ){

                db.addAttendanceRecord(NavMenu.ui,FragmentHomeClass.ci, recID.get(i) , recNAME.get(i), date , 1,1);

            }



    }




}
