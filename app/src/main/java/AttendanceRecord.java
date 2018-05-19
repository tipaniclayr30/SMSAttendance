import com.example.user.softeng.AttendanceSet;

public class AttendanceRecord {

   public AttendanceRecord(int u, int c, int s, String sn , String d, int r, int st ){

        sname=sn;
        user= u;
        course = c;
        student = s;
        remark = r;
        status = s;
        date = d;

   }
    public int user,course,student,remark,status;
    public String date,sname;
}
